package org.indigo.cloudproviderranker;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Doc TODO.
 */
public class Restrictions {
  /**
   * Doc TODO.
   */
  public double total_limit         = Double.POSITIVE_INFINITY;
  /**
   * Doc TODO.
   */
  public long   total_guaranteed    = 0;
  /**
   * Doc TODO.
   */
  public long   instance_guaranteed = 0;
  /**
   * Doc TODO.
   */
  public double instance_limit      = Double.POSITIVE_INFINITY;
  /**
   * Doc TODO.
   */
  public long   user_guaranteed     = 0;
  /**
   * Doc TODO.
   */
  public double user_limit          = Double.POSITIVE_INFINITY;

  /**
   * Doc TODO.
   */
  @Override
  public final String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
