package org.indigo.cloudproviderranker.dto;

import org.indigo.cloudproviderranker.dto.Priority;
import org.junit.Test;
import static org.junit.Assert.*;


public class PriorityTest {

  @Test
  public void test() {

    Priority priority = new Priority();
    priority.setSla_id("sla_id");
    priority.setService_id("service_id");
    priority.setWeight(0.15);

    assertTrue(priority.getSla_id().compareTo("sla_id") == 0);
    assertTrue(priority.getService_id().compareTo("service_id") == 0);
    assertTrue(priority.getWeight().compareTo(0.15) == 0);

    priority.toString();

    Priority p2 = new Priority();
    p2.setSla_id("sla_id2");
    p2.setService_id("service_id2");
    p2.setWeight(0.7);

    assertTrue(priority.compareTo(p2) < 0);
    assertTrue(p2.compareTo(priority) > 0);
    assertTrue(p2.compareTo(p2) == 0);

    Priority p3 = new Priority("sla_id3", "service_id3", 0.15);

    assertTrue(p3.getSla_id().compareTo("sla_id3") == 0);
    assertTrue(p3.getService_id().compareTo("service_id3") == 0);
    assertTrue(p3.getWeight().compareTo(0.15) == 0);

  }
}
