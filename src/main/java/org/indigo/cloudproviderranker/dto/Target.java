package org.indigo.cloudproviderranker.dto;

import java.util.Map;
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
public class Target {

  /**
   * 
   */
  private String type;

  /**
   * 
   */
  private String unit;

  /**
   * 
   */
  Map<String, Float> restrictions;
}