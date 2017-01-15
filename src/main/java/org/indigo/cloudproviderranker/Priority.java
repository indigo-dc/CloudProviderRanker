package org.indigo.cloudproviderranker;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Priority implements Comparable<Priority> {
  public String slaId;
  //public String serviceId;
  public float  weight;

  @Override
  public final String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

  public final int compareTo(final Priority prio) {
    if (weight == prio.weight) {
      return 0;
    }
    int ret = weight > prio.weight ? -1 : 1;
    return ret;
  }
}
