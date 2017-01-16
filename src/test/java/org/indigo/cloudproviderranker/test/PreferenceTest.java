package org.indigo.cloudproviderranker.test;

import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.indigo.cloudproviderranker.Preference;
import org.indigo.cloudproviderranker.Priority;
import com.google.gson.*;

public class PreferenceTest {
  @Test
  public void test( ) {
    ArrayList<Priority> P = new ArrayList<Priority>();
    Priority p = new Priority( );
    p.slaId="sla_id";
    p.serviceId="service_id";
    p.weight=(float)0.15;
    P.add(p);
    Preference pref = new Preference( "type", "id", P );
    
    //String checkString = "type=type; Priorities=[{sla_id=sla_id - service_id=service_id - weight=0.15}]";
    if(pref!=null)
      assertTrue( pref.serviceType.compareTo("type") == 0 );
    String jsonTest = "{\"preferences\": [{\"service_type\": \"compute\",\"priority\": [{\"sla_id\": \"4401ac5dc8cfbbb737b0a02575ee53f6\",\"service_id\": \"4401ac5dc8cfbbb737b0a02575e8040f\",\"weight\": 0.5}, {\"sla_id\": \"4401ac5dc8cfbbb737b0a02575ee3b58\",\"service_id\": \"4401ac5dc8cfbbb737b0a02575e6f4bc\",\"weight\": 0.5}]}]}";
    JsonParser parser = new JsonParser();
    JsonElement jsonElement = parser.parse( jsonTest );
    JsonObject obj = jsonElement.getAsJsonObject( );
    ArrayList<Preference> preferences = Preference.fromJsonObject( obj );
    assertTrue(null!=preferences);
    assertTrue(preferences.get(0).toString( ).compareTo("")!=0);
    
  }
}
