package org.indigo.cloudproviderruleengine;

public class Target {
  public String       type;
  public String       unit;
  public Restrictions restrictions;
  
  public String toString( ) {
    return "type=" + type + ", unit=" + unit + ", restritions={" + restrictions.toString( )+"}";
  }
}
