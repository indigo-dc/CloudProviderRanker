package org.indigo.cloudproviderranker.dto;

import java.util.ArrayList;
import org.indigo.cloudproviderranker.dto.Preference;
import org.indigo.cloudproviderranker.dto.Priority;

import static org.junit.Assert.*;
import org.junit.Test;

public class PreferenceTest {

  @Test
  public void test() {

    Priority p = new Priority();
    p.setSla_id("sla_id");
    p.setService_id("service_id");
    p.setWeight(0.15);
    ArrayList<Priority> priorities = new ArrayList<Priority>();
    priorities.add(p);

    Preference p1 = new Preference();
    p1.setService_type("service_type");
    p1.setPriority(priorities);

    assertTrue(p1.getService_type().compareTo("service_type") == 0);
    assertTrue(p1.getPriority() != null);
    assertTrue(p1.getPriority().get(0).compareTo(p) == 0);

    p1.toString();   

    Preference p2 = new Preference("service_type", priorities);

    assertTrue(p2.getService_type().compareTo("service_type") == 0);
    assertTrue(p2.getPriority() != null);
    assertTrue(p2.getPriority().get(0).compareTo(p) == 0);

  }
}
