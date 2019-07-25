package org.indigo.cloudproviderranker.dto;

import static org.junit.Assert.assertTrue;

import org.indigo.cloudproviderranker.dto.Priority;
import org.junit.Test;


public class PriorityTest {

  @Test
  public void test() {

    Priority priority = new Priority();
    priority.setSlaId("sla_id");
    priority.setServiceId("service_id");
    priority.setWeight(0.15);

    assertTrue(priority.getSlaId().compareTo("sla_id") == 0);
    assertTrue(priority.getServiceId().compareTo("service_id") == 0);
    assertTrue(priority.getWeight().compareTo(0.15) == 0);

    priority.toString();

    Priority p2 = new Priority();
    p2.setSlaId("sla_id2");
    p2.setServiceId("service_id2");
    p2.setWeight(0.7);

    assertTrue(priority.compareTo(p2) < 0);
    assertTrue(p2.compareTo(priority) > 0);
    assertTrue(p2.compareTo(p2) == 0);

    Priority p3 = new Priority("sla_id3", "service_id3", 0.15);

    assertTrue(p3.getSlaId().compareTo("sla_id3") == 0);
    assertTrue(p3.getServiceId().compareTo("service_id3") == 0);
    assertTrue(p3.getWeight().compareTo(0.15) == 0);

  }
}
