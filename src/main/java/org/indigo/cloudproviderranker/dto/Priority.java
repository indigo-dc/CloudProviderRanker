package org.indigo.cloudproviderranker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


/** Priority class.
 * 
 * 
 *
 */

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Priority {

  /** Sla ID field.
   */
  @JsonProperty("sla_id")
  private String slaId;

  /** Service ID field.
   */
  @JsonProperty("service_id")
  private String serviceId;

  /** Weight field.
   */
  private Double weight;

  /** compare method.
   */
  public final int compareTo(final Priority p) {
    if (p == null) {
      return 1;
    }
    if (weight.equals(p.getWeight())) {
      return 0;
    }
    return weight > p.getWeight() ? 1 : -1;
  }
}
