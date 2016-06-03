package org.indigo.cloudproviderruleengine;

import it.reply.monitoringpillar.domain.dsl.monitoring.pillar.wrapper.paas.PaaSMetric;
import com.google.gson.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class PaaSMetricRanked extends PaaSMetric {
	private float  rank = (float)0.0;
	private String providerName = "";
	
	/**
	 *
	 *
	 */
	public static String normalization_file = null;
	
	/**
	 *
	 *
	 */
	public float getRank( ) { return rank; }
	
	/**
	 *
	 *
	 */
	public void addToRank( float f ) { rank += f; }
	
	/**
	 *
	 *
	 */
	public static HashMap<String, ArrayList<PaaSMetricRanked>> fromJsonArray( JsonArray array ) {
 	  String Line = "";
	  PaaSMetricNormalization paaSMetricNormalization = null;
	  if(normalization_file != null) {
	    InputStream is = null;
    	    
    	    try {
      	      is = new FileInputStream(new File(normalization_file));
    
    
	      InputStreamReader inputReader = new InputStreamReader(is);
     	      BufferedReader buffReader     = new BufferedReader(inputReader);
    	      //String Line = "";
    	      String line = "";
    	      while( (line = buffReader.readLine()) != null) {
    	        Line += line;
    	      }
   	    } catch(Exception e) {
	      paaSMetricNormalization = new PaaSMetricNormalization(1,(float)0.001,1,1,(float)0.001,1,1,(float)0.001,1,1,(float)0.001,1);
   	    } catch(Throwable t) {
              paaSMetricNormalization = new PaaSMetricNormalization(1,(float)0.001,1,1,(float)0.001,1,1,(float)0.001,1,1,(float)0.001,1);
   	    }
	  }
	  Gson gson = new Gson();
   	  JsonElement E = gson.fromJson(Line, JsonElement.class);
	  
	  paaSMetricNormalization = (PaaSMetricNormalization)gson.fromJson(E.getAsJsonObject( ), PaaSMetricNormalization.class);
	  
	  HashMap<String, ArrayList<PaaSMetricRanked>> providerMonitor = new HashMap<String, ArrayList<PaaSMetricRanked>>();
	  for( int i = 0; i< array.size(); ++i ) { // loop over the array monitoring[]
	    JsonObject obj = array.get(i).getAsJsonObject( );
	    String providerName = obj.get("provider").getAsString();
	    JsonArray metricsArray = obj.get("metrics").getAsJsonArray(); 
	    for(int j = 0; j < metricsArray.size(); ++j ) { // loop over the array metrics[]
	      
	      JsonObject currentMetricJsonObject = metricsArray.get( j ).getAsJsonObject();
	      
	      //System.err.println("METRIC="+currentMetricJsonObject.getAsString( ) );
	      
	      PaaSMetricRanked paaSMetricRanked = (PaaSMetricRanked)(new GsonBuilder().create()).fromJson(currentMetricJsonObject, PaaSMetricRanked.class);
	      
	      //System.err.println(" ****  PaaSMetricRanked=" +paaSMetricRanked);
	      
	      if(paaSMetricRanked.getMetricName().compareTo("OCCI Create VM availability")==0) {
	        //paaSMetricRanked.setMetricValue( paaSMetricRanked.getMetricValue() * paaSMetricNormalization.OCCI_Create_VM_availability );
	        paaSMetricRanked.addToRank( paaSMetricRanked.getMetricValue() * paaSMetricNormalization.OCCI_Create_VM_availability );
	      }
	      
	      if(paaSMetricRanked.getMetricName().compareTo("OCCI CreateVM Response Time")==0) {
	        //paaSMetricRanked.setMetricValue( paaSMetricRanked.getMetricValue() * paaSMetricNormalization.OCCI_CreateVM_Response_Time );
	        paaSMetricRanked.addToRank( 0 - paaSMetricRanked.getMetricValue() * paaSMetricNormalization.OCCI_CreateVM_Response_Time );
	      }
	      
	      if(paaSMetricRanked.getMetricName().compareTo("OCCI CreateVM Result")==0) {
	        //paaSMetricRanked.setMetricValue( paaSMetricRanked.getMetricValue() * paaSMetricNormalization.OCCI_CreateVM_Result );
	        paaSMetricRanked.addToRank( paaSMetricRanked.getMetricValue() * paaSMetricNormalization.OCCI_CreateVM_Result );
	      }
	    
	      if(paaSMetricRanked.getMetricName().compareTo("OCCI Delete VM Availability")==0) {
	        //paaSMetricRanked.setMetricValue( paaSMetricRanked.getMetricValue() * paaSMetricNormalization.OCCI_Delete_VM_Availability );
	        paaSMetricRanked.addToRank( paaSMetricRanked.getMetricValue() * paaSMetricNormalization.OCCI_Delete_VM_Availability );
	      }
	    
	      if(paaSMetricRanked.getMetricName().compareTo("OCCI DeleteVM Response Time")==0) {
	        //paaSMetricRanked.setMetricValue( paaSMetricRanked.getMetricValue() * paaSMetricNormalization.OCCI_DeleteVM_Response_Time );
	        paaSMetricRanked.addToRank( 0 - paaSMetricRanked.getMetricValue() * paaSMetricNormalization.OCCI_DeleteVM_Response_Time );
	      }
	    
	      if(paaSMetricRanked.getMetricName().compareTo("OCCI DeleteVM Result")==0) {
	        //paaSMetricRanked.setMetricValue( paaSMetricRanked.getMetricValue() * paaSMetricNormalization.OCCI_DeleteVM_Result );
	        paaSMetricRanked.addToRank( paaSMetricRanked.getMetricValue() * paaSMetricNormalization.OCCI_DeleteVM_Result );
	      }
	    
	      if(paaSMetricRanked.getMetricName().compareTo("General OCCI API Availability")==0) {
	        //paaSMetricRanked.setMetricValue( paaSMetricRanked.getMetricValue() * paaSMetricNormalization.General_OCCI_API_Availability );
	        paaSMetricRanked.addToRank( paaSMetricRanked.getMetricValue() * paaSMetricNormalization.General_OCCI_API_Availability );
	      }
	    
	      if(paaSMetricRanked.getMetricName().compareTo("General OCCI API Response Time")==0) {
	        //paaSMetricRanked.setMetricValue( paaSMetricRanked.getMetricValue() * paaSMetricNormalization.General_OCCI_API_Response_Time );
	        paaSMetricRanked.addToRank( 0 - paaSMetricRanked.getMetricValue() * paaSMetricNormalization.General_OCCI_API_Response_Time );
	      }
	    
	      if(paaSMetricRanked.getMetricName().compareTo("General OCCI API Result")==0) {
	        //paaSMetricRanked.setMetricValue( paaSMetricRanked.getMetricValue() * paaSMetricNormalization.General_OCCI_API_Result );
	        paaSMetricRanked.addToRank( paaSMetricRanked.getMetricValue() * paaSMetricNormalization.General_OCCI_API_Result );
	      }
	    
	      if(paaSMetricRanked.getMetricName().compareTo("OCCI Inspect VM availability")==0) {
	        //paaSMetricRanked.setMetricValue( paaSMetricRanked.getMetricValue() * paaSMetricNormalization.OCCI_Inspect_VM_availability );
	        paaSMetricRanked.addToRank( paaSMetricRanked.getMetricValue() * paaSMetricNormalization.OCCI_Inspect_VM_availability );
	      }
	      
	      if(paaSMetricRanked.getMetricName().compareTo("OCCI InspectVM Response Time")==0) {
	        //paaSMetricRanked.setMetricValue( paaSMetricRanked.getMetricValue() * paaSMetricNormalization.OCCI_InspectVM_Response_Time );
	        paaSMetricRanked.addToRank( 0 - paaSMetricRanked.getMetricValue() * paaSMetricNormalization.OCCI_InspectVM_Response_Time );
	      }
	    
	      if(paaSMetricRanked.getMetricName().compareTo("OCCI InspectVM Result")==0) {
	        //paaSMetricRanked.setMetricValue( paaSMetricRanked.getMetricValue() * paaSMetricNormalization.OCCI_InspectVM_Result );
	        paaSMetricRanked.addToRank( paaSMetricRanked.getMetricValue() * paaSMetricNormalization.OCCI_InspectVM_Result );
	      }
	    
	      //System.err.println(" ****  PaaSMetricRanked=" +paaSMetricRanked);
	    
	      //paaSMetricRankedArray.add( paaSMetricRanked );
	    
	      
	      if(providerMonitor.containsKey(providerName)) {
	        providerMonitor.get(providerName).add( paaSMetricRanked );
	      } else {
	        ArrayList<PaaSMetricRanked> _array = new ArrayList<PaaSMetricRanked>();
		_array.add( paaSMetricRanked );
	      	providerMonitor.put( providerName, _array );
	      }
	      
	    }
	  }

	  return providerMonitor;
	}
	
	/**
	 *
	 *
	 */
	@Override	
 	public String toString( ) {
 	  return ToStringBuilder.reflectionToString(this);
 	}
	
	
}
