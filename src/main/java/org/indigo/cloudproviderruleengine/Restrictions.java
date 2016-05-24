package org.indigo.cloudproviderruleengine;

public class Restrictions {
  public double total_limit         = Double.POSITIVE_INFINITY;
  public long   total_guaranteed    = 0;
  public long   instance_guaranteed = 0;
  public double instance_limit      = Double.POSITIVE_INFINITY;
  public long   user_guaranteed     = 0;
  public double user_limit          = Double.POSITIVE_INFINITY;
}
