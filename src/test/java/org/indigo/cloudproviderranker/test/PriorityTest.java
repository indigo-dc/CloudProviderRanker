package org.indigo.cloudproviderranker.test;

import org.junit.Test;
import static org.junit.Assert.*;

import org.indigo.cloudproviderranker.dto.Priority;


public class PriorityTest {
	
  @Test
  public void test( ) {
    Priority P = new Priority( );
    P.setSla_id("sla_id");
    P.setService_id("service_id");
    P.setWeight(0.15);
    
    //String checkString = "{sla_id=sla_id - service_id=service_id - weight=0.15}";
    if(P!=null)
      assertTrue( P.getSla_id().compareTo("sla_id") == 0 );
    
    Priority P2 = new Priority( );
    P.setSla_id("sla_id2");
    P.setService_id("service_id2");
    P.setWeight(0.7);
    
    assertTrue(P.compareTo(P2)<0);
    assertTrue(P2.compareTo(P)>0);
    assertTrue(P2.compareTo(P2)==0);
    P.toString();
  }
}
