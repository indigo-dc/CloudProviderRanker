package org.indigo.cloudproviderranker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


/** RankedService class.
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RankedService {

  /** Provider field.
   */
  private String provider;

  /** Service ID field.
   */
  @JsonProperty("service_id")
  private String serviceId;

  /** Service parent ID field.
   */
  private String serviceParentId;

  /** Service Type field.
   **/
  private String serviceType;

  /** Targets field.
   */
  private List<Target> targets;

  /** Metrics field.
   */
  private Map<String, Float> metrics;

  /** Metric Score field.
   */
  private Float metricsScore;

  /** Sla Score field.
   */
  private Float slaScore;

  /** Sla Weight field.
   */
  private Float slaWeight;

  /** Total Score field.
   */
  @JsonProperty("total_score")
  private Float totalScore;

  /** Rank field.
   */
  private int rank;

  /** Ranked field.
   */
  private boolean ranked;

  /** computeTotalScore method.
   * @param parentService id of the parent service
   */
  public void computeTotalScore(RankedService parentService) {
    this.totalScore = this.slaScore + this.metricsScore; 
    if (parentService != null) {
      this.totalScore += (parentService.getSlaScore() + parentService.getMetricsScore());
    }
  }
}