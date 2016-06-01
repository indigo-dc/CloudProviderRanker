package org.indigo.cloudproviderruleengine;

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
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Collections;

import com.google.gson.*;

import org.kie.api.KieServices;
//import org.kie.api.KieServicesImpl;
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
	
	ArrayList<Preference> preferences = new ArrayList<Preference>();
	
	try {
	    InputStream is = httpExchange.getRequestBody();
	    InputStreamReader inputReader = new InputStreamReader(is,"utf-8");
	    BufferedReader buffReader = new BufferedReader(inputReader);
	    String Line = "";
	    String line = "";
	    while( (line = buffReader.readLine()) != null) {
       		Line += line;
	    }
	    
	    Gson gson = new Gson();
	    //System.err.println("JSON=["+Line+"]");
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
	    
	    //
	    //
	    // Concatenate all preferences' priorities and sort them basing on the weight
	    //
	    //
	    /* Test with: curl -k -X POST -d \
	     '{"preferences":[{"service_type":"compute","priority":[{"sla_id":"4401ac5dc8cfbbb737b0a02575ee53f6","service_id":"4401ac5dc8cfbbb7a02575e8040f","weight":0.1},{"sla_id":"4401ac5dc8cfbbb737b0a02575ee3b58","service_id":"4401ac5dc8cfbbb737b0a02575e6f4bc","weight":0.9}],"id":"4401ac5dc8cfbbb737b0a02575ee0e55"},{"service_type":"storage","priority":[{"sla_id":"4401ac5dc8cfbbb737b0a02575ee53f7","service_id":"4401ac5dc8cfbbb737b0a02575e8040f","weight":0.7},{"sla_id":"4401ac5dc8cfbbb737b0a02575ee3b60","service_id":"4401ac5dc8cfbbb737b0a02575e6f4bd","weight":0.4}],"id":"4401ac5dc8cfbbb737b0a02575ee0e59"}],"sla":[{"customer":"indigo-demo","provider":"provider-UPV-GRyCAP","start_date":"11.01.2016+15:50:00","end_date":"11.02.2016+15:50:00","services":[{"type":"compute","service_id":"4401ac5dc8cfbbb737b0a02575e6f4bc","targets":[{"type":"public_ip","unit":"none","restrictions":{"total_limit":100,"total_guaranteed":10}}]}],"id":"4401ac5dc8cfbbb737b0a02575ee3b58"},{"customer":"indigo-demo","provider":"provider-PADOVA-CAP","start_date":"11.01.2016+15:50:00","end_date":"11.02.2016+15:50:00","services":[{"type":"compute","service_id":"4401ac5dc8cfbbb737b0a02575e6f4bc","targets":[{"type":"public_ip","unit":"none","restrictions":{"total_limit":100,"total_guaranteed":10}}]}],"id":"4401ac5dc8cfbbb737b0a02575ee3b60"},{"customer":"indigo-demo","provider":"provider-RECAS-BARI","start_date":"11.01.2016+15:50:00","end_date":"11.02.2016+15:50:00","services":[{"type":"compute","service_id":"4401ac5dc8cfbbb737b0a02575e8040f","targets":[{"type":"computing_time","unit":"h","restrictions":{"total_guaranteed":200}}]}],"id":"4401ac5dc8cfbbb737b0a02575ee53f6"},{"customer":"indigo-demo","provider":"provider-RECAS-TORINO","start_date":"11.01.2016+15:50:00","end_date":"11.02.2016+15:50:00","services":[{"type":"compute","service_id":"4401ac5dc8cfbbb737b0a02575e8040f","targets":[{"type":"computing_time","unit":"h","restrictions":{"total_guaranteed":200}}]}],"id":"4401ac5dc8cfbbb737b0a02575ee53f7"}]}'\
	     http://localhost:8443/rank
  	    */
	    // see http://pastebin.com/TnRWU2cj for pretty formatted version
	    
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
	    // Build an Hashtable sla_id -> provider_name
	    //
	    //
	    Hashtable<String, String> slaid_to_provider = new Hashtable<String, String>();
	    for (Iterator<Sla> i = SLAs.iterator(); i.hasNext(); ) {
	      Sla sla = i.next( );
	      slaid_to_provider.put(sla.id, sla.provider);
	    }
	    
	    //
	    //
	    // If preferences are specified, order the providers basing on the priorities and return them to the client
	    //
	    //
	    Vector<RankedCloudProvider> ranked_providers = new Vector<RankedCloudProvider>();
	    if(specified_preferences && specified_sla) {
	      int j = 0;
	      for (Iterator<Priority> i = all_priorities.iterator(); i.hasNext(); ) {
	        Priority p = i.next( );
	        ranked_providers.add(new RankedCloudProvider( slaid_to_provider.get( p.sla_id ), 
					 		      (all_priorities.size() - j++),
					 		      true,
					 		      "" ) 
							    );
	      }
	      
	      Vector<String> rcp_vec = new Vector<String>();
	      for (Iterator<RankedCloudProvider> i = ranked_providers.iterator(); i.hasNext(); ) {
	        RankedCloudProvider rcp = i.next( );
	        rcp_vec.add(gson.toJson(rcp));
	      }
	      
	      String response = "[" + String.join(",", rcp_vec) + "]";
	      
	      System.err.println("[" + clientHostName + "] Returning ranked provider to the client: "+ response + "\n\n");
	      Headers responseHeaders = httpExchange.getResponseHeaders();
	      responseHeaders.set("Content-Type", "application/json");
 	      httpExchange.sendResponseHeaders(200, response.getBytes().length);
 	      OutputStream os = httpExchange.getResponseBody();
 	      os.write(response.getBytes());
 	      os.close();
 	      return;
	    }
	    
	    HashMap<String, ArrayList<PaaSMetricRanked>> paasMetricRanked = null;
	    if(obj.has("monitoring")) {
	      //System.err.println("MONITORING="+obj.getAsString());
	      paasMetricRanked = PaaSMetricRanked.fromJsonArray( obj.getAsJsonArray("monitoring") );
	    }
	    
	    Set<String> providers = paasMetricRanked.keySet( );
// 	    ArrayList<PaaSMetricRanked> paaSMetricRankerArrayList = new ArrayList<PaaSMetricRanked>( );
//    	    for(Iterator<String> it = providers.iterator( ); it.hasNext( ); ) {
// 	      String provider = it.next( );
// 	      //System.err.println("provider[" + provider + "]");
// 	      for(Iterator<PaaSMetricRanked> i2t = paasMetricRanked.get(provider).iterator( ); i2t.hasNext( ); ) {
// 	        PaaSMetricRanked P = i2t.next( );
// 		//System.err.println("    " + P );
// 		paaSMetricRankerArrayList.add( P );
//   	      } 
// 	    }
	    
// 	    KieServices kieServices      = KieServices.Factory.get( );
// 	    KieContainer kContainer      = kieServices.getKieClasspathContainer( );
// 	    StatelessKieSession kSession = kContainer.newStatelessKieSession( );
// 	    kSession.execute( paaSMetricRankerArrayList );
	    ArrayList<RankedCloudProvider> rankedCloudProviders = new ArrayList<RankedCloudProvider>();
	    for(Iterator<String> it = providers.iterator(); it.hasNext( ); ) {
	      String provider = it.next( ) ;
	      RankedCloudProvider rcp = new RankedCloudProvider( provider, 0.0f, true, "" );
	      for(Iterator<PaaSMetricRanked> jt = paasMetricRanked.get(provider).iterator( ); jt.hasNext( ); ) {
	        PaaSMetricRanked p = jt.next( );
	        rcp.addToRank( p.getRank( ) );
	      }
	      rankedCloudProviders.add( rcp );
	    }
	    
	    //
	    //
	    // Iterate over rankedCloudProviders and build up 
	    // a JSON response
	    //
	    //ArrayList<RankedCloudProvider> rankedCloudProvider = new ArrayList<RankedCloudProvider>();
	    Vector<String> respVec = new Vector<String>();
	    for(Iterator<RankedCloudProvider> it = rankedCloudProviders.iterator( ); it.hasNext( ); ) {
	      //String provider = it.next( );
	      //ArrayList<PaaSMetricRanked> paaSMetricRankedArray = paasMetricRanked.get( provider ); 
	      //float totalRank = 0.0f;
	      //for(Iterator<PaaSMetricRanked> it2 = paaSMetricRankedArray.iterator( ); it2.hasNext( ); ) {
	      //  PaaSMetricRanked p = it2.next( ) ;
	      //  totalRank += p.getMetricValue( );
	      //}
	      
	      //RankedCloudProvider rcp = new RankedCloudProvider( provider, totalRank, true, "" );
	      
	      String json = gson.toJson(it.next());
 	      respVec.add(json);
	      //rankedCloudProvider.add( rcp );
	    }
	    
	    String response = "{" + String.join("," , respVec)  + "}";
 	    System.err.println( "[" + clientHostName + "] Returning ranked provider to the client: "+ response + "\n\n" );
 	    httpExchange.sendResponseHeaders( 200, response.getBytes().length );
 	    OutputStream os = httpExchange.getResponseBody( );
 	    os.write( response.getBytes() );
 	    os.close( );
 	    return;
	    
	} catch(Exception e) {
	    String err = "Exception parsing JSON client request: " + e.getMessage() + "\n";
	    httpExchange.sendResponseHeaders(400, err.getBytes().length);
	    OutputStream os = httpExchange.getResponseBody();
	    os.write(err.getBytes());
	    os.close();
	    e.printStackTrace();
	    return;
	} catch(Throwable e) {
	    String err = "Throwable parsing JSON client request: " + e.getMessage() + "\n";
	    httpExchange.sendResponseHeaders(400, err.getBytes().length);
	    OutputStream os = httpExchange.getResponseBody();
	    os.write(err.getBytes());
	    os.close();
	    e.printStackTrace();
	    return;
	}
    }
}
