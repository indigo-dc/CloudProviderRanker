package org.indigo.cloudproviderranker;

import java.util.ArrayList;
import com.google.gson.JsonArray;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;
import java.util.logging.Level;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Doc TODO.
 */
public class Sla {
  /**
   * Doc TODO.
   */
  public  String    	     id;
  /**
   * Doc TODO.
   */
  public  String    	     customer;
  /**
   * Doc TODO.
   */
  public  String    	     provider;
  /**
   * Doc TODO.
   */
  public  String    	     startDate;
  /**
   * Doc TODO.
   */
  public  String    	     endDate;
  /**
   * Doc TODO.
   */
  public  ArrayList<Service> services;
  /**
   * Doc TODO.
   */
  public  SlaNormalizations  slaNormalizations;
  /**
   * Doc TODO.
   */
  public  float              rank;
  /**
   * Doc TODO.
   */
  private float 	     infinityValue;

  /**
   * Doc TODO.
   */
  public Sla(final String id, final String customer,
             final String provider, final String startDate,
             final String endDate,  final ArrayList<Service> services) {
    this.id                = id;
    this.customer          = customer;
    this.provider          = provider;
    this.startDate         = startDate;
    this.endDate           = endDate;
    this.services          = services;
    this.slaNormalizations = new SlaNormalizations();
    this.slaNormalizations.fromDefaultFile();
    this.slaNormalizations.fromCustomFile();
    this.rank              = 0.0f;

//    KieServices kieServices = KieServices.Factory.get();
  //  KieContainer kContainer = kieServices.getKieClasspathContainer();
   // KieSession ksession = kContainer.newKieSession();
    //ksession.insert(this);
    //int totRules = ksession.fireAllRules();
    //ksession.dispose();

    for (Target t : services.get(0).targets) {

      float normalizationFactor = 0.0f;
      infinityValue = slaNormalizations.infinityValue;
      if (0  ==  t.type.compareTo("public_ip")) {
        normalizationFactor = slaNormalizations.publicIp;
      }
      if (0  ==  t.type.compareTo("computing_time")) {
        normalizationFactor = slaNormalizations.computingTime;
      }
      if (0  ==  t.type.compareTo("num_cpus")) {
        normalizationFactor = slaNormalizations.numCpus;
      }
      if (0  ==  t.type.compareTo("mem_size")) {
        normalizationFactor = slaNormalizations.memSize;
      }
      if (0  ==  t.type.compareTo("disk_size")) {
        normalizationFactor = slaNormalizations.diskSize;
      }
      if (0  ==  t.type.compareTo("upload_bandwidth")) {
        normalizationFactor = slaNormalizations.uploadBandwidth;
      }
      if (0  ==  t.type.compareTo("download_bandwidth")) {
        normalizationFactor = slaNormalizations.downloadBandwidth;
      }
      if (0  ==  t.type.compareTo("upload_aggregated")) {
        normalizationFactor = slaNormalizations.uploadAggregated;
      }
      if (0  ==  t.type.compareTo("download_aggregated")) {
        normalizationFactor = slaNormalizations.downloadAggregated;
      }

      rank += ((t.restrictions.total_limit < Double.POSITIVE_INFINITY ? t.restrictions.total_limit : infinityValue)
                + t.restrictions.total_guaranteed
      	        + (t.restrictions.user_limit < Double.POSITIVE_INFINITY ? t.restrictions.user_limit : infinityValue)
		+ t.restrictions.user_guaranteed
		+ (t.restrictions.instance_limit < Double.POSITIVE_INFINITY ? t.restrictions.instance_limit : infinityValue)
		+ t.restrictions.instance_guaranteed) * normalizationFactor;
    }
  }

  /**
   * Doc TODO.
   */
  @Override
  public final String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

  /**
   * Doc TODO.
   */
  public static ArrayList<Sla> fromJsonObject(final JsonObject obj) {
    JsonArray slas = obj.get("sla").getAsJsonArray();
    ArrayList<Service> serviceArray = new ArrayList<Service>();
    ArrayList<Sla> slaArray = new ArrayList<Sla>();

    for (int i = 0; i < slas.size(); ++i) {
      JsonObject currentSLA = slas.get(i).getAsJsonObject();
      serviceArray = parseService(currentSLA);
      slaArray.add(new Sla(currentSLA.get("id").getAsString(),
		           currentSLA.get("customer").getAsString(),
		           currentSLA.get("provider").getAsString(),
		           currentSLA.get("start_date").getAsString(),
		           currentSLA.get("end_date").getAsString(),  serviceArray));
    }
    return slaArray;
  }

  /**
   * Doc TODO.
   */
  private static ArrayList<Service> parseService(final JsonObject sla) {
    JsonArray services = sla.get("services").getAsJsonArray();
    ArrayList<Service> serviceArray = new ArrayList<Service>();
    for (int i = 0; i < services.size(); i++) {
      try {
        JsonObject obj = services.get(i).getAsJsonObject();
	String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
	ArrayList<Target> targets = parseTarget(obj);
	serviceArray.add(new Service(obj.get("service_id").getAsString(), obj.get("type").getAsString(),  targets));
      } catch (Exception e) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
	Logger.getLogger("").log(Level.INFO,  timeStamp + "Exception: " + e.getMessage());
      } catch (Throwable t) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
	Logger.getLogger("").log(Level.INFO,  timeStamp + "Exception: " + t.getMessage());
      }
    }
    return serviceArray;
  }

  /**
   * Doc TODO.
   */
  private static ArrayList<Target> parseTarget(final JsonObject service) {
    JsonArray targets = service.get("targets").getAsJsonArray();
    ArrayList<Target> targetArray = new ArrayList<Target>();
    Gson gson = new GsonBuilder().create();
    for (int i = 0; i < targets.size(); i++) {
      targetArray.add(gson.fromJson(targets.get(i).getAsJsonObject(),  Target.class));
    }
    return targetArray;
  }
}
