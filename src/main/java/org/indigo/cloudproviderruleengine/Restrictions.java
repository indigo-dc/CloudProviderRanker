package org.indigo.cloudproviderruleengine;

public class Restrictions {
  public double total_limit         = Double.POSITIVE_INFINITY;
  public long   total_guaranteed    = 0;
  public long   instance_guaranteed = 0;
  public double instance_limit      = Double.POSITIVE_INFINITY;
  public long   user_guaranteed     = 0;
  public double user_limit          = Double.POSITIVE_INFINITY;
  
  public String toString( ) {
    return "total_limit="+total_limit+", total_guaranteed="+total_guaranteed+", instance_guaranteed="+instance_guaranteed+", instance_limit="+instance_limit+",user_guaranteed="+user_guaranteed+", user_limit="+user_limit;
  }
}
