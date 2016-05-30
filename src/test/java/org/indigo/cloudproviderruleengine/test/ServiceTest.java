package org.indigo.cloudproviderruleengine.test;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.indigo.cloudproviderruleengine.Service;
import org.indigo.cloudproviderruleengine.Target;
import org.indigo.cloudproviderruleengine.Restrictions;

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
    
    ArrayList<Target> t = new ArrayList<Target>();
    Target T = new Target();
    T.type = "type";
    T.unit = "unit";
    T.restrictions = r;
    Service S = new Service("id", "type", t);
    //String checkString="service_id=id, type=type, targets=[type=type, unit=unit, restritions={total_limit=10.0, total_guaranteed=1, instance_guaranteed=1, instance_limit=100.0,user_guaranteed=1, user_limit=5.0}]";
    assertTrue( null != S );
  }
}
