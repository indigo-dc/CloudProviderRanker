package org.indigo.cloudproviderranker;

import it.reply.monitoringpillar.domain.dsl.monitoring.pillar.wrapper.paas.PaaSMetric;
import com.google.gson.JsonArray;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class PaaSMetricRanked extends PaaSMetric {
  private float  rank = 0.0f;
  private String providerName = "";
  private String clientHostName = "";
    
  public void setClientIP( String ip ) {
    clientHostName = ip;
  }
	
  public float getRank( ) {
    return rank;
  }
	
  public void addToRank( float f ) { rank += f; }

  public HashMap<String, ArrayList<PaaSMetricRanked>> fromJsonArray( JsonArray array ) {
 	  
    PaaSMetricNormalization paaSMetricNormalization = new PaaSMetricNormalization(true);
	
    HashMap<String, ArrayList<PaaSMetricRanked>> providerMonitor =
	new HashMap<String, ArrayList<PaaSMetricRanked>>( );
    for( int i = 0; i< array.size( ); ++i ) { // loop over the array monitoring[]
      JsonObject obj = array.get(i).getAsJsonObject( );
      String providerName = obj.get( "provider" ).getAsString( );
      JsonArray metricsArray = obj.get( "metrics" ).getAsJsonArray( );
      for(int j = 0; j < metricsArray.size( ); ++j ) { // loop over the array metrics[]
        JsonObject currentMetricJsonObject = metricsArray.get( j ).getAsJsonObject( );
	
	PaaSMetricRanked paaSMetricRanked =
	    (PaaSMetricRanked)(new
			       GsonBuilder( ).create()).fromJson(currentMetricJsonObject,
								PaaSMetricRanked.class);
	
	if(paaSMetricRanked.getMetricName( ).compareTo( "OCCI Create VM availability" )==0) {
	  float val = paaSMetricRanked.getMetricValue( ) *
	      paaSMetricNormalization.OCCI_Create_VM_availability;
	  paaSMetricRanked.addToRank( val );
	}
	
	if(paaSMetricRanked.getMetricName( ).compareTo( "OCCI CreateVM Response Time" )==0) {
	  float val = paaSMetricRanked.getMetricValue( ) *
	      paaSMetricNormalization.OCCI_CreateVM_Response_Time;
	  paaSMetricRanked.addToRank( 0 - val );
	}
	
	if(paaSMetricRanked.getMetricName( ).compareTo( "OCCI CreateVM Result" )==0) {
	  float val =  paaSMetricRanked.getMetricValue( ) * paaSMetricNormalization.OCCI_CreateVM_Result;
	  paaSMetricRanked.addToRank( val );
	}
	
	if(paaSMetricRanked.getMetricName( ).compareTo( "OCCI Delete VM Availability" )==0) {
	  float val = paaSMetricRanked.getMetricValue( ) *
	      paaSMetricNormalization.OCCI_Delete_VM_Availability;
	  paaSMetricRanked.addToRank( val );
	}
	    
	if(paaSMetricRanked.getMetricName( ).compareTo( "OCCI DeleteVM Response Time" )==0) {
	  float val = paaSMetricRanked.getMetricValue( ) *
	      paaSMetricNormalization.OCCI_DeleteVM_Response_Time;
	  paaSMetricRanked.addToRank( 0 - val );
	}
	
	if(paaSMetricRanked.getMetricName( ).compareTo( "OCCI DeleteVM Result" )==0) {
	  float val = paaSMetricRanked.getMetricValue( ) *
	      paaSMetricNormalization.OCCI_DeleteVM_Result;
	  paaSMetricRanked.addToRank( val );
	}
	
	if(paaSMetricRanked.getMetricName( ).compareTo( "General OCCI API Availability" )==0) {
	  float val = paaSMetricRanked.getMetricValue( ) *
	      paaSMetricNormalization.General_OCCI_API_Availability;
	  paaSMetricRanked.addToRank( val );
	}
	    
	if(paaSMetricRanked.getMetricName( ).compareTo( "General OCCI API Response Time" )==0) {
	  float val = paaSMetricRanked.getMetricValue( ) *
	      paaSMetricNormalization.General_OCCI_API_Response_Time;
	  paaSMetricRanked.addToRank( 0 - val );
	}
	    
	if(paaSMetricRanked.getMetricName( ).compareTo( "General OCCI API Result" )==0) {
	  float val = paaSMetricRanked.getMetricValue( ) *
	      paaSMetricNormalization.General_OCCI_API_Result;
	  paaSMetricRanked.addToRank( val );
	}
	
	if(paaSMetricRanked.getMetricName( ).compareTo( "OCCI Inspect VM availability" )==0) {
	  float val = paaSMetricRanked.getMetricValue( ) *
	      paaSMetricNormalization.OCCI_Inspect_VM_availability;
	  paaSMetricRanked.addToRank( val );
	}
	
	if(paaSMetricRanked.getMetricName( ).compareTo( "OCCI InspectVM Response Time" )==0) {
	  float val = paaSMetricRanked.getMetricValue( ) *
	      paaSMetricNormalization.OCCI_InspectVM_Response_Time;
	  paaSMetricRanked.addToRank( 0 - val );
	}
	
	if(paaSMetricRanked.getMetricName( ).compareTo( "OCCI InspectVM Result" )==0) {
	  float val = paaSMetricRanked.getMetricValue( ) *
	      paaSMetricNormalization.OCCI_InspectVM_Result;
	  paaSMetricRanked.addToRank( val );
	}
	
	if(providerMonitor.containsKey(providerName)) {
	  providerMonitor.get(providerName).add( paaSMetricRanked );
	} else {
	  ArrayList<PaaSMetricRanked> _array = new ArrayList<PaaSMetricRanked>( );
	  _array.add( paaSMetricRanked );
	  providerMonitor.put( providerName, _array );
	}
	
      }
    }
    
    return providerMonitor;
  }

  @Override	
  public String toString( ) {
    return ToStringBuilder.reflectionToString(this);
  }	
}
