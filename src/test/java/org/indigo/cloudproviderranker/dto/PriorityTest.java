package org.indigo.cloudproviderranker.dto;

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
    
    assertTrue(P.getSla_id().compareTo("sla_id")==0);
    assertTrue(P.getService_id().compareTo("service_id")==0);
    assertTrue(P.getWeight().compareTo(0.15)==0);
    
    P.toString();
    
    Priority P2 = new Priority( );
    P2.setSla_id("sla_id2");
    P2.setService_id("service_id2");
    P2.setWeight(0.7);
    
    assertTrue(P.compareTo(P2)<0);
    assertTrue(P2.compareTo(P)>0);
    assertTrue(P2.compareTo(P2)==0);
    
    Priority P3 = new Priority("sla_id3", "service_id3", 0.15);
    
    assertTrue(P3.getSla_id().compareTo("sla_id3")==0);
    assertTrue(P3.getService_id().compareTo("service_id3")==0);
    assertTrue(P3.getWeight().compareTo(0.15)==0);
        
  }
}
