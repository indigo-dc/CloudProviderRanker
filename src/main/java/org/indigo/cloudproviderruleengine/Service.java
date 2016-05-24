package org.indigo.cloudproviderruleengine;

import java.util.List;

public class Service {
  //public String id;
  public String type;
  public String service_id;
  public Target[] targets;
  
  public Service(String service_id, String type, Target[] targets ) {
    this.service_id = service_id;
    this.type       = type;
    this.targets    = targets;
  }
  
  public String toString( ) {
    String[] targets_string = new String[targets.length];
    for(int i =0; i< targets.length; ++i) {
      targets_string[i] = targets[i].toString( );
    }
    return "service_id="+service_id + ", type=" + type + ", targets=[" + String.join(",", targets_string) + "]";
  }
}
