package org.indigo.cloudproviderranker;

import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Doc TODO.
 */
public class Restrictions {
  /**
   * Doc TODO.
   */
  @SerializedName("total_limit")
  public double totalLimit         = Double.POSITIVE_INFINITY;
  /**
   * Doc TODO.
   */
  @SerializedName("total_guaranteed")
  public long   totalGuaranteed    = 0;
  /**
   * Doc TODO.
   */
  @SerializedName("instance_guaranteed")
  public long   instanceGuaranteed = 0;
  /**
   * Doc TODO.
   */
  @SerializedName("instance_limit")
  public double instanceLimit      = Double.POSITIVE_INFINITY;
  /**
   * Doc TODO.
   */
  @SerializedName("user_guaranteed")
  public long   userGuaranteed     = 0;
  /**
   * Doc TODO.
   */
  @SerializedName("user_limit")
  public double userLimit          = Double.POSITIVE_INFINITY;

  /**
   * Doc TODO.
   */
  @Override
  public final String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
