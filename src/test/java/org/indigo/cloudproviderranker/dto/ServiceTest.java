package org.indigo.cloudproviderranker.dto;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.indigo.cloudproviderranker.dto.Metric;
import org.indigo.cloudproviderranker.dto.Service;
import org.indigo.cloudproviderranker.dto.Target;
import org.junit.Test;

public class ServiceTest {

  @Test
  public void test() {

    ArrayList<Target> targets = new ArrayList<Target>();
    Target t = new Target();
    t.setType("target_type");
    targets.add(t);

    ArrayList<Metric> metrics = new ArrayList<Metric>();
    Metric m = new Metric();
    m.setMetricName("metric_name");
    metrics.add(m);

    Service s = new Service();
    s.setServiceId("service_id");
    s.setServiceParentId("service_parent_id");
    s.setType("service_type");
    s.setTargets(targets);
    s.setMetrics(metrics);

    assertTrue(s.getServiceId().compareTo("service_id") == 0);
    assertTrue(s.getServiceParentId().compareTo("service_parent_id") == 0);
    assertTrue(s.getType().compareTo("service_type") == 0);
    assertTrue(s.getTargets() != null);
    assertTrue(s.getTargets().get(0).equals(t));
    assertTrue(s.getMetrics() != null);
    assertTrue(s.getMetrics().get(0).equals(m));

    s.toString();

    Service s2 = new Service("service_id", "service_parent_id", "service_type", targets, metrics);

    assertTrue(s2.getServiceId().compareTo("service_id") == 0);
    assertTrue(s2.getServiceParentId().compareTo("service_parent_id") == 0);
    assertTrue(s2.getType().compareTo("service_type") == 0);
    assertTrue(s2.getTargets() != null);
    assertTrue(s2.getTargets().get(0).equals(t));
    assertTrue(s2.getMetrics() != null);
    assertTrue(s2.getMetrics().get(0).equals(m));

  }
}
