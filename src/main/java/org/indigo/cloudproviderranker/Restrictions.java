package org.indigo.cloudproviderranker;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Doc TODO.
 */
public class Restrictions {
  /**
   * Doc TODO.
   */
  public double totalLimit         = Double.POSITIVE_INFINITY;
  /**
   * Doc TODO.
   */
  public long   totalGuaranteed    = 0;
  /**
   * Doc TODO.
   */
  public long   instanceGuaranteed = 0;
  /**
   * Doc TODO.
   */
  public double instanceLimit      = Double.POSITIVE_INFINITY;
  /**
   * Doc TODO.
   */
  public long   userGuaranteed     = 0;
  /**
   * Doc TODO.
   */
  public double userLimit          = Double.POSITIVE_INFINITY;

  /**
   * Doc TODO.
   */
  @Override
  public final String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
