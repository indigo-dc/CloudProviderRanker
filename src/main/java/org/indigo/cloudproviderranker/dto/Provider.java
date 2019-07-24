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
public class Provider {

  /**
   * 
   */
  private String provider;

  /**
   * 
   */
  private List<Service> services;

}
