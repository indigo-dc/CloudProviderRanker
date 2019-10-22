package org.indigo.cloudproviderranker.dto;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



/** Target class.
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

  /** Type field.
   * 
   */
  private String type;

  /** Unit field.
   * 
   */
  private String unit;

  /** Restrictions field.
   * 
   */
  Map<String, Float> restrictions;
}