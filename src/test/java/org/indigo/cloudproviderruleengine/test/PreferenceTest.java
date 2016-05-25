package org.indigo.cloudproviderruleengine.test;

import org.junit.Test;
import static org.junit.Assert.*;
import org.indigo.cloudproviderruleengine.Preference;
import org.indigo.cloudproviderruleengine.Priority;

public class PreferenceTest {
  @Test
  public void test( ) {
    Priority P[] = new Priority[1];
    P[0] = new Priority( );
    P[0].sla_id="sla_id";
    P[0].service_id="service_id";
    P[0].weight=(float)0.15;
    
    Preference pref = new Preference( "type", P );
    
    String checkString = "type=type; Priorities=[{sla_id=sla_id - service_id=service_id - weight=0.15}]";
    
    assertTrue( 0 == pref.toString( ).compareTo(checkString) );
  }
}
