package org.indigo.cloudproviderranker;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import it.reply.monitoringpillar.domain.dsl.monitoring.pillar.wrapper.paas.PaaSMetric;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.HashMap;

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
  public final void setClientIp(final String ip) {
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
  public final void addToRank(final float rankValue) {
    rank += rankValue;
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

        if (paaSMetricRanked.getMetricName().compareTo("OCCI Create VM availability")  ==  0) {
          float val = paaSMetricRanked.getMetricValue()
              * paaSMetricNormalization.occiCreatevmAvailability;
          paaSMetricRanked.addToRank(val);
        }

        if (paaSMetricRanked.getMetricName().compareTo("OCCI CreateVM Response Time")  ==  0) {
          float val = paaSMetricRanked.getMetricValue()
              * paaSMetricNormalization.occiCreatevmResponseTime;
          paaSMetricRanked.addToRank(0 - val);
        }

        if (paaSMetricRanked.getMetricName().compareTo("OCCI CreateVM Result")  ==  0) {
          float val =  paaSMetricRanked.getMetricValue()
              * paaSMetricNormalization.occiCreatevmResult;
          paaSMetricRanked.addToRank(val);
        }

        if (paaSMetricRanked.getMetricName().compareTo("OCCI Delete VM Availability")  ==  0) {
          float val = paaSMetricRanked.getMetricValue()
              * paaSMetricNormalization.occiDeletevmAvailability;
          paaSMetricRanked.addToRank(val);
        }

        if (paaSMetricRanked.getMetricName().compareTo("OCCI DeleteVM Response Time")  ==  0) {
          float val = paaSMetricRanked.getMetricValue()
              * paaSMetricNormalization.occiDeletevmResponseTime;
          paaSMetricRanked.addToRank(0 - val);
        }

        if (paaSMetricRanked.getMetricName().compareTo("OCCI DeleteVM Result")  ==  0) {
          float val = paaSMetricRanked.getMetricValue()
              * paaSMetricNormalization.occiDeletevmResult;
          paaSMetricRanked.addToRank(val);
        }

        if (paaSMetricRanked.getMetricName().compareTo("General OCCI API Availability")  ==  0) {
          float val = paaSMetricRanked.getMetricValue()
              * paaSMetricNormalization.generalOcciApiAvailability;
          paaSMetricRanked.addToRank(val);
        }

        if (paaSMetricRanked.getMetricName().compareTo("General OCCI API Response Time")  ==  0) {
          float val = paaSMetricRanked.getMetricValue()
              * paaSMetricNormalization.generalOcciApiResponseTime;
          paaSMetricRanked.addToRank(0 - val);
        }

        if (paaSMetricRanked.getMetricName().compareTo("General OCCI API Result")  ==  0) {
          float val = paaSMetricRanked.getMetricValue()
              * paaSMetricNormalization.generalOcciApiResult;
          paaSMetricRanked.addToRank(val);
        }

        if (paaSMetricRanked.getMetricName().compareTo("OCCI Inspect VM availability")  ==  0) {
          float val = paaSMetricRanked.getMetricValue()
              * paaSMetricNormalization.occiInspectVmAvailability;
          paaSMetricRanked.addToRank(val);
        }

        if (paaSMetricRanked.getMetricName().compareTo("OCCI InspectVM Response Time")  ==  0) {
          float val = paaSMetricRanked.getMetricValue()
              * paaSMetricNormalization.occiInspectVmResponseTime;
          paaSMetricRanked.addToRank(0 - val);
        }

        if (paaSMetricRanked.getMetricName().compareTo("OCCI InspectVM Result")  ==  0) {
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
