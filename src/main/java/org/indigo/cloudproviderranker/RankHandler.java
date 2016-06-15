 package org.indigo.cloudproviderranker;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.Headers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Set;
import java.util.Vector;
import java.util.Iterator;
import java.util.HashMap;

import java.text.SimpleDateFormat;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Collections;

import com.google.gson.*;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

/**
 * 
 * This is the real ranker which receives the JSON text to be converted to CloudProvider's instances
 * each instance is ranked basing on the rule define in the file main/resources/rules/CloudProviderRule.drl
 * 
 * @author dorigoa
 *
 */
public class RankHandler implements HttpHandler {
    
    private String clientHostName = "";
 
    /**
     *
     *
     *
     *
     *
     *
     *
     */   
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
	if(httpExchange.getRequestMethod( ).compareToIgnoreCase("POST")!=0) {
	    String response = "API \"rank\" only supports POST method";
	    httpExchange.sendResponseHeaders(405, response.getBytes().length);
	    OutputStream os = httpExchange.getResponseBody();
	    os.write(response.getBytes());
	    os.close();
	    return;
	}
	
	clientHostName = httpExchange.getRemoteAddress( ).getHostName( );
	String Line = "";
	try {
	    InputStream is = httpExchange.getRequestBody();
	    InputStreamReader inputReader = new InputStreamReader(is,"utf-8");
	    BufferedReader buffReader = new BufferedReader(inputReader);
	    String line = "";
	    while( (line = buffReader.readLine()) != null) {
       		Line += line;
	    }
	} catch(IOException ioe) {

	}
	String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format( new java.util.Date() );
	System.err.println(timeStamp + " [" + clientHostName + "] New request from this client... ");

	ParseResult responseToClient = parseRequest( Line );
 	Headers responseHeaders = httpExchange.getResponseHeaders();
	responseHeaders.set("Content-Type", "application/json");
	httpExchange.sendResponseHeaders(responseToClient.getHTTPCode(), responseToClient.getMessage().getBytes().length);
	timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format( new java.util.Date() );
	System.err.println(timeStamp + " [" + clientHostName + "] Returning ranked provider to the client: "+ responseToClient.getMessage() + "\n\n");
	OutputStream os = httpExchange.getResponseBody();
	os.write( responseToClient.getMessage().getBytes() );
	os.close();
    }

    /**
     *
     *
     *
     *
     *
     *
     *
     */
    public ParseResult parseRequest( String Line ) {
	ArrayList<Preference> preferences = new ArrayList<Preference>();
	try{
	    
	    Gson gson = new Gson();
	    JsonElement E = gson.fromJson(Line, JsonElement.class);
	    JsonObject obj = E.getAsJsonObject( );
	    
	    boolean specified_preferences = false, specified_sla = false;
	    
	    //
	    //
	    // convert preferences json block to Java Preference[] array
	    //
	    //
	    if( obj.has("preferences") ) {
	        specified_preferences = true;
		preferences = Preference.fromJsonObject( obj );
	    }
	    
	    Service[] services = null;
	    ArrayList<Sla> SLAs = null;
	    
	    //
	    //
	    // Convert sla json blocks to Java Sla arraylist
	    //
	    //
	    if( obj.has("sla") ) {
	      specified_sla = true;
	      SLAs = Sla.fromJsonObject( obj );
	    }
	    
// 	    KieServices kieServices      = KieServices.Factory.get( );
// 	    KieContainer kContainer      = kieServices.getKieClasspathContainer( );
// 	    StatelessKieSession kSession = kContainer.newStatelessKieSession( );
// 	    kSession.execute( SLAs );
	    	    
	    //
	    //
	    // Concatenate all preferences' priorities and sort them basing on the weight
	    //
	    //
	    //
	    ArrayList<Priority> all_priorities = new ArrayList<Priority>();
	    if(specified_preferences) {
	      for (int i = 0; i < preferences.size(); ++i) {
	        ArrayList<Priority> priorities_loc = preferences.get(i).priorities;
	        for(int j = 0; j < priorities_loc.size(); ++j) {
	          all_priorities.add( priorities_loc.get(j) );
	        }
	      }
	      Collections.sort(all_priorities); // Sorting based on Priority.weight
	    }
	    
	    //
	    //
	    // Build an Hashtable sla_id -> provider_name and a map provider->SLA
	    //
	    //
	    HashMap<String, String> slaid_to_provider = new HashMap<String, String>();
	    HashMap<String, Sla> providerToSLAMap = new HashMap<String, Sla>();
	    for( Sla sla : SLAs ) {
	      slaid_to_provider.put(sla.id, sla.provider);
	      providerToSLAMap.put( sla.provider, sla );
	    }
	    
	    //
	    //
	    // If preferences are specified, order the providers basing on the priorities and return them to the client
	    //
	    //
	    Vector<RankedCloudProvider> ranked_providers = new Vector<RankedCloudProvider>();
	    if(specified_preferences && specified_sla) {
	      int j = 0;
	      for( Priority p : all_priorities ) {
	        ranked_providers.add(new RankedCloudProvider( slaid_to_provider.get( p.sla_id ), 
					 		      (all_priorities.size() - j++),
					 		      true,
					 		      "" ) 
							    );
	      }
	      
	      Vector<String> rcp_vec = new Vector<String>( );
	      for(RankedCloudProvider rcp : ranked_providers ) {
	        rcp_vec.add( gson.toJson(rcp) );
	      }
	      
	      return new ParseResult("[" + String.join(",", rcp_vec) + "]", 200);
	    }
	    
	    HashMap<String, ArrayList<PaaSMetricRanked>> paasMetricRanked = null;
	    if(obj.has("monitoring")) {
	      paasMetricRanked = PaaSMetricRanked.fromJsonArray( obj.getAsJsonArray("monitoring") );
	    }
	    
	    Set<String> providers = paasMetricRanked.keySet( );
	    
// 	    KieServices kieServices      = KieServices.Factory.get( );
// 	    KieContainer kContainer      = kieServices.getKieClasspathContainer( );
// 	    StatelessKieSession kSession = kContainer.newStatelessKieSession( );
// 	    kSession.execute( paaSMetricRankerArrayList );
	    ArrayList<RankedCloudProvider> rankedCloudProviders = new ArrayList<RankedCloudProvider>();
	    for( String provider : providers ) {
	      RankedCloudProvider rcp = new RankedCloudProvider( provider, 0.0f, true, "" );
	      for(Iterator<PaaSMetricRanked> jt = paasMetricRanked.get(provider).iterator( ); jt.hasNext( ); ) {
	        PaaSMetricRanked p = jt.next( );
	        rcp.addToRank( p.getRank( ) );
	      }
	      rcp.addToRank( providerToSLAMap.get( provider ).rank );
	      rankedCloudProviders.add( rcp );
	    }
	    
	    //
	    //
	    // Iterate over rankedCloudProviders and build up 
	    // a JSON response
	    //
	    //
	    Vector<String> respVec = new Vector<String>();
	    for( RankedCloudProvider rcp : rankedCloudProviders ) {
	      String json = gson.toJson( rcp/*it.next()*/);
 	      respVec.add(json);
	    }
	    return  new ParseResult("{" + String.join("," , respVec)  + "}", 200);
	} catch(Exception e) {
	    return new ParseResult("Exception parsing JSON client request: " + e.getMessage() + "\n", 400);
	} catch(Throwable e) {
	    return new ParseResult("Throwable parsing JSON client request: " + e.getMessage() + "\n", 400);
	}
    }
}
