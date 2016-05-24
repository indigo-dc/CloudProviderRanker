package org.indigo.cloudproviderruleengine;

import java.util.List;

public class Preference {

  public String     service_type;
  public String     id;
  public Priority[] priorities;
  
  public String toString( ) {
    String[] prioStrings = new String[priorities.length];
    int i = 0;
    for (Priority prio : priorities) {
      prioStrings[i++] = prio.toString( );
    }
    return "type=" + service_type + "; Priorities=[" + String.join(",", prioStrings) + "]";
  }
  
  public Preference(String service_type, Priority[] priorities ) {
    this.service_type = service_type;
    this.priorities   = priorities;
  }
}
