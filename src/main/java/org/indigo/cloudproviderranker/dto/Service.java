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
public class Service {

  /**
   * 
   */
  private String service_id;

  /**
   * 
   */
  private String service_parent_id;

  /**
   * 
   */
  private String type;

  /**
   * 
   */
  private List<Target> targets;

  /**
   * 
   */
  private List<Metric> metrics;
}