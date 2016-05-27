package org.indigo.cloudproviderruleengine;

import java.util.List;
import com.google.gson.*;

public class Preference {

  public String     service_type;
  public String     id;
  public Priority[] priorities;
  
  /**
   *
   */
  public String toString( ) {
    String[] prioStrings = new String[priorities.length];
    int i = 0;
    for (Priority prio : priorities) {
      prioStrings[i++] = prio.toString( );
    }
    return "type=" + service_type + "; Priorities=[" + String.join(",", prioStrings) + "]";
  }
  
  /**
   *
   */
  public Preference(String service_type, Priority[] priorities ) {
    this.service_type = service_type;
    this.priorities   = priorities;
  }
  
  /**
   *
   */
  public static Preference[] fromJsonObject( JsonObject obj ) {
    Preference[] preferences = null;
    Priority[] priorities = null;
    JsonArray Preferences = obj.get("preferences").getAsJsonArray( );
    preferences = new Preference[Preferences.size() ];
    for(int i = 0; i< Preferences.size(); ++i) {
    JsonObject pref = Preferences.get(i).getAsJsonObject( );
      priorities = parsePriorities( Preferences.get(i).getAsJsonObject( ).get("priority").getAsJsonArray( ) );
      preferences[i] = new Preference( pref.get("service_type").getAsString( ), priorities );
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
   private static Priority[] parsePriorities( JsonArray array ) {
   
     Priority[] priorities = new Priority[array.size( )];
     for(int i = 0; i < array.size( ); i++) {
       try {
 	 JsonObject obj = array.get( i ).getAsJsonObject();
 	 //System.err.println("\n[" + clientHostName + "] Processing priority " + obj.toString()+"\n");
 	 Gson gson = new GsonBuilder().create();
	 priorities[i] = gson.fromJson(obj, Priority.class);
       } catch(Exception e) {
	 System.err.println("Exception: " + e.getMessage());
       } catch(Throwable t) {
	 System.err.println("Throwable: " + t.getMessage());
       }
     }
     
     return priorities;
     
   }
}
