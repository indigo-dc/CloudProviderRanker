package org.indigo.cloudproviderranker;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.Message;
import org.kie.api.io.KieResources;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Doc TODO.
 */
public class Sla {
  /**
   * Doc TODO.
   */
  public  String id;
  /**
   * Doc TODO.
   */
  public  String customer;
  /**
   * Doc TODO.
   */
  public  String provider;
  /**
   * Doc TODO.
   */
  @SerializedName("start_date") public  String startDate;
  /**
   * Doc TODO.
   */
  @SerializedName("end_date") public  String endDate;
  /**
   * Doc TODO.
   */
  public  ArrayList<Service> services;
  /**
   * Doc TODO.
   */
  public  SlaNormalizations slaNormalizations;
  /**
   * Doc TODO.
   */
  public  float rank;
  /**
   * Doc TODO.
   */
  private float infinityValue;

  public static String rules_file = null;

  private static KieSession ksession;

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

    ksession.insert(this);
    int totRules = ksession.fireAllRules();
    ksession.dispose();

    for (Target t : services.get(0).targets) {
      float normalizationFactor = slaNormalizations.getByName(t.type);
      infinityValue = slaNormalizations.infinityValue;

      rank += ((t.restrictions.totalLimit
                < Double.POSITIVE_INFINITY ? t.restrictions.totalLimit : infinityValue)
                + t.restrictions.totalGuaranteed
                + (t.restrictions.userLimit
                   < Double.POSITIVE_INFINITY ? t.restrictions.userLimit : infinityValue)
                + t.restrictions.userGuaranteed
                + (t.restrictions.instanceLimit
                   < Double.POSITIVE_INFINITY ? t.restrictions.instanceLimit : infinityValue)
                + t.restrictions.instanceGuaranteed) * normalizationFactor;
    }
  }

  public final static void loadRules() {
    KieServices ks = KieServices.Factory.get();
    KieRepository kr = ks.getRepository();
    KieFileSystem kfs = ks.newKieFileSystem();
    KieResources kres = ks.getResources();

    if(null != rules_file && !rules_file.isEmpty()) {
      // this will parse and compile in one step
      kfs.write(kres.newFileSystemResource(rules_file));
    } else {
      // load default file
      kfs.write(kres.newClassPathResource("rules/DefaultRules.drl", Sla.class));
    }

    KieBuilder kb = ks.newKieBuilder(kfs);
    kb.buildAll();

    // Check the builder for errors
    if (kb.getResults().hasMessages(Message.Level.ERROR)) {
      throw new RuntimeException("Rules Build Errors:\n" + kb.getResults().toString());
    }

    KieContainer kc = ks.newKieContainer(kr.getDefaultReleaseId());
    ksession = kc.newKieSession();
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
      JsonObject currentSla = slas.get(i).getAsJsonObject();
      serviceArray = parseService(currentSla);
      slaArray.add(new Sla(currentSla.get("id").getAsString(),
                           currentSla.get("customer").getAsString(),
                           currentSla.get("provider").getAsString(),
                           currentSla.get("start_date").getAsString(),
                           currentSla.get("end_date").getAsString(),  serviceArray));
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
        serviceArray.add(new Service(obj.get("service_id").getAsString(), 
                                     obj.get("type").getAsString(),  targets));
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
