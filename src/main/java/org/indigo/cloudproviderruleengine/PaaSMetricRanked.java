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
	  return paaSMetricRanked;
	}
	
	@Override	
 	public String toString( ) {
 	  return ToStringBuilder.reflectionToString(this);
 	}
}
