package org.indigo.cloudproviderruleengine.test;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.indigo.cloudproviderruleengine.PaaSMetricRanked;
import com.google.gson.*;
import java.util.HashMap;
import java.util.ArrayList;

public class PaaSMetricRankedTest {
  @Test
  public void test( ) {
    PaaSMetricRanked p = new PaaSMetricRanked( );
    if(null!=p) {
      assertTrue( p.getRank( ) == 0 );
      p.addToRank( 0.7f );
      assertTrue( p.getRank( ) == 0.7f );
      ArrayList<String> metricNames = new ArrayList<String>();
      metricNames.add("OCCI Create VM availability");
      metricNames.add("OCCI CreateVM Result");
      metricNames.add("OCCI Delete VM Availability");
      metricNames.add("OCCI DeleteVM Response Time");
      metricNames.add("OCCI DeleteVM Result");
      metricNames.add("General OCCI API Availability");
      metricNames.add("General OCCI API Response Time");
      metricNames.add("OCCI InspectVM Result");
      metricNames.add("General OCCI API Result");
      metricNames.add("OCCI Inspect VM availability");
      metricNames.add("OCCI InspectVM Response Time");
      PaaSMetricRanked.normalization_file = "paasmetric_normalization.json";
      JsonParser parser = new JsonParser();
      String jsonTest = "";
      HashMap<String, ArrayList<PaaSMetricRanked>> result = null;
      for(String type : metricNames ) {
	  jsonTest = "[{\"provider\": \"provider-RECAS-BARI\",\"metrics\": [{\"metricName\": \"" + type + "\",\"metricKey\":\"Cloud_Providers.provider-RECAS-BARI..OCCI Create VM availability\",\"metricValue\": 1.0,\"metricTime\": \"Instant null because no metrics were returned in the last 24hs\",\"metricUnit\": \"bit\",\"paasThresholds\": [],\"historyClocks\": [],\"historyValues\": []}, {\"metricName\": \"OCCI CreateVM Response Time\",\"metricKey\":\"Cloud_Providers.provider-RECAS-BARI..OCCI CreateVM Response Time\",\"metricValue\": 10.0,\"metricTime\":\"Instant null because no metrics were returned in the last 24hs\",\"metricUnit\": \"ms\",\"paasThresholds\": [],\"historyClocks\": [],\"historyValues\": []}]}]";
	  //PaaSMetricRanked.normalization_file = "paasmetric_normalization.json";
	  
	  JsonElement jsonElement = parser.parse( jsonTest );
	  JsonArray array = jsonElement.getAsJsonArray( );
	  //JsonObject 
	  result = PaaSMetricRanked.fromJsonArray( array );
      }
      
    }
  }

}
