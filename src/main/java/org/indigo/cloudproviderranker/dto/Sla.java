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
public class Sla {

    /**
     * 
     */
    private String customer;

    /**
     * 
     */
    private String provider;

    /**
     * 
     */
    private String start_date;

    /**
     * 
     */
    private String end_date;

    /**
     * 
     */
    private List<Service> services;

    /**
     * 
     */
    private String id;

}
