package org.indigo.cloudproviderruleengine;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.Headers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Vector;
import java.util.Iterator;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Collections;

import com.google.gson.*;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

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
	
	Preference[] preferences = null;
	
	try {
	    InputStream is = httpExchange.getRequestBody();
	    InputStreamReader inputReader = new InputStreamReader(is,"utf-8");
	    BufferedReader buffReader = new BufferedReader(inputReader);
	    String Line = "";
	    String line = "";
	    while( (line = buffReader.readLine()) != null) {
       		Line += line;
	    }
	    System.err.println("DEBUG - Line="+Line);
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
	      Priority[] priorities = null;
	      JsonArray Preferences = obj.get("preferences").getAsJsonArray( );
	      preferences = new Preference[Preferences.size() ];
	      for(int i = 0; i< Preferences.size(); ++i) {
	        JsonObject pref = Preferences.get(i).getAsJsonObject( );
		priorities = parsePriorities( Preferences.get(i).getAsJsonObject( ).get("priority").getAsJsonArray( ) );
		preferences[i] = new Preference( pref.get("service_type").getAsString( ), priorities );
	      }
	    }
	    
	    
	    Service[] services = null;
	    ArrayList<Sla> SLAs = new ArrayList<Sla>();
	    
	    //
	    //
	    // Convert sla json blocks to Java Sla arraylist
	    //
	    //
	    if( obj.has("sla") ) {
	      specified_sla = true;
	      JsonArray SLAS = obj.get("sla").getAsJsonArray( );
	      for(int i=0; i < SLAS.size( ); ++i) {
                JsonObject currentSLA = SLAS.get(i).getAsJsonObject( );
                services = parseService( currentSLA );
		SLAs.add( new Sla( currentSLA.get("id").getAsString(),
				   currentSLA.get("customer").getAsString(),
				   currentSLA.get("provider").getAsString(),
				   currentSLA.get("start_date").getAsString(),
				   currentSLA.get("end_date").getAsString(), services) );
	      }
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
	    for (int i = 0; i < preferences.length; ++i) {
	      Priority[] priorities_loc = preferences[i].priorities;
	      for(int j = 0; j < priorities_loc.length; ++j) {
	        all_priorities.add( priorities_loc[j] );
	      }
	    }
	    Collections.sort(all_priorities);
	    
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
	    // if specified preferences, order the providers and immediately return them to the client
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
					 		      "" ) );
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

	    
	    /**
	     *
	     * Order the providers basing on the preferences (if any)
	     *
	     */

	    
// 	    if ( obj.has("cloudproviders") ) {
// 		cpvec = parse( obj.get("cloudproviders").getAsJsonArray( ) );
// 	    }
// 	    
// 	    if(cpvec.size()==0) {
// 		String response = "No valid cloud provider was found: have you provided some ? and with correct JSON syntax ?";
// 		t.sendResponseHeaders(200, response.getBytes().length);
// 		OutputStream os = t.getResponseBody();
// 		os.write(response.getBytes());
// 		os.close();
// 		return;
// 	    }
// 	    
// 	    KieServices ks = KieServices.Factory.get();
// 	    KieContainer kContainer = ks.getKieClasspathContainer();
// 	    KieSession kSession = kContainer.newKieSession("ksession-rules");
// 	    
// 	    for(CloudProvider cp : cpvec )
// 		kSession.insert(cp);
// 	    
// 	    int tot = kSession.fireAllRules();
// 	    System.err.println("[" + clientHostName + "] Total rules applied="+tot);
// 	    
// 	    Vector<String> respVec = new Vector<String>();
// 	    for(CloudProvider cp : cpvec ) {
// 		RankedCloudProvider rcp = null;
// 		if(!cp.isGoodParsed( )) {
// 		    rcp = new RankedCloudProvider( cp.getID(), 
// 						   cp.getName(), 
// 						   cp.getTotalRank(), 
// 						   false, 
// 						   "Not ranked provider because " + cp.getParseError( ) );
// 		} else
// 		    rcp = new RankedCloudProvider( cp.getID(), 
// 						   cp.getName(), 
// 						   cp.getTotalRank(), 
// 						   true, 
// 						   "" );
// 		Gson gson2 = new Gson();
// 		String json = gson2.toJson(rcp);
// 		respVec.add(json);
// 	    }
 	    String response = "{ok}";
 	    System.err.println("[" + clientHostName + "] Returning ranked provider to the client: "+ response + "\n\n");
 	    httpExchange.sendResponseHeaders(200, response.getBytes().length);
 	    OutputStream os = httpExchange.getResponseBody();
 	    os.write(response.getBytes());
 	    os.close();
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
    
    
    /**
     *
     *
     * Convert JsonObject to CloudProvider object
     *
     *
     */
/*     List<CloudProvider> parse(JsonArray array) {
    	List<CloudProvider> cpvec = new Vector<CloudProvider>( );
        for(int i = 0; i < array.size( ); i++) {
	    try {
		JsonObject obj = array.get( i ).getAsJsonObject();
		System.err.println("[" + clientHostName + "] Processing provider "+obj.toString());
		Gson gson = new GsonBuilder().create();
		CloudProvider cp = gson.fromJson(obj, CloudProvider.class);
		cp.setGoodParsed();
		if(!obj.has(CloudProvider.ID)) {
		    cp.setWrongParsed( );
		    cp.setErrorMessage( CloudProvider.ID + " field is missing" );
		}
		if(!obj.has(CloudProvider.NAME)) {
		    cp.setWrongParsed( );
		    cp.setErrorMessage( CloudProvider.NAME + " field is missing" );
		}
		if(!obj.has(CloudProvider.TOTALVCPU)) {
		    cp.setWrongParsed( );
		    cp.setErrorMessage( CloudProvider.TOTALVCPU + " field is missing" );
		}
		if(!obj.has(CloudProvider.TOTALVRAM)) {
		    cp.setWrongParsed( );
		    cp.setErrorMessage( CloudProvider.TOTALVRAM + " field is missing" );
		}
		if(!obj.has(CloudProvider.TOTALVDISK)) {
		    cp.setWrongParsed( );
		    cp.setErrorMessage( CloudProvider.TOTALVDISK + " field is missing" );
		}
		if(!obj.has(CloudProvider.TOTALVEPHDISK)) {
		    cp.setWrongParsed( );
		    cp.setErrorMessage( CloudProvider.TOTALVEPHDISK + " field is missing" );
		}
		if(!obj.has(CloudProvider.INUSEVCPU)) {
		    cp.setWrongParsed( );
		    cp.setErrorMessage( CloudProvider.INUSEVCPU + " field is missing" );
		}
		if(!obj.has(CloudProvider.INUSEVRAM)) {
		    cp.setWrongParsed( );
		    cp.setErrorMessage( CloudProvider.INUSEVRAM + " field is missing" );
		}
		if(!obj.has(CloudProvider.INUSEVDISK)) {
		    cp.setWrongParsed( );
		    cp.setErrorMessage( CloudProvider.INUSEVDISK + " field is missing" );
		}
		if(!obj.has(CloudProvider.INUSEVEPHDISK)) {
		    cp.setWrongParsed( );
		    cp.setErrorMessage( CloudProvider.INUSEVEPHDISK + " field is missing" );
		}
		cpvec.add(cp);
	    } catch(Exception e) {
		System.err.println(e.getMessage());
	    } catch(Throwable t) {
		System.err.println(t.getMessage());
	    }
	}
	return cpvec;
    } */
    
// 
//    /**
//     *
//     * Convert a JSON array "preferences" into a java array of Preference objects
//     *
//     */
//    private Preference[] parsePreferences( JsonArray array ) {
//    
//      Preference[] preferences = new Preference[array.size( )];
//      for(int i = 0; i < array.size( ); i++) {
//        try {
// 	 JsonObject obj = array.get( i ).getAsJsonObject();
// 	 System.err.println("\n[" + clientHostName + "] Processing preference " + obj.toString()+"\n");
// 	 Gson gson = new GsonBuilder().create();
// 	 //System.err.println("obj[" + i + "]=" + obj );
// 	 Preference pref = gson.fromJson(obj, Preference.class);
// 	 System.err.println("Preference={" + pref + "}\n\n" );
//        } catch(Exception e) {
// 	 System.err.println("Exception: " + e.getMessage());
//        } catch(Throwable t) {
// 	 System.err.println("Throwable: " + t.getMessage());
//        }
//      }
//      
//      return preferences;
//      
//    }
   
   /**
    *
    *
    * Convert a JSON array "priority" into a java array of Priority objects
    *
    *
    */
   private Priority[] parsePriorities( JsonArray array ) {
   
     Priority[] priorities = new Priority[array.size( )];
     for(int i = 0; i < array.size( ); i++) {
       try {
 	 JsonObject obj = array.get( i ).getAsJsonObject();
 	 System.err.println("\n[" + clientHostName + "] Processing priority " + obj.toString()+"\n");
 	 Gson gson = new GsonBuilder().create();
	 priorities[i] = gson.fromJson(obj, Priority.class);
       } catch(Exception e) {
	 System.err.println("Exception: " + e.getMessage());
       } catch(Throwable t) {
	 System.err.println("Throwable: " + t.getMessage());
       }
     }
     
     return priorities;
     
   }
   
   /**
    *
    *
    * Extract (and convert to a Java array) the services from a SLA json element
    *
    *
    */
   private Service[] parseService( JsonObject sla ) {
     JsonArray Services = sla.get("services").getAsJsonArray( );
     Service[] services = new Service[Services.size()];
     for(int i = 0; i < Services.size( ); i++) {
       try {
         JsonObject obj = Services.get( i ).getAsJsonObject();
         System.err.println("\n[" + clientHostName + "] Processing Service " + obj.toString()+"\n");
	 Target[] targets = parseTarget( obj ); 
	 services[i] = new Service( obj.get("service_id").getAsString(), obj.get("type").getAsString( ) , targets);
	 //System.err.println("Service[" + i + "]=" + services[i]);
       } catch(Exception e) {
	 System.err.println("Exception: " + e.getMessage());
       } catch(Throwable t) {
	 System.err.println("Throwable: " + t.getMessage());
       }
     }
     return services;
   }
   
   /**
    *
    *
    * Extract (and convert to a Java array) the targets from a service json element
    *
    *
    */
   private Target[] parseTarget( JsonObject service ) {
     JsonArray Targets = service.get("targets").getAsJsonArray( );
     Target[] targets = new Target[Targets.size( )];
     Gson gson = new GsonBuilder().create();
     for(int i = 0; i < Targets.size( ); i++) {
       targets[i] = gson.fromJson(Targets.get(i).getAsJsonObject( ), Target.class);

     }
     return targets;
   }
}
