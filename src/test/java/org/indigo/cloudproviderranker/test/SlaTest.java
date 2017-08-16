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
    r.totalLimit = 10;
    r.totalGuaranteed = 1;
    r.instanceLimit = 100;
    r.instanceGuaranteed = 1;
    r.userLimit = 5;
    r.userGuaranteed = 1;

    if(r!=null)
      assertTrue(r.totalLimit==10);

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

      ArrayList<Service> s = new ArrayList<Service>( );

      Service S = new Service("id", "type", t);
      if(S!=null)
        assertTrue(S.type.compareTo("type")==0);

      s.add(S);
      SlaNormalizations.priority_file = "sla_priorities.json";
      Sla sla = new Sla("id", "customer", "provider", "start_date", "end_date", s );
      assertTrue( sla.toString().compareTo("") != 0);
      assertTrue( sla.rank != 0 );

      Gson gson = new Gson();
      String line = "{\"sla\": [{\"customer\": \"indigo-dc\",\"provider\": \"provider-UPV-GRyCAP\",\"start_date\": \"11.01.2016+15:50:00\",\"end_date\": \"11.02.2016+15:50:00\", \"services\": [{\"type\": \"compute\",\"service_id\": \"4401ac5dc8cfbbb737b0a02575e6f4bc\",\"targets\": [{ \"type\": \"public_ip\", \"unit\": \"none\", \"restrictions\": { \"total_guaranteed\": 10 } }] }], \"id\": \"4401ac5dc8cfbbb737b0a02575ee3b58\" }, { \"customer\": \"indigo-dc\", \"provider\": \"provider-RECAS-BARI\", \"start_date\": \"11.01.2016+15:50:00\", \"end_date\": \"11.02.2016+15:50:00\", \"services\": [{ \"type\": \"compute\", \"service_id\": \"4401ac5dc8cfbbb737b0a02575e8040f\", \"targets\": [{ \"type\": \"computing_time\", \"unit\": \"h\", \"restrictions\": { \"total_guaranteed\": 200 } }]  }],\"id\": \"4401ac5dc8cfbbb737b0a02575ee53f6\"}]}";
      JsonElement E = gson.fromJson(line, JsonElement.class);
      JsonObject obj = E.getAsJsonObject( );

      ArrayList<Sla> slas = Sla.fromJsonObject( obj );

      assertTrue( slas.size() == 2);

      assertEquals( 3010.0, slas.get(0).rank, 0.01 );
      assertEquals( 53.12, slas.get(1).rank, 0.001 );
    }
  }
}
