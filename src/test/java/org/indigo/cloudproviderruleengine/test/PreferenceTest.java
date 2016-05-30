package org.indigo.cloudproviderruleengine.test;

import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.indigo.cloudproviderruleengine.Preference;
import org.indigo.cloudproviderruleengine.Priority;

public class PreferenceTest {
  @Test
  public void test( ) {
    ArrayList<Priority> P = new ArrayList<Priority>();
    Priority p = new Priority( );
    p.sla_id="sla_id";
    p.service_id="service_id";
    p.weight=(float)0.15;
    P.add(p);
    Preference pref = new Preference( "type", "id", P );
    
    //String checkString = "type=type; Priorities=[{sla_id=sla_id - service_id=service_id - weight=0.15}]";
    
    assertTrue( null != pref );
  }
}
