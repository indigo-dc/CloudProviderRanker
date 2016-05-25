package org.indigo.cloudproviderruleengine;

public class Priority implements Comparable<Priority> {
  public String sla_id;
  public String service_id;
  public float  weight;
  
  public String toString( ) { return "{sla_id="+sla_id + " - service_id="+service_id+" - weight=" +weight+ "}"; }
  
  public int compareTo( Priority prio ) {
    if(weight==prio.weight) return 0;
    int ret = weight>prio.weight ? -1 : 1;
    return ret;
  }
}
