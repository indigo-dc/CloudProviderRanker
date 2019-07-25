package org.indigo.cloudproviderranker.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


//TODO docs

/** Provider class.
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Provider {

  /** Provider field.
   */
  private String provider;

  /** Services field.
   */
  private List<Service> services;

}
