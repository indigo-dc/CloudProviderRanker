package org.indigo.cloudproviderruleengine;

import java.util.ArrayList;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Service {

  public String 	   type;
  public String 	   service_id;
  public ArrayList<Target> targets;
  
  public Service(String service_id, String type, ArrayList<Target> targets ) {
    this.service_id = service_id;
    this.type       = type;
    this.targets    = targets;
  }
  
  @Override
  public String toString( ) {
/*    String[] targets_string = new String[ targets.size( ) ];
    for(int i =0; i < targets.size( ); ++i) {
      targets_string[i] = targets.get(i).toString( );
    }
    return "service_id="+service_id + ", type=" + type + ", targets=[" + String.join(",", targets_string) + "]";*/
    return ToStringBuilder.reflectionToString(this);
  }
}
