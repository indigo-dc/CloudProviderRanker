package org.indigo.cloudproviderranker;

import java.util.ArrayList;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Service {

  public String 	   type;
  public String 	   service_id;
  public ArrayList<Target> targets;

  public Service(String service_id,  String type,  ArrayList<Target> targets) {
    this.service_id = service_id;
    this.type       = type;
    this.targets    = targets;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
