package org.indigo.cloudproviderranker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



/** Sla class.
 * 
 * 
 *
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sla {

  /** Customer field.
   * 
   */
  private String customer;

  /** Provider field.
   * 
   */
  private String provider;

  /** Start date field.
   * 
   */
  @JsonProperty("start_date")
  private String startDate;

  /** End date field.
   * 
   */
  @JsonProperty("end_date")
  private String endDate;

  /** Services field.
   * 
   */
  private List<Service> services;

  /** ID field.
   * 
   */
  private String id;

}
