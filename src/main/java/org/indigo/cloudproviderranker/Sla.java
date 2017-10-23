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

import java.io.UnsupportedEncodingException;
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

  public static String rules_file = null;

  private static KieContainer kcontainer;

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

    KieSession ks = kcontainer.newKieSession();
    ks.insert(this);
    int totRules = ks.fireAllRules();
    ks.dispose();
  }

  /**
   * Doc TODO.
   */
  public static final void loadRules() throws UnsupportedEncodingException {
    KieServices ks = KieServices.Factory.get();
    KieFileSystem kfs = ks.newKieFileSystem();
    KieResources kres = ks.getResources();

    if (null != rules_file && !rules_file.isEmpty()) {
      Logger.getLogger("").log(Level.INFO, "Loading rules from '" + rules_file + "'");
      kfs.write("src/main/resources/rules/rules.drl", kres.newFileSystemResource(rules_file));
    } else {
      Logger.getLogger("").log(Level.INFO, "Loading default rules.");
      kfs.write("src/main/resources/rules/rules.drl",
                kres.newClassPathResource("rules/DefaultRules.drl", Sla.class));
    }

    String ruleContent = new String(kfs.read("src/main/resources/rules/rules.drl"), "UTF8");
    Logger.getLogger("").log(Level.INFO, "Rules content:\n" + ruleContent + "\n");
    // this will parse and compile in one step
    KieBuilder kb = ks.newKieBuilder(kfs);
    kb.buildAll();

    // Check the builder for errors
    if (kb.getResults().hasMessages(Message.Level.ERROR)) {
      throw new RulesBuildException(kb.getResults());
    }

    KieRepository kr = ks.getRepository();
    kcontainer = ks.newKieContainer(kr.getDefaultReleaseId());
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
