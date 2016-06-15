package org.indigo.cloudproviderranker.test;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.indigo.cloudproviderranker.Sla;
import org.indigo.cloudproviderranker.Service;
import org.indigo.cloudproviderranker.Target;
import org.indigo.cloudproviderranker.Restrictions;
import org.indigo.cloudproviderranker.SlaNormalizations;
import com.google.gson.*;


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

    if(r!=null)
      assertTrue(r.total_limit==10);
    
    ArrayList<String> targetTypes = new ArrayList<String>();
    targetTypes.add( "public_ip" );
    targetTypes.add( "computing_time" );
    targetTypes.add( "num_cpus" );
    targetTypes.add( "mem_size" );
    targetTypes.add( "disk_size" );
    targetTypes.add( "upload_bandwidth" );
    targetTypes.add( "download_bandwidth" );
    targetTypes.add( "upload_aggregated" );
    targetTypes.add( "download_aggregated" );    

    for(String type : targetTypes) {

	ArrayList<Target> t = new ArrayList<Target>();
	Target T = new Target();
	T.type = type;
	T.unit = "unit";
	T.restrictions = r;
	t.add(T);
    
	if(T!=null)
	    assertTrue(T.type.compareTo("")!=0);   
       
	//Service S = new Service();
	ArrayList<Service> s = new ArrayList<Service>( );
	
	Service S = new Service("id", "type", t);
	if(S!=null)
	    assertTrue(S.type.compareTo("type")==0);
	
	s.add(S);
	SlaNormalizations.priority_file = "sla_priorities.json";
	Sla sla = new Sla("id", "customer", "provider", "start_date", "end_date", s );
	sla.reloadPriorityFile( );
	assertTrue( sla.toString().compareTo("") != 0);
/*
  	String slaTestString = "{\"sla\":[{\"customer\":\"customer1\", \"provider\":\"provider1\",\"id\":\"1\",\"services\":[{\"type\":\"compute\",\"service_id\":\"1\",\"targets\": [{\"type\":\"computing_time\",\"unit\":\"h\", \"restrictions\": {}}]}]}]}";
	JsonParser parser = new JsonParser();
	JsonElement jsonElement = parser.parse( slaTestString );
        JsonObject obj = jsonElement.getAsJsonObject( );
        SlaNormalizations.priority_file = "sla_priorities.json";
 	ArrayList<Sla> _slas = Sla.fromJsonObject( obj );	*/
    }
  }
}
