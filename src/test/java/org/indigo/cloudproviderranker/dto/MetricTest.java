package org.indigo.cloudproviderranker.dto;

import java.util.ArrayList;
import java.util.List;
import org.indigo.cloudproviderranker.dto.Metric;
import org.junit.Test;
import static org.junit.Assert.*;

public class MetricTest {

  @Test
  public void test() {

    Metric m = new Metric();

    m.setMetricName("metric_name");
    m.setMetricKey("metric_key");
    m.setMetricValue(1.0f);
    m.setMetricTime("metric_time");
    m.setMetricUnit("metric_unit");

    List<Object> paasThresholds = new ArrayList<Object>();
    paasThresholds.add("paas_threshold1");
    m.setPaasThresholds(paasThresholds);

    List<Object> historyClocks = new ArrayList<Object>();
    historyClocks.add("history_clock1");
    m.setHistoryClocks(historyClocks);

    List<Object> historyValues = new ArrayList<Object>();
    historyValues.add("history_value1");
    m.setHistoryValues(historyValues);

    assertTrue(m.getMetricName().compareTo("metric_name") == 0);
    assertTrue(m.getMetricKey().compareTo("metric_key") == 0);
    assertTrue(m.getMetricValue().compareTo(1.0f) == 0);
    assertTrue(m.getMetricTime().compareTo("metric_time") == 0);
    assertTrue(m.getMetricUnit().compareTo("metric_unit") == 0);
    assertTrue(m.getPaasThresholds() != null);
    assertTrue(((String)m.getPaasThresholds().get(0)).compareTo("paas_threshold1") == 0);
    assertTrue(m.getHistoryClocks() != null);
    assertTrue(((String)m.getHistoryClocks().get(0)).compareTo("history_clock1") == 0);
    assertTrue(m.getHistoryValues() != null);
    assertTrue(((String)m.getHistoryValues().get(0)).compareTo("history_value1") == 0);

    m.toString();

    Metric m2 = new Metric("metric_name",
        "metric_key",
        1.0f,
        "metric_time",
        "metric_unit",
        paasThresholds,
        historyClocks,
        historyValues);

    assertTrue(m2.getMetricName().compareTo("metric_name") == 0);
    assertTrue(m2.getMetricKey().compareTo("metric_key") == 0);
    assertTrue(m2.getMetricValue().compareTo(1.0f) == 0);
    assertTrue(m2.getMetricTime().compareTo("metric_time") == 0);
    assertTrue(m2.getMetricUnit().compareTo("metric_unit") == 0);
    assertTrue(m2.getPaasThresholds() != null);
    assertTrue(((String)m2.getPaasThresholds().get(0)).compareTo("paas_threshold1") == 0);
    assertTrue(m2.getHistoryClocks() != null);
    assertTrue(((String)m2.getHistoryClocks().get(0)).compareTo("history_clock1") == 0);
    assertTrue(m2.getHistoryValues() != null);
    assertTrue(((String)m2.getHistoryValues().get(0)).compareTo("history_value1") == 0);

  }
}
