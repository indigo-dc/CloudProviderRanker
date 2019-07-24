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
public class RankRequest {

  /**
   * 
   */
  private List<Preference> preferences;

  /**
   * 
   */
  private List<Sla> sla;

  /**
   * 
   */
  private List<Provider> monitoring;
}
