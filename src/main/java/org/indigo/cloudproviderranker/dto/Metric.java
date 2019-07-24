package org.indigo.cloudproviderranker.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



//TODO docs

/**
 * 
 * 
 *
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Metric {

  /**
   * 
   */
  private String metricName;

  /**
   * 
   */
  private String metricKey;

  /**
   * 
   */
  private Float metricValue;

  /**
   * 
   */
  private String metricTime;

  /**
   * 
   */
  private String metricUnit;

  /**
   * 
   */
  private List<Object> paasThresholds;

  /**
   * 
   */
  private List<Object> historyClocks;

  /**
   * 
   */
  private List<Object> historyValues;
}
