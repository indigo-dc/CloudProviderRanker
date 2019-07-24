package org.indigo.cloudproviderranker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class RankResult {

	/**
	 * 
	 */
	private String provider;

	/**
	 * 
	 */
	@JsonProperty("service_id")
	private String serviceId;

	/**
	 * 
	 */
	@JsonProperty("sla_weight")
	private Float slaWeight;

	/**
	 * 
	 */
	@JsonProperty("total_score")
	private Float totalScore;

	/**
	 * 
	 */
	private int rank;

	/**
	 * 
	 */
	private boolean ranked;
}
