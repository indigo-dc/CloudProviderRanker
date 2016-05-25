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
    P.weight=(float)0.15;
    String checkString = "{sla_id=sla_id - service_id=service_id - weight=0.15}";
    assertTrue( 0 == P.toString( ).compareTo(checkString) );
  }
}
