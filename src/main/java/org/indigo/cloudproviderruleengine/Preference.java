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
//   public String toString( ) {
//     String[] prioStrings = new String[priorities.size()];
//     int i = 0;
//     for (Priority prio : priorities) {
//       prioStrings[i++] = prio.toString( );
//     }
//     return "type=" + service_type + "; Priorities=[" + String.join(",", prioStrings) + "]";
//   }
  
  /**
   *
   */
  public Preference(String service_type, String id, ArrayList<Priority> priorities ) {
    this.service_type = service_type;
    this.priorities   = priorities;
  }
  
  /**
   *
   */
  public static ArrayList<Preference> fromJsonObject( JsonObject obj ) {
    ArrayList<Preference> preferences = new ArrayList<Preference>();
    ArrayList<Priority> priorities = new ArrayList<Priority>( );
    JsonArray Preferences = obj.get("preferences").getAsJsonArray( );
    for(int i = 0; i< Preferences.size(); ++i) {
    JsonObject pref = Preferences.get(i).getAsJsonObject( );
      priorities = parsePriorities( Preferences.get(i).getAsJsonObject( ).get("priority").getAsJsonArray( ) );
      preferences.add( new Preference( pref.get("service_type").getAsString( ), pref.get("id").getAsString( ), priorities ) );
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
   
   @Override
   public String toString( ) { 
    //return "{sla_id="+sla_id + " - service_id="+service_id+" - weight=" +weight+ "}"; 
    return ToStringBuilder.reflectionToString(this);
  }
}
