package org.indigo.cloudproviderruleengine.test;

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
    
    Target[] t = new Target[1];
    t[0] = new Target();
    t[0].type = "type";
    t[0].unit = "unit";
    t[0].restrictions = r;
    Service[] S = new Service[1];
    S[0] = new Service("id", "type", t);
    Sla s = new Sla("id", "customer", "provider", "start_date", "end_date", S );
    //System.err.println(s);
    String checkString="id=id, customer=customer, provider=provider, start_date=start_date, end_date=end_date, services={service_id=id, type=type, targets=[type=type, unit=unit, restritions={total_limit=10.0, total_guaranteed=1, instance_guaranteed=1, instance_limit=100.0,user_guaranteed=1, user_limit=5.0}]}";
    //String output = s.toString( );
    //System.err.println(checkString);
    //System.err.println("Compare result="+s.toString( ).compareTo(checkString));
    assertTrue( 0==s.toString( ).compareTo(checkString) );
  }
}
