package org.indigo.cloudproviderranker;

import java.util.ArrayList;
import org.apache.commons.lang3.builder.ToStringBuilder;

  /**
   * Doc TODO.
   */
public class Service {
  /**
   * Doc TODO.
   */
  public String 	   type;
  /**
   * Doc TODO.
   */
  public String 	   service_id;
  /**
   * Doc TODO.
   */
  public ArrayList<Target> targets;
  /**
   * Doc TODO.
   */
  public Service(final String service_id, final String type, final ArrayList<Target> targets) {
    this.service_id = service_id;
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
