package org.indigo.cloudproviderranker.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



//TODO docs

/** Metric class.
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

  /** Metric Name.
   * 
   */
  private String metricName;

  /** Metric Key.
   * 
   */
  private String metricKey;

  /** Metric Value.
   * 
   */
  private Float metricValue;

  /** Metric Time.
   * 
   */
  private String metricTime;

  /** Metric Unit.
   * 
   */
  private String metricUnit;

  /** PaaS Thresholds.
   * 
   */
  private List<Object> paasThresholds;

  /** History Clocks.
   * 
   */
  private List<Object> historyClocks;

  /** History Values.
   * 
   */
  private List<Object> historyValues;
}
