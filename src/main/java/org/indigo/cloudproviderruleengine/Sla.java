package org.indigo.cloudproviderruleengine;

import java.util.List;

public class Sla {
  public String    id;
  public String    customer;
  public String    provider;
  public String    start_date;
  public String    end_date;
  public Service[] services;
  
  public Sla( String id, String customer, String provider, String start_date, String end_date, Service[] services ) {
    this.id         = id;
    this.customer   = customer;
    this.provider   = provider;
    this.start_date = start_date;
    this.end_date   = end_date;
    this.services   = services;
  }
  
  public String toString( ) {
    String[] services_strings = new String[services.length];
    for(int i = 0; i < services.length; ++i) {
      services_strings[i] = services[i].toString( );
    }
    return "id=" + id + ", customer=" + customer + ", provider=" + provider + ", start_date=" + start_date + ", end_date=" + end_date + ", services={"+ String.join(",", services_strings) + "}";
  }
}
