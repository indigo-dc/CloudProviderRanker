package org.indigo.cloudproviderruleengine;

import it.reply.monitoringpillar.domain.dsl.monitoring.pillar.wrapper.paas.PaaSMetric;
import com.google.gson.*;
import java.util.ArrayList;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class PaaSMetricRanked extends PaaSMetric {
	private float rank = (float)0.0;
	
	public static String normalization_file = null;
	
	public float getRank( ) { return rank; }
	public void addToRank( float f ) { rank += f; }
	
	public static ArrayList<PaaSMetricRanked> fromJsonArray( JsonArray array ) {
	  ArrayList<PaaSMetricRanked> paaSMetricRanked = new ArrayList<PaaSMetricRanked>();
	  for(int i = 0; i < array.size( ); ++i ) {
	    paaSMetricRanked.add( (PaaSMetricRanked)(new GsonBuilder().create()).fromJson(array.get( i ).getAsJsonObject(), PaaSMetricRanked.class) );
	  }
	  if(normalization_file != null) {
	    InputStream is = null;
    	    String Line = "";
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
	      return new PaaSMetricNormalizations(1,1,1,1,1);
   	    } catch(Throwable t) {
              return new PaaSMetricNormalizations(1,1,1,1,1);  
   	    }
	  }
	  Gson gson = new Gson();
   	  //System.err.println("SlaPriorities - Line="+Line);
    	  JsonElement E = gson.fromJson(Line, JsonElement.class);
    	  //return (SlaNormalizations)gson.fromJson(E.getAsJsonObject( ), SlaNormalizations.class);
	  return paaSMetricRanked;
	}
	
	@Override	
 	public String toString( ) {
 	  return ToStringBuilder.reflectionToString(this);
 	}
	
	
}
