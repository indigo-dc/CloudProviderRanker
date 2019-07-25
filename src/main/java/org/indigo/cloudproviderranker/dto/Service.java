package org.indigo.cloudproviderranker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


/** Service class.
 * 
 * 
 *
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Service {

  /** Service ID field.
   * 
   */
  @JsonProperty("service_id")
  private String serviceId;

  /** Service parent ID field.
   * 
   */
  @JsonProperty("service_parent_id")
  private String serviceParentId;

  /** Type field.
   * 
   */
  private String type;

  /** Targets field.
   * 
   */
  private List<Target> targets;

  /** Metrics field.
   * 
   */
  private List<Metric> metrics;
}