package org.indigo.cloudproviderranker.test;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.indigo.cloudproviderranker.Service;
import org.indigo.cloudproviderranker.Target;
import org.indigo.cloudproviderranker.Restrictions;

public class ServiceTest {
  @Test
  public void test( ) {
    Restrictions r = new Restrictions( );
    r.total_limit = 10;
    r.total_guaranteed = 1;
    r.instance_limit = 100;
    r.instance_guaranteed = 1;
    r.user_limit = 5;
    r.user_guaranteed = 1;
    
    if(r!=null)
      assertTrue(r.total_limit==10);
    
    ArrayList<Target> t = new ArrayList<Target>();
    Target T = new Target();
    T.type = "type";
    T.unit = "unit";
    T.restrictions = r;
    if(T!=null)
      assertTrue(T.type.compareTo("type")==0);
    Service S = new Service("id", "type", t);
    
    if(S!=null) {
      assertTrue(S.type.compareTo("type")==0);
    }
    assertTrue(S.toString( ).compareTo("") != 0);
    //String checkString="service_id=id, type=type, targets=[type=type, unit=unit, restritions={total_limit=10.0, total_guaranteed=1, instance_guaranteed=1, instance_limit=100.0,user_guaranteed=1, user_limit=5.0}]";
    //if(null!=S)
    //  assertTrue( S.targets.get(0).type.compareTo("type") == 0 );
  }
}
