package org.indigo.cloudproviderranker.test;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.indigo.cloudproviderranker.PaaSMetricRanked;
import org.indigo.cloudproviderranker.PaaSMetricNormalization;
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
      metricNames.add("occi create vm availability");
      metricNames.add("occi createvm Result");
      metricNames.add("occi delete vm Availability");
      metricNames.add("occi deletevm Response Time");
      metricNames.add("occi deletevm Result");
      metricNames.add("general occi api Availability");
      metricNames.add("general occi api Response Time");
      metricNames.add("occi inspectvm Result");
      metricNames.add("general occi api Result");
      metricNames.add("occi inspect vm availability");
      metricNames.add("occi inspectvm Response Time");
      PaaSMetricNormalization.normalization_file = "paasmetric_normalization.json";
      JsonParser parser = new JsonParser();
      p.setClientIp( "0.0.0.0" );
      String jsonTest = "";
      HashMap<String, ArrayList<PaaSMetricRanked>> result = null;
      for(String type : metricNames ) {
	  jsonTest = "[{\"provider\": \"provider-RECAS-BARI\",\"metrics\": [{\"metricName\": \"" + type + "\",\"metricKey\":\"Cloud_Providers.provider-RECAS-BARI..occi Create VM availability\",\"metricValue\": 1.0,\"metricTime\": \"Instant null because no metrics were returned in the last 24hs\",\"metricUnit\": \"bit\",\"paasThresholds\": [],\"historyClocks\": [],\"historyValues\": []}, {\"metricName\": \"occi CreateVM Response Time\",\"metricKey\":\"Cloud_Providers.provider-RECAS-BARI..occi CreateVM Response Time\",\"metricValue\": 10.0,\"metricTime\":\"Instant null because no metrics were returned in the last 24hs\",\"metricUnit\": \"ms\",\"paasThresholds\": [],\"historyClocks\": [],\"historyValues\": []}]}]";
	  PaaSMetricNormalization.normalization_file = "paasmetric_normalization.json";
	  
	  JsonElement jsonElement = parser.parse( jsonTest );
	  JsonArray array = jsonElement.getAsJsonArray( );
	  //JsonObject 
	  result = (new PaaSMetricRanked()).fromJsonArray( array );
      }
      //String checkString = "rank=0.0,providerName=,clientHostName=,metricName=occi InspectVM Response Time,metricKey=Cloud_Providers.provider-RECAS-BARI..occi Create VM availability,metricValue=1.0,metricTime=Instant null because no metrics were returned in the last 24hs,metricUnit=bit,paasThresholds=[],historyClocks=[],historyValues=[]], org.indigo.cloudproviderranker.PaaSMetricRanked@64729b1e[rank=0.0,providerName=,clientHostName=,metricName=occi CreateVM Response Time,metricKey=Cloud_Providers.provider-RECAS-BARI..occi CreateVM Response Time,metricValue=10.0,metricTime=Instant null because no metrics were returned in the last 24hs,metricUnit=ms,paasThresholds=[],historyClocks=[],historyValues=[]";
      //System.out.println("\n\nresult.get="+result.get("provider-RECAS-BARI").toString()+"\n\n");
      
      //assertTrue( result.get("provider-RECAS-BARI").toString().contains(checkString) );
      
    }
  }
}
