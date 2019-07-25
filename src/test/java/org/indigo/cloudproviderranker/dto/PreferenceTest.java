package org.indigo.cloudproviderranker.dto;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import org.indigo.cloudproviderranker.dto.Preference;
import org.indigo.cloudproviderranker.dto.Priority;

import org.junit.Test;

public class PreferenceTest {

  @Test
  public void test() {

    Priority p = new Priority();
    p.setSlaId("sla_id");
    p.setServiceId("service_id");
    p.setWeight(0.15);
    ArrayList<Priority> priorities = new ArrayList<Priority>();
    priorities.add(p);

    Preference p1 = new Preference();
    p1.setServiceType("service_type");
    p1.setPriority(priorities);

    assertTrue(p1.getServiceType().compareTo("service_type") == 0);
    assertTrue(p1.getPriority() != null);
    assertTrue(p1.getPriority().get(0).compareTo(p) == 0);

    p1.toString();   

    Preference p2 = new Preference("service_type", priorities);

    assertTrue(p2.getServiceType().compareTo("service_type") == 0);
    assertTrue(p2.getPriority() != null);
    assertTrue(p2.getPriority().get(0).compareTo(p) == 0);

  }
}
