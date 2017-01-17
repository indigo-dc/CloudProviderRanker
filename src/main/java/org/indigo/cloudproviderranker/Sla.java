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

public class Sla {
  public  String    	     id;
  public  String    	     customer;
  public  String    	     provider;
  public  String    	     startDate;
  public  String    	     endDate;
  public  ArrayList<Service> services;
  public  SlaNormalizations  slaNormalizations;
  public  float              rank;
  private float 	     infinityValue;

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
      infinityValue = slaNormalizations.infinity_value;
      if (0  ==  t.type.compareTo("public_ip")) {
        normalizationFactor = slaNormalizations.public_ip;
      }

      if (0  ==  t.type.compareTo("computing_time")) {
        normalizationFactor = slaNormalizations.computing_time;
      }
      if (0  ==  t.type.compareTo("num_cpus")) {
        normalizationFactor = slaNormalizations.num_cpus;
      }
      if (0  ==  t.type.compareTo("mem_size")) {
        normalizationFactor = slaNormalizations.mem_size;
      }
      if (0  ==  t.type.compareTo("disk_size")) {
        normalizationFactor = slaNormalizations.disk_size;
      }
      if (0  ==  t.type.compareTo("upload_bandwidth")) {
        normalizationFactor = slaNormalizations.upload_bandwidth;
      }
      if (0  ==  t.type.compareTo("download_bandwidth")) {
        normalizationFactor = slaNormalizations.download_bandwidth;
      }
      if (0  ==  t.type.compareTo("upload_aggregated")) {
        normalizationFactor = slaNormalizations.upload_aggregated;
      }
      if (0  ==  t.type.compareTo("download_aggregated")) {
        normalizationFactor = slaNormalizations.download_aggregated;
      }

      rank += ((t.restrictions.total_limit < Double.POSITIVE_INFINITY ? t.restrictions.total_limit : infinityValue)
                + t.restrictions.total_guaranteed
      	        + (t.restrictions.user_limit < Double.POSITIVE_INFINITY ? t.restrictions.user_limit : infinityValue)
		+ t.restrictions.user_guaranteed
		+ (t.restrictions.instance_limit < Double.POSITIVE_INFINITY ? t.restrictions.instance_limit : infinityValue)
		+ t.restrictions.instance_guaranteed) * normalizationFactor;
    }
  }

  @Override
  public final String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

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
