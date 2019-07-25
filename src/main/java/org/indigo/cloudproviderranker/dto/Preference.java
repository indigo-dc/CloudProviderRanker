package org.indigo.cloudproviderranker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


//TODO docs

/** Preferences class.
 * 
 * 
 *
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Preference {

  /** Service Type field.
   */
  @JsonProperty("service_type")
  private String serviceType;

  /** Priority field.
   */
  private List<Priority> priority;

}
