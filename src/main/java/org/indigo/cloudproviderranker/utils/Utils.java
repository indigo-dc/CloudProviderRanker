package org.indigo.cloudproviderranker.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.indigo.cloudproviderranker.dto.Metric;


public class Utils {

  public static Map<String, Float> metricsToMap(List<Metric> m) {
    return m.stream().collect(Collectors.toMap(Metric::getMetricName, Metric::getMetricValue));
  }
  
  public static <T> String toJsonString(T obj) {
	  try {
			ObjectMapper objmapper = new ObjectMapper();
			return objmapper.writeValueAsString(obj);
	  }catch (JsonProcessingException e)
	  {
		  return "";
	  }
  }
}
