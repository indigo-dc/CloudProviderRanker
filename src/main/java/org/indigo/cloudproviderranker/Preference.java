package org.indigo.cloudproviderranker;

import java.util.ArrayList;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.GsonBuilder;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Preference {

  public String     	     serviceType;
  public String     	     id;
  public ArrayList<Priority> priorities;

  public Preference(final String serviceType,
                    final String id,
                    final ArrayList<Priority> priorities) {
    this.serviceType = serviceType;
    this.priorities   = priorities;
  }

  public static ArrayList<Preference> fromJsonObject(final JsonObject obj) {
    ArrayList<Preference> preferences = new ArrayList<Preference>();
    //ArrayList<Priority> priorities = new ArrayList<Priority>();
    JsonArray preferencesArray = obj.get("preferences").getAsJsonArray();
    //System.err.println("preferences size="+Preferences.size());
    for (int i = 0; i < preferencesArray.size(); ++i) {
      JsonObject prefJsonObj = preferencesArray.get(i).getAsJsonObject();
      //System.err.println("prefJsonObj=" + prefJsonObj.getAsJsonObject().getAsString());
      ArrayList<Priority> priorities = parsePriorities(prefJsonObj.get("priority").getAsJsonArray());
      String serviceType = prefJsonObj.get("serviceType").getAsString();
      //String id           = prefJsonObj.get("id").getAsString();
      Preference prf = new Preference(serviceType,  "",  priorities);
      preferences.add(prf);
    }
    return preferences;
  }

  private static ArrayList<Priority> parsePriorities(final JsonArray array) {
    ArrayList<Priority> priorities = new ArrayList<Priority>();
    for (int i = 0; i < array.size(); i++) {
      JsonObject obj = array.get(i).getAsJsonObject();
      priorities.add((new GsonBuilder().create()).fromJson(obj,  Priority.class));
    }
    return priorities;
  }

  @Override
  public final String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
