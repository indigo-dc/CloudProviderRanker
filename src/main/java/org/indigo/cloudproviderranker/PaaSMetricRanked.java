package org.indigo.cloudproviderranker;

import it.reply.monitoringpillar.domain.dsl.monitoring.pillar.wrapper.paas.PaaSMetric;
import com.google.gson.JsonArray;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Doc TODO.
 */
public class PaaSMetricRanked extends PaaSMetric {
  /**
   * Doc TODO.
   */
  private float  rank = 0.0f;
  /**
   * Doc TODO.
   */
  private String providerName = "";
  /**
   * Doc TODO.
   */
  private String clientHostName = "";
  /**
   * Doc TODO.
   */
  public final void setClientIP(final String ip) {
    clientHostName = ip;
  }
  /**
   * Doc TODO.
   */
  public final float getRank() {
    return rank;
  }
  /**
   * Doc TODO.
   */
  public final void addToRank(final float f) {
    rank += f;
  }
  /**
   * Doc TODO.
   */
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
	      * paaSMetricNormalization.occiCreatevmAvailability;
	  paaSMetricRanked.addToRank(val);
	}

	if (paaSMetricRanked.getMetricName().compareTo("occi createvm Response Time")  ==  0) {
	  float val = paaSMetricRanked.getMetricValue()
	      * paaSMetricNormalization.occiCreatevmResponseTime;
	  paaSMetricRanked.addToRank(0 - val);
	}

	if (paaSMetricRanked.getMetricName().compareTo("occi createvm Result")  ==  0) {
	  float val =  paaSMetricRanked.getMetricValue()
	      * paaSMetricNormalization.occiCreatevmResult;
	  paaSMetricRanked.addToRank(val);
	}

	if (paaSMetricRanked.getMetricName().compareTo("occi delete vm Availability")  ==  0) {
	  float val = paaSMetricRanked.getMetricValue()
	      * paaSMetricNormalization.occiDeletevmAvailability;
	  paaSMetricRanked.addToRank(val);
	}

	if (paaSMetricRanked.getMetricName().compareTo("occi deletevm Response Time")  ==  0) {
	  float val = paaSMetricRanked.getMetricValue()
	      * paaSMetricNormalization.occiDeletevmResponseTime;
	  paaSMetricRanked.addToRank(0 - val);
	}

	if (paaSMetricRanked.getMetricName().compareTo("occi deletevm Result")  ==  0) {
	  float val = paaSMetricRanked.getMetricValue()
	      * paaSMetricNormalization.occiDeletevmResult;
	  paaSMetricRanked.addToRank(val);
	}

	if (paaSMetricRanked.getMetricName().compareTo("general occi api Availability")  ==  0) {
	  float val = paaSMetricRanked.getMetricValue()
	      * paaSMetricNormalization.generalOcciApiAvailability;
	  paaSMetricRanked.addToRank(val);
	}

	if (paaSMetricRanked.getMetricName().compareTo("general occi api Response Time")  ==  0) {
	  float val = paaSMetricRanked.getMetricValue()
	      * paaSMetricNormalization.generalOcciApiResponseTime;
	  paaSMetricRanked.addToRank(0 - val);
	}

	if (paaSMetricRanked.getMetricName().compareTo("general occi api Result")  ==  0) {
	  float val = paaSMetricRanked.getMetricValue()
	      * paaSMetricNormalization.generalOcciApiResult;
	  paaSMetricRanked.addToRank(val);
	}

	if (paaSMetricRanked.getMetricName().compareTo("occi inspect vm availability")  ==  0) {
	  float val = paaSMetricRanked.getMetricValue()
	      * paaSMetricNormalization.occiInspectVmAvailability;
	  paaSMetricRanked.addToRank(val);
	}

	if (paaSMetricRanked.getMetricName().compareTo("occi inspectvm Response Time")  ==  0) {
	  float val = paaSMetricRanked.getMetricValue()
	      * paaSMetricNormalization.occiInspectVmResponseTime;
	  paaSMetricRanked.addToRank(0 - val);
	}

	if (paaSMetricRanked.getMetricName().compareTo("occi inspectvm Result")  ==  0) {
	  float val = paaSMetricRanked.getMetricValue()
	      * paaSMetricNormalization.occiInspectVmResult;
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
  /**
   * Doc TODO.
   */
  @Override
  public final String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
