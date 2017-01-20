package org.indigo.cloudproviderranker;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Doc TODO.
 */
public class Target {
  /**
   * Doc TODO.
   */
  public String       type;
  /**
   * Doc TODO.
   */
  public String       unit;
  /**
   * Doc TODO.
   */
  public Restrictions restrictions;

  /**
   * Doc TODO.
   */
  @Override
  public final String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
