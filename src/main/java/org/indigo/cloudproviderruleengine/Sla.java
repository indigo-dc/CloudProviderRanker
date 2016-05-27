package org.indigo.cloudproviderruleengine;

import java.util.List;

public class Sla {
  public String    	   id;
  public String    	   customer;
  public String    	   provider;
  public String    	   start_date;
  public String    	   end_date;
  public Service[] 	   services;
  public SlaNormalizations slaNormalizations;
  public float             rank;
  
  public Sla( String id, String customer, String provider, String start_date, String end_date, Service[] services ) {
    this.id                = id;
    this.customer          = customer;
    this.provider          = provider;
    this.start_date        = start_date;
    this.end_date          = end_date;
    this.services          = services;
    this.slaNormalizations = SlaNormalizations.fromFile( );
    this.rank              = (float)0.0;
    
    // for now let's assume there's only one service: "compute".
    Target[] targets = services[0].targets;
    for(int i = 0; i < targets.length; ++i) {
      Target t = targets[i];
      
      float normalization_factor = (float)0.0;
      if(0==t.type.compareTo("public_ip"))
        normalization_factor = slaNormalizations.public_ip;
      if(0==t.type.compareTo("computing_time"))
        normalization_factor = slaNormalizations.computing_time;
      if(0==t.type.compareTo("num_cpus"))
        normalization_factor = slaNormalizations.num_cpus;
      if(0==t.type.compareTo("mem_size"))
        normalization_factor = slaNormalizations.mem_size;
      if(0==t.type.compareTo("disk_size"))
        normalization_factor = slaNormalizations.disk_size;
      if(0==t.type.compareTo("upload_bandwidth"))
        normalization_factor = slaNormalizations.upload_bandwidth;
      if(0==t.type.compareTo("download_bandwidth"))
        normalization_factor = slaNormalizations.download_bandwidth;
      if(0==t.type.compareTo("upload_aggregated"))
        normalization_factor = slaNormalizations.upload_aggregated;
      if(0==t.type.compareTo("download_aggregated"))
        normalization_factor = slaNormalizations.download_aggregated;
      
      rank += ( (t.restrictions.total_limit<Double.POSITIVE_INFINITY ? t.restrictions.total_limit : 1000000.0) 
                + t.restrictions.total_guaranteed
      	        + (t.restrictions.user_limit<Double.POSITIVE_INFINITY ? t.restrictions.user_limit : 1000000.0) 
		+ t.restrictions.user_guaranteed
		+ (t.restrictions.instance_limit<Double.POSITIVE_INFINITY ? t.restrictions.instance_limit : 1000000.0) 
		+ t.restrictions.instance_guaranteed ) * normalization_factor;
    }
  }
  
  public String toString( ) {
    String[] services_strings = new String[services.length];
    for(int i = 0; i < services.length; ++i) {
      services_strings[i] = services[i].toString( );
    }
    return "id=" + id + ", customer=" + customer + ", provider=" + provider + ", start_date=" + start_date + ", end_date=" + end_date + ", services={"+ String.join(",", services_strings) + "}";
  }
  
  public void reloadPriorityFile( ) {
    slaNormalizations = SlaNormalizations.fromFile( );
  }
  
}
