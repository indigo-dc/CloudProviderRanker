package org.indigo.cloudproviderruleengine;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Priority implements Comparable<Priority> {
  public String sla_id;
  public String service_id;
  public float  weight;
  
  @Override
  public String toString( ) { 
    //return "{sla_id="+sla_id + " - service_id="+service_id+" - weight=" +weight+ "}"; 
    return ToStringBuilder.reflectionToString(this);
  }
  
  public int compareTo( Priority prio ) {
    if(weight==prio.weight) return 0;
    int ret = weight>prio.weight ? -1 : 1;
    return ret;
  }
}
