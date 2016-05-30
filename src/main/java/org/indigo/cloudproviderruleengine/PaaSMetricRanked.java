package org.indigo.cloudproviderruleengine;

import it.reply.monitoringpillar.domain.dsl.monitoring.pillar.wrapper.paas.PaaSMetric;
import com.google.gson.*;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class PaaSMetricRanked extends PaaSMetric {
	private float rank = (float)0.0;
	
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
	public static ArrayList<PaaSMetricRanked> fromJsonArray( JsonArray array ) {
	  //System.err.println( "Array size="+array.size( ) );
	  ArrayList<PaaSMetricRanked> paaSMetricRankedArray = new ArrayList<PaaSMetricRanked>();
	  String Line = "";
	  PaaSMetricNormalization paaSMetricNormalization = null;
	  if(normalization_file != null) {
	    InputStream is = null;
    	    //String Line = "";
	    
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
	  System.err.println("Line-normaliz="+Line);
   	  JsonElement E = gson.fromJson(Line, JsonElement.class);
	  
	  paaSMetricNormalization = (PaaSMetricNormalization)gson.fromJson(E.getAsJsonObject( ), PaaSMetricNormalization.class);
	  
	  //System.err.println("paasNormlization="+paaSMetricNormalization);
	  
	  for(int i = 0; i < array.size( ); ++i ) {
	    PaaSMetricRanked paaSMetricRanked = (PaaSMetricRanked)(new GsonBuilder().create()).fromJson(array.get( i ).getAsJsonObject(),PaaSMetricRanked.class);
	    if(paaSMetricRanked.getMetricName().compareTo("OCCI Create VM availability")==0)
	      paaSMetricRanked.setMetricValue( paaSMetricRanked.getMetricValue() * paaSMetricNormalization.OCCI_Create_VM_availability );
	    
	    if(paaSMetricRanked.getMetricName().compareTo("OCCI CreateVM Response Time")==0)
	      paaSMetricRanked.setMetricValue( paaSMetricRanked.getMetricValue() * paaSMetricNormalization.OCCI_CreateVM_Response_Time );
	    
	    if(paaSMetricRanked.getMetricName().compareTo("OCCI CreateVM Result")==0)
	      paaSMetricRanked.setMetricValue( paaSMetricRanked.getMetricValue() * paaSMetricNormalization.OCCI_CreateVM_Result );
	    
	    if(paaSMetricRanked.getMetricName().compareTo("OCCI Delete VM Availability")==0)
	      paaSMetricRanked.setMetricValue( paaSMetricRanked.getMetricValue() * paaSMetricNormalization.OCCI_Delete_VM_Availability );
	    
	    if(paaSMetricRanked.getMetricName().compareTo("OCCI DeleteVM Response Time")==0)
	      paaSMetricRanked.setMetricValue( paaSMetricRanked.getMetricValue() * paaSMetricNormalization.OCCI_DeleteVM_Response_Time );
	    
	    if(paaSMetricRanked.getMetricName().compareTo("OCCI DeleteVM Result")==0)
	      paaSMetricRanked.setMetricValue( paaSMetricRanked.getMetricValue() * paaSMetricNormalization.OCCI_DeleteVM_Result );
	    
	    if(paaSMetricRanked.getMetricName().compareTo("General OCCI API Availability")==0)
	      paaSMetricRanked.setMetricValue( paaSMetricRanked.getMetricValue() * paaSMetricNormalization.General_OCCI_API_Availability );
	    
	    if(paaSMetricRanked.getMetricName().compareTo("General OCCI API Response Time")==0)
	      paaSMetricRanked.setMetricValue( paaSMetricRanked.getMetricValue() * paaSMetricNormalization.General_OCCI_API_Response_Time );
	    
	    if(paaSMetricRanked.getMetricName().compareTo("General OCCI API Result")==0)
	      paaSMetricRanked.setMetricValue( paaSMetricRanked.getMetricValue() * paaSMetricNormalization.General_OCCI_API_Result );
	    
	    if(paaSMetricRanked.getMetricName().compareTo("OCCI Inspect VM availability")==0)
	      paaSMetricRanked.setMetricValue( paaSMetricRanked.getMetricValue() * paaSMetricNormalization.OCCI_Inspect_VM_availability );
	    
	    if(paaSMetricRanked.getMetricName().compareTo("OCCI InspectVM Response Time")==0)
	      paaSMetricRanked.setMetricValue( paaSMetricRanked.getMetricValue() * paaSMetricNormalization.OCCI_InspectVM_Response_Time );
	    
	    if(paaSMetricRanked.getMetricName().compareTo("OCCI InspectVM Result")==0)
	      paaSMetricRanked.setMetricValue( paaSMetricRanked.getMetricValue() * paaSMetricNormalization.OCCI_InspectVM_Result );
	    
	    System.err.println("PaaSMetricRanked=" +paaSMetricRanked);
	    
	    paaSMetricRankedArray.add( paaSMetricRanked );
	  }
	  
    	  return paaSMetricRankedArray;
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
