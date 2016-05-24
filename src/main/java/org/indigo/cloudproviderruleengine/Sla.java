package org.indigo.cloudproviderruleengine;

import java.util.List;

public class Sla {
  public String id;
  public String customer;
  public String provider;
  public String start_date;
  public String end_date;
  
  public List<Service> services;
}
