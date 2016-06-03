package org.indigo.cloudproviderruleengine.test;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.indigo.cloudproviderruleengine.Sla;
import org.indigo.cloudproviderruleengine.Service;
import org.indigo.cloudproviderruleengine.Target;
import org.indigo.cloudproviderruleengine.Restrictions;

public class SlaTest {
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
    t.add(T);
    //Service S = new Service();
    ArrayList<Service> s = new ArrayList<Service>();
    Service S = new Service("id", "type", t);
    s.add(S);
    //Sla sla = new Sla("id", "customer", "provider", "start_date", "end_date", s );
    //System.err.println(s);
    //String checkString="id=id, customer=customer, provider=provider, start_date=start_date, end_date=end_date, services={service_id=id, type=type, targets=[type=type, unit=unit, restritions={total_limit=10.0, total_guaranteed=1, instance_guaranteed=1, instance_limit=100.0,user_guaranteed=1, user_limit=5.0}]}";
    //String output = s.toString( );
    //System.err.println(checkString);
    //System.err.println("Compare result="+s.toString( ).compareTo(checkString));
    //assertTrue( null != s );
  }
}
