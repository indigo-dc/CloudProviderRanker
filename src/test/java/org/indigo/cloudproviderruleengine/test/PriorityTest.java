package org.indigo.cloudproviderruleengine.test;

import org.junit.Test;
import static org.junit.Assert.*;
import org.indigo.cloudproviderruleengine.Priority;

public class PriorityTest {
  @Test
  public void test( ) {
    Priority P = new Priority( );
    P.sla_id="sla_id";
    P.service_id="service_id";
    P.weight=0.15f;
    //String checkString = "{sla_id=sla_id - service_id=service_id - weight=0.15}";
    if(P!=null)
      assertTrue( P.sla_id.compareTo("sla_id") == 0 );
    Priority P2 = new Priority( );
    P.sla_id="sla_id2";
    P.service_id = "service_id2";
    P.weight=0.7f;
    assertTrue(P.compareTo(P2)<0);
    assertTrue(P2.compareTo(P)>0);
     assertTrue(P2.compareTo(P2)==0);
  }
}
