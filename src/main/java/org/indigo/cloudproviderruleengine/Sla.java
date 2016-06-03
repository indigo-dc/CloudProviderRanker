package org.indigo.cloudproviderruleengine;

import java.util.List;
import java.util.ArrayList;
import com.google.gson.*;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Sla {
  public String    	    id;
  public String    	    customer;
  public String    	    provider;
  public String    	    start_date;
  public String    	    end_date;
  public ArrayList<Service> services;
  public SlaNormalizations  slaNormalizations;
  public float              rank;
  
  /**
   *
   */
  public Sla( String id, String customer, String provider, String start_date, String end_date, ArrayList<Service> services ) {
    this.id                = id;
    this.customer          = customer;
    this.provider          = provider;
    this.start_date        = start_date;
    this.end_date          = end_date;
    this.services          = services;
    this.slaNormalizations = SlaNormalizations.fromFile( );
    this.rank              = 0.0f;
    
    // for now let's assume there's only one service: "compute".
    ArrayList<Target> targets = services.get(0).targets;
    for(int i = 0; i < targets.size( ); ++i) {
      Target t = targets.get(i);
      
      float normalization_factor = 0.0f;
      float inf = slaNormalizations.infinity_value;
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
      
      rank += ( (t.restrictions.total_limit<Double.POSITIVE_INFINITY ? t.restrictions.total_limit : inf) 
                + t.restrictions.total_guaranteed
      	        + (t.restrictions.user_limit<Double.POSITIVE_INFINITY ? t.restrictions.user_limit : inf) 
		+ t.restrictions.user_guaranteed
		+ (t.restrictions.instance_limit<Double.POSITIVE_INFINITY ? t.restrictions.instance_limit : inf) 
		+ t.restrictions.instance_guaranteed ) * normalization_factor;
    }
  }
  
  /**
   *
   */
  @Override
  public String toString( ) {
//     String[] services_strings = new String[services.size()];
//     for(int i = 0; i < services.size() ; ++i) {
//       services_strings[i] = services.get(i).toString( );
//     }
    //return "id=" + id + ", customer=" + customer + ", provider=" + provider + ", start_date=" + start_date + ", end_date=" + end_date + ", services={"+ String.join(",", services_strings) + "}";
    return ToStringBuilder.reflectionToString(this);
  }
  
  /**
   *
   */
  public void reloadPriorityFile( ) {
    slaNormalizations = SlaNormalizations.fromFile( );
  }
  
  /**
   *
   */
  public static ArrayList<Sla> fromJsonObject( JsonObject obj ) {
    JsonArray SLAS = obj.get("sla").getAsJsonArray( );
    ArrayList<Service> services = new ArrayList<Service>();
    ArrayList<Sla> SLAs = new ArrayList<Sla>();
    for(int i=0; i < SLAS.size( ); ++i) {
      JsonObject currentSLA = SLAS.get(i).getAsJsonObject( );
      services = parseService( currentSLA );
      SLAs.add( new Sla( currentSLA.get("id").getAsString(),
     	 		 currentSLA.get("customer").getAsString(),
      			 currentSLA.get("provider").getAsString(),
      			 currentSLA.get("start_date").getAsString(),
      			 currentSLA.get("end_date").getAsString(), services) );
    }
    return SLAs;
  }
  
     
   /**
    *
    *
    * Extract (and convert to a Java array) the services from a SLA json element
    *
    *
    */
   private static ArrayList<Service> parseService( JsonObject sla ) {
     JsonArray Services = sla.get("services").getAsJsonArray( );
     ArrayList<Service> services = new ArrayList<Service>();
     for(int i = 0; i < Services.size( ); i++) {
       try {
         JsonObject obj = Services.get( i ).getAsJsonObject();
         //System.err.println("\n[" + clientHostName + "] Processing Service " + obj.toString()+"\n");
	 ArrayList<Target> targets = parseTarget( obj ); 
	 services.add( new Service( obj.get("service_id").getAsString(), obj.get("type").getAsString( ) , targets) );
	 //System.err.println("Service[" + i + "]=" + services[i]);
       } catch(Exception e) {
	 System.err.println("Exception: " + e.getMessage());
       } catch(Throwable t) {
	 System.err.println("Throwable: " + t.getMessage());
       }
     }
     return services;
   }
   
   /**
    *
    *
    * Extract (and convert to a Java array) the targets from a service json element
    *
    *
    */
   private static ArrayList<Target> parseTarget( JsonObject service ) {
     JsonArray Targets = service.get("targets").getAsJsonArray( );
     ArrayList<Target> targets = new ArrayList<Target>();
     Gson gson = new GsonBuilder().create();
     for(int i = 0; i < Targets.size( ); i++) {
       targets.add( gson.fromJson(Targets.get(i).getAsJsonObject( ), Target.class) );

     }
     return targets;
   }
   
}
