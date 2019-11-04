package org.indigo.cloudproviderranker.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.indigo.cloudproviderranker.dto.Metric;


public class Utils {

  /** metricsToMap() static method
   *
   * @param m A list of metric objects
   * @return a map <metric name, metric value>
   */
  public static Map<String, Float> metricsToMap(List<Metric> m) {
    return m.stream().collect(Collectors.toMap(Metric::getMetricName, Metric::getMetricValue));
  }
 
  /** toJsonString() static method
   * 
   * @param obj an object of type T
   * @return a json string representing the input object 
   */ 
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
