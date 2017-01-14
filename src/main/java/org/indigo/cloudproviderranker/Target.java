package org.indigo.cloudproviderranker;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Target {
  public String       type;
  public String       unit;
  public Restrictions restrictions;
  
  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
