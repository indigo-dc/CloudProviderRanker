package org.indigo.cloudproviderranker.test;

import org.junit.Test;
import static org.junit.Assert.*;
import org.indigo.cloudproviderranker.Priority;

public class PriorityTest {
  @Test
  public void test( ) {
    Priority P = new Priority( );
    P.slaId="sla_id";
    P.serviceId="service_id";
    P.weight=0.15f;
    //String checkString = "{sla_id=sla_id - service_id=service_id - weight=0.15}";
    if(P!=null)
      assertTrue( P.slaId.compareTo("sla_id") == 0 );
    Priority P2 = new Priority( );
    P.slaId="sla_id2";
    P.serviceId = "service_id2";
    P.weight=0.7f;
    assertTrue(P.compareTo(P2)<0);
    assertTrue(P2.compareTo(P)>0);
    assertTrue(P2.compareTo(P2)==0);
    P.toString();
  }
}
