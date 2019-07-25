package org.indigo.cloudproviderranker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


/** Rank Result class.
 * 
 * 
 *
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RankResult {

  /** provider field.
   * 
   */
  private String provider;

  /** service Id field.
   * 
   */
  @JsonProperty("service_id")
  private String serviceId;

  /** sla weight field.
   * 
   */
  @JsonProperty("sla_weight")
  private Float slaWeight;

  /** total score field.
   * 
   */
  @JsonProperty("total_score")
  private Float totalScore;

  /** rank field.
   * 
   */
  private int rank;

  /** ranked field.
   * 
   */
  private boolean ranked;
}
