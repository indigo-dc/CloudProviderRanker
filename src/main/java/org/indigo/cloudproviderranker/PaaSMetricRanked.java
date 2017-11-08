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

        if (paaSMetricRanked.getMetricName()
            .compareTo(CustomPaaSParams.OCCI_CREATE_VM_AVAILABILITY)  ==  0) {
          float val = paaSMetricRanked.getMetricValue()
              * paaSMetricNormalization.occiCreatevmAvailability;
          paaSMetricRanked.addToRank(val);
        }

        if (paaSMetricRanked.getMetricName()
            .compareTo(CustomPaaSParams.OCCI_CREATEVM_RESPONSE_TIME)  ==  0) {
          float val = paaSMetricRanked.getMetricValue()
              * paaSMetricNormalization.occiCreatevmResponseTime;
          paaSMetricRanked.addToRank(0 - val);
        }

        if (paaSMetricRanked.getMetricName()
            .compareTo(CustomPaaSParams.OCCI_CREATEVM_RESULT)  ==  0) {
          float val =  paaSMetricRanked.getMetricValue()
              * paaSMetricNormalization.occiCreatevmResult;
          paaSMetricRanked.addToRank(val);
        }

        if (paaSMetricRanked.getMetricName()
            .compareTo(CustomPaaSParams.OCCI_DELETE_VM_AVAILABILITY)  ==  0) {
          float val = paaSMetricRanked.getMetricValue()
              * paaSMetricNormalization.occiDeletevmAvailability;
          paaSMetricRanked.addToRank(val);
        }

        if (paaSMetricRanked.getMetricName()
            .compareTo(CustomPaaSParams.OCCI_DELETEVM_RESPONSE_TIME)  ==  0) {
          float val = paaSMetricRanked.getMetricValue()
              * paaSMetricNormalization.occiDeletevmResponseTime;
          paaSMetricRanked.addToRank(0 - val);
        }

        if (paaSMetricRanked.getMetricName()
            .compareTo(CustomPaaSParams.OCCI_DELETEVM_RESULT)  ==  0) {
          float val = paaSMetricRanked.getMetricValue()
              * paaSMetricNormalization.occiDeletevmResult;
          paaSMetricRanked.addToRank(val);
        }

        if (paaSMetricRanked.getMetricName()
            .compareTo(CustomPaaSParams.GENERAL_OCCI_API_AVAILABILITY)  ==  0) {
          float val = paaSMetricRanked.getMetricValue()
              * paaSMetricNormalization.generalOcciApiAvailability;
          paaSMetricRanked.addToRank(val);
        }

        if (paaSMetricRanked.getMetricName()
            .compareTo(CustomPaaSParams.GENERAL_OCCI_API_RESPONSE_TIME)  ==  0) {
          float val = paaSMetricRanked.getMetricValue()
              * paaSMetricNormalization.generalOcciApiResponseTime;
          paaSMetricRanked.addToRank(0 - val);
        }

        if (paaSMetricRanked.getMetricName()
            .compareTo(CustomPaaSParams.GENERAL_OCCI_API_RESULT)  ==  0) {
          float val = paaSMetricRanked.getMetricValue()
              * paaSMetricNormalization.generalOcciApiResult;
          paaSMetricRanked.addToRank(val);
        }

        if (paaSMetricRanked.getMetricName()
            .compareTo(CustomPaaSParams.OCCI_INSPECT_VM_AVAILABILITY)  ==  0) {
          float val = paaSMetricRanked.getMetricValue()
              * paaSMetricNormalization.occiInspectVmAvailability;
          paaSMetricRanked.addToRank(val);
        }

        if (paaSMetricRanked.getMetricName()
            .compareTo(CustomPaaSParams.OCCI_INSPECTVM_RESPONSE_TIME)  ==  0) {
          float val = paaSMetricRanked.getMetricValue()
              * paaSMetricNormalization.occiInspectVmResponseTime;
          paaSMetricRanked.addToRank(0 - val);
        }

        if (paaSMetricRanked.getMetricName()
            .compareTo(CustomPaaSParams.OCCI_INSPECTVM_RESULT)  ==  0) {
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
