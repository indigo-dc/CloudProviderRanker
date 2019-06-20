package org.indigo.cloudproviderranker.dto;

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
public class Priority {
	
	/**
	 * 
	 */
    private String sla_id;
    
    /**
     * 
     */
    private String service_id;
    
    /**
     * 
     */
    private Double weight;
    
    /**
     * 
     */
    public final int compareTo(final Priority p) {
    	if (p == null)
    		return 1;
    	if (weight.equals(p.getWeight())) {
    		return 0;
    	}
    	return weight > p.getWeight() ? 1 : -1;
    }    
}
