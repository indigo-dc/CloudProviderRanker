package org.indigo.cloudproviderranker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

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
public class Metric {

	/**
	 * 
	 */
	private String metricName;

	/**
	 * 
	 */
	private String metricKey;

	/**
	 * 
	 */
	private Float metricValue;

	/**
	 * 
	 */
	private String metricTime;

	/**
	 * 
	 */
	private String metricUnit;

	/**
	 * 
	 */
	private List<Object> paasThresholds;

	/**
	 * 
	 */
	private List<Object> historyClocks;

	/**
	 * 
	 */
	private List<Object> historyValues;
}
