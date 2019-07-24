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
