package org.indigo.cloudproviderranker;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.Headers;
//import com.sun.net.httpserver.HttpHandler;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.HashMap;
//import java.util.Hashtable;
import java.io.InputStream;
import java.io.IOException;
import java.util.Iterator;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.Logger;
import java.util.logging.Level;

import com.google.gson.JsonArray;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

//import org.kie.api.KieServices;
//import org.kie.api.runtime.KieContainer;
//import org.kie.api.runtime.StatelessKieSession;

/**
 *
 * This is the real ranker which receives the JSON text to be converted to CloudProvider's instances
 * each instance is ranked basing on the rule define in the file main/resources/rules/CloudProviderRule.drl
 *
 * @author dorigoa
 *
 */
public class RankHandler extends RequestHandler {

  @Override
  public void handle(final HttpExchange httpExchange) throws IOException {
    if (httpExchange.getRequestMethod().compareToIgnoreCase("POST") != 0) {
      String response = "API \"rank\" only supports POST method";
      httpExchange.sendResponseHeaders(405,  response.getBytes().length);
      OutputStream os = httpExchange.getResponseBody();
      os.write(response.getBytes());
      os.close();
      return;
    }

    clientHostName = httpExchange.getRemoteAddress().getHostName();

    ParseResult responseToClient = parseRequest(httpExchange.getRequestBody()/*Line*/);
    Headers responseHeaders = httpExchange.getResponseHeaders();
    responseHeaders.set("Content-Type",  "application/json");
    httpExchange.sendResponseHeaders(responseToClient.getHTTPCode(),
				     responseToClient.getMessage().getBytes().length);
    String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
    Logger.getLogger("").log(Level.INFO,  timeStamp
			     + " ["
			     + clientHostName
			     + "] Returning ranked provider to the client: "
			     + responseToClient.getMessage() + "\n\n");

    OutputStream os = httpExchange.getResponseBody();
    os.write(responseToClient.getMessage().getBytes());
    os.close();
  }

  public ParseResult parseRequest(final InputStream is) {
    String line = "";
    try {
      InputStreamReader inputReader = new InputStreamReader(is, "utf-8");
      BufferedReader buffReader = new BufferedReader(inputReader);
      String sline = "";
      while ((sline = buffReader.readLine()) != null) {
        line += sline;
      }
      is.close();
    } catch (IOException ioe) {
    }
    String timeStamp = new
	SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());

    Logger.getLogger("").log(Level.INFO,  timeStamp
			     + " [" + clientHostName
			     + "] New request for /rank API from this client... ");

    ArrayList<Preference> preferences = new ArrayList<Preference>();
    try {
      Gson gson = new Gson();
      JsonElement element = gson.fromJson(line,  JsonElement.class);
      JsonObject obj = element.getAsJsonObject();

      boolean specifiedPreferences = false,  specifiedSla = false;
      //
      // convert preferences json block to Java Preference[] array
      //
      if (obj.has("preferences")) {
        specifiedPreferences = true;
	preferences = Preference.fromJsonObject(obj);
      }

      Service[] services = null;
      ArrayList<Sla> slaArray = null;

      //
      //
      // Convert sla json blocks to Java Sla arraylist
      //
      //
      if (obj.has("sla")) {
	specifiedSla = true;
	slaArray = Sla.fromJsonObject(obj);
      }

      // 	    KieServices kieServices      = KieServices.Factory.get();
      // 	    KieContainer kContainer      = kieServices.getKieClasspathContainer();
      // 	    StatelessKieSession kSession = kContainer.newStatelessKieSession();
      // 	    kSession.execute(SLAs);

      //
      //
      // Concatenate all preferences' priorities and sort them basing on the weight
      //
      //
      //
      ArrayList<Priority> allPriorities = new ArrayList<Priority>();
      if (specifiedPreferences) {
        for (int i = 0; i < preferences.size(); ++i) {
	  ArrayList<Priority> prioritiesLoc = preferences.get(i).priorities;
	  for (int j = 0; j < prioritiesLoc.size(); ++j) {
	    allPriorities.add(prioritiesLoc.get(j));
	  }
	}
	Collections.sort(allPriorities); // Sorting based on Priority.weight
      }

      //
      //
      // Build an Hashtable sla_id -> provider_name and a map provider->SLA
      //
      //
      HashMap<String,  String> slaidToProvider = new HashMap<String,  String>();
      HashMap<String,  Sla> providerToSLAMap = new HashMap<String,  Sla>();
      for (Sla sla : slaArray) {
        slaidToProvider.put(sla.id,  sla.provider);
          providerToSLAMap.put(sla.provider,  sla);
	}

      //
      //
      // If preferences are specified,  order the providers
      // based on the priorities and return them to the client
      //
      //
      Vector<RankedCloudProvider> rankedProviders = new Vector<RankedCloudProvider>();
      if (specifiedPreferences && specifiedSla) {
        int j = 0;
	for (Priority p : allPriorities) {
	  rankedProviders.add(new RankedCloudProvider(slaidToProvider.get(p.sla_id),
						      (allPriorities.size() - j++),
						      true,
						      "")
			       );
	}

	Vector<String> rcpVec = new Vector<String>();
	for (RankedCloudProvider rcp : rankedProviders) {
	  rcpVec.add(gson.toJson(rcp));
	}

	return new ParseResult("[" + String.join(", ",  rcpVec) + "]",  200);
      }

      HashMap<String,  ArrayList<PaaSMetricRanked>> paasMetricRanked = null;
      if (obj.has("monitoring")) {
	  JsonArray arrayTmp =  obj.getAsJsonArray("monitoring");
	  paasMetricRanked = (new PaaSMetricRanked()).fromJsonArray(arrayTmp);
      }

      Set<String> providers = paasMetricRanked.keySet();

      // 	    KieServices kieServices      = KieServices.Factory.get();
      // 	    KieContainer kContainer      = kieServices.getKieClasspathContainer();
      // 	    StatelessKieSession kSession = kContainer.newStatelessKieSession();
      // 	    kSession.execute(paaSMetricRankerArrayList);
      ArrayList<RankedCloudProvider> rankedCloudProviders =
	  new ArrayList<RankedCloudProvider>();
      for (String provider : providers) {
	RankedCloudProvider rcp = new RankedCloudProvider(provider,  0.0f,  true,  "");
	ArrayList<PaaSMetricRanked> psmr = paasMetricRanked.get(provider);
	for (Iterator<PaaSMetricRanked> jt = psmr.iterator(); jt.hasNext();) {
	  PaaSMetricRanked p = jt.next();
	  p.setClientIP(clientHostName);
	  rcp.addToRank(p.getRank());
	}
	rcp.addToRank(providerToSLAMap.get(provider).rank);
	rankedCloudProviders.add(rcp);
      }

      //
      //
      // Iterate over rankedCloudProviders and build up
      // a JSON response
      //
      //
      Vector<String> respVec = new Vector<String>();
      for (RankedCloudProvider rcp : rankedCloudProviders) {
        String json = gson.toJson(rcp);
	respVec.add(json);
      }
      return  new ParseResult("[" + String.join(", ",  respVec)  + "]",  200);
    } catch (Exception e) {
      return new ParseResult("Exception parsing JSON client request: " + e.getMessage() + "\n",  400);
    } catch (Throwable e) {
      return new ParseResult("Throwable parsing JSON client request: " + e.getMessage() + "\n",  400);
    }
  }
}
