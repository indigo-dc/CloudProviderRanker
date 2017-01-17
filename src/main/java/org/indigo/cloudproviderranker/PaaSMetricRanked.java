package org.indigo.cloudproviderranker;

import it.reply.monitoringpillar.domain.dsl.monitoring.pillar.wrapper.paas.PaaSMetric;
import com.google.gson.JsonArray;
//import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class PaaSMetricRanked extends PaaSMetric {
  private float  rank = 0.0f;
  private String providerName = "";
  private String clientHostName = "";

  public final void setClientIP(final String ip) {
    clientHostName = ip;
  }

  public final float getRank() {
    return rank;
  }

  public final void addToRank(final float f) {
    rank += f;
  }

  public final HashMap<String,  ArrayList<PaaSMetricRanked>> fromJsonArray(final JsonArray array) {

    PaaSMetricNormalization paaSMetricNormalization = new PaaSMetricNormalization(true);

    HashMap<String,  ArrayList<PaaSMetricRanked>> providerMonitor =
        new HashMap<String,  ArrayList<PaaSMetricRanked>>();
    for (int i = 0; i < array.size(); ++i) { // loop over the array monitoring[]
      JsonObject obj = array.get(i).getAsJsonObject();
      String providerName = obj.get("provider").getAsString();
      JsonArray metricsArray = obj.get("metrics").getAsJsonArray();
      for (int j = 0; j < metricsArray.size(); ++j) { // loop over the array metrics[]
        JsonObject currentMetricJsonObject = metricsArray.get(j).getAsJsonObject();

        PaaSMetricRanked paaSMetricRanked =
	    (PaaSMetricRanked) (new
			       GsonBuilder().create()).fromJson(currentMetricJsonObject,
								PaaSMetricRanked.class);

	if (paaSMetricRanked.getMetricName().compareTo("occi create vm availability")  ==  0) {
	  float val = paaSMetricRanked.getMetricValue()
	      * paaSMetricNormalization.occi_create_vm_availability;
	  paaSMetricRanked.addToRank(val);
	}

	if (paaSMetricRanked.getMetricName().compareTo("occi createvm Response Time")  ==  0) {
	  float val = paaSMetricRanked.getMetricValue()
	      * paaSMetricNormalization.occi_createvm_Response_Time;
	  paaSMetricRanked.addToRank(0 - val);
	}

	if (paaSMetricRanked.getMetricName().compareTo("occi createvm Result")  ==  0) {
	  float val =  paaSMetricRanked.getMetricValue()
	      * paaSMetricNormalization.occi_createvm_Result;
	  paaSMetricRanked.addToRank(val);
	}

	if (paaSMetricRanked.getMetricName().compareTo("occi delete vm Availability")  ==  0) {
	  float val = paaSMetricRanked.getMetricValue()
	      * paaSMetricNormalization.occi_delete_vm_Availability;
	  paaSMetricRanked.addToRank(val);
	}

	if (paaSMetricRanked.getMetricName().compareTo("occi deletevm Response Time")  ==  0) {
	  float val = paaSMetricRanked.getMetricValue()
	      * paaSMetricNormalization.occi_deletevm_Response_Time;
	  paaSMetricRanked.addToRank(0 - val);
	}

	if (paaSMetricRanked.getMetricName().compareTo("occi deletevm Result")  ==  0) {
	  float val = paaSMetricRanked.getMetricValue()
	      * paaSMetricNormalization.occi_deletevm_Result;
	  paaSMetricRanked.addToRank(val);
	}

	if (paaSMetricRanked.getMetricName().compareTo("general occi api Availability")  ==  0) {
	  float val = paaSMetricRanked.getMetricValue()
	      * paaSMetricNormalization.general_occi_api_Availability;
	  paaSMetricRanked.addToRank(val);
	}

	if (paaSMetricRanked.getMetricName().compareTo("general occi api Response Time")  ==  0) {
	  float val = paaSMetricRanked.getMetricValue()
	      * paaSMetricNormalization.general_occi_api_Response_Time;
	  paaSMetricRanked.addToRank(0 - val);
	}

	if (paaSMetricRanked.getMetricName().compareTo("general occi api Result")  ==  0) {
	  float val = paaSMetricRanked.getMetricValue()
	      * paaSMetricNormalization.general_occi_api_Result;
	  paaSMetricRanked.addToRank(val);
	}

	if (paaSMetricRanked.getMetricName().compareTo("occi inspect vm availability")  ==  0) {
	  float val = paaSMetricRanked.getMetricValue()
	      * paaSMetricNormalization.occi_inspect_vm_availability;
	  paaSMetricRanked.addToRank(val);
	}

	if (paaSMetricRanked.getMetricName().compareTo("occi inspectvm Response Time")  ==  0) {
	  float val = paaSMetricRanked.getMetricValue()
	      * paaSMetricNormalization.occi_inspectvm_Response_Time;
	  paaSMetricRanked.addToRank(0 - val);
	}

	if (paaSMetricRanked.getMetricName().compareTo("occi inspectvm Result")  ==  0) {
	  float val = paaSMetricRanked.getMetricValue()
	      * paaSMetricNormalization.occi_inspectvm_Result;
	  paaSMetricRanked.addToRank(val);
	}

	if (providerMonitor.containsKey(providerName)) {
	  providerMonitor.get(providerName).add(paaSMetricRanked);
	} else {
	  ArrayList<PaaSMetricRanked> arrayTmp = new ArrayList<PaaSMetricRanked>();
	  arrayTmp.add(paaSMetricRanked);
	  providerMonitor.put(providerName,  arrayTmp);
	}

      }
    }

    return providerMonitor;
  }

  @Override
  public final String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
