package org.indigo.cloudproviderranker;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;

/**
 * Doc TODO.
 */
public class Service {
  /**
   * Doc TODO.
   */
  public String type;
  /**
   * Doc TODO.
   */
  public String serviceId;
  /**
   * Doc TODO.
   */
  public ArrayList<Target> targets;
  /**
   * Doc TODO.
   */
  public Service(final String serviceid, final String type, final ArrayList<Target> targets) {
    this.serviceId = serviceid;
    this.type       = type;
    this.targets    = targets;
  }
  /**
   * Doc TODO.
   */
  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
