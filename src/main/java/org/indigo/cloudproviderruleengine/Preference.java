package org.indigo.cloudproviderruleengine;

//import java.util.List;
import java.util.ArrayList;
import com.google.gson.*;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Preference {

  public String     	     service_type;
  public String     	     id;
  public ArrayList<Priority> priorities;
  
  /**
   *
   */
  public Preference(String service_type, String id, ArrayList<Priority> priorities ) {
    this.service_type = service_type;
    this.priorities   = priorities;
  }
  
  /**
   *
   *
   *
   *
   */
  public static ArrayList<Preference> fromJsonObject( JsonObject obj ) {
    ArrayList<Preference> preferences = new ArrayList<Preference>();
    //ArrayList<Priority> priorities = new ArrayList<Priority>( );
    JsonArray preferencesArray = obj.get("preferences").getAsJsonArray( );
    //System.err.println("preferences size="+Preferences.size());
    for(int i = 0; i< preferencesArray.size(); ++i) {
      JsonObject prefJsonObj = preferencesArray.get(i).getAsJsonObject( );
      //System.err.println("prefJsonObj=" + prefJsonObj.getAsJsonObject().getAsString( ) );
      ArrayList<Priority> priorities = parsePriorities( prefJsonObj.get("priority").getAsJsonArray( ) );
      String service_type = prefJsonObj.get("service_type").getAsString( );
      //String id           = prefJsonObj.get("id").getAsString( );
      Preference prf = new Preference( service_type, "", priorities );
      if(preferences==null)
        System.err.println("preferences NULL!!!");
      else
        preferences.add( prf );
    }
    return preferences;
  }
   
   /**
    *
    *
    * Convert a JSON array "priority" into a java array of Priority objects
    *
    *
    */
   private static ArrayList<Priority> parsePriorities( JsonArray array ) {
   
     ArrayList<Priority> priorities = new ArrayList<Priority>();
     for(int i = 0; i < array.size( ); i++) {
       try {
 	 priorities.add( (new GsonBuilder().create()).fromJson(array.get( i ).getAsJsonObject(), Priority.class) );
       } catch(Exception e) {
	 System.err.println("Exception: " + e.getMessage());
       } catch(Throwable t) {
	 System.err.println("Throwable: " + t.getMessage());
       }
     }
     
     return priorities;
     
   }
  
  /**
   *
   *
   *
   *
   */   
   @Override
   public String toString( ) { 
    //return "{sla_id="+sla_id + " - service_id="+service_id+" - weight=" +weight+ "}"; 
    return ToStringBuilder.reflectionToString(this);
  }
}
