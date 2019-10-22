package org.indigo.cloudproviderranker.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//TODO docs

/** RankRequest class.
 * 
 *
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RankRequest {

  /** Preferences field.
   */
  private List<Preference> preferences;

  /** Sla field.
   */
  private List<Sla> sla;

  /** monitoring field.
   */
  private List<Provider> monitoring;
}
