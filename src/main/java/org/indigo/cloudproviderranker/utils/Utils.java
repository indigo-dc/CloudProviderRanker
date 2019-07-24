package org.indigo.cloudproviderranker.utils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.indigo.cloudproviderranker.dto.Metric;

public class Utils {

	public static Map<String, Float> metricsToMap(List<Metric> m) {
		return m.stream().collect(Collectors.toMap(Metric::getMetricName, Metric::getMetricValue));        
	}
}
