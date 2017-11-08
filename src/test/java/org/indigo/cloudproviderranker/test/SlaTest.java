package org.indigo.cloudproviderranker.test;

import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.channels.FileChannel;

import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import org.junit.rules.TemporaryFolder;
import org.indigo.cloudproviderranker.Sla;
import org.indigo.cloudproviderranker.Service;
import org.indigo.cloudproviderranker.Target;
import org.indigo.cloudproviderranker.Restrictions;
import org.indigo.cloudproviderranker.SlaNormalizations;
import com.google.gson.*;


public class SlaTest {
  Restrictions restrictions;
  ArrayList<String> targetTypes;
  JsonObject sla_input_obj;
  String rules_file;

  @Rule
  public TemporaryFolder tmp_dir = new TemporaryFolder();

  @Before
  public void prepare() throws Exception {
    Restrictions r = new Restrictions( );
    r.totalLimit = 10;
    r.totalGuaranteed = 1;
    r.instanceLimit = 100;
    r.instanceGuaranteed = 1;
    r.userLimit = 5;
    r.userGuaranteed = 1;
    this.restrictions = r;

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
    this.targetTypes = targetTypes;

    Gson gson = new Gson();
    String line = "{\"sla\": [{\"customer\": \"indigo-dc\",\"provider\": \"provider-UPV-GRyCAP\",\"start_date\": \"11.01.2016+15:50:00\",\"end_date\": \"11.02.2016+15:50:00\", \"services\": [{\"type\": \"compute\",\"service_id\": \"4401ac5dc8cfbbb737b0a02575e6f4bc\",\"targets\": [{ \"type\": \"public_ip\", \"unit\": \"none\", \"restrictions\": { \"total_guaranteed\": 10 } }] }], \"id\": \"4401ac5dc8cfbbb737b0a02575ee3b58\" }, { \"customer\": \"indigo-dc\", \"provider\": \"provider-RECAS-BARI\", \"start_date\": \"11.01.2016+15:50:00\", \"end_date\": \"11.02.2016+15:50:00\", \"services\": [{ \"type\": \"compute\", \"service_id\": \"4401ac5dc8cfbbb737b0a02575e8040f\", \"targets\": [{ \"type\": \"computing_time\", \"unit\": \"h\", \"restrictions\": { \"total_guaranteed\": 200 } }]  }],\"id\": \"4401ac5dc8cfbbb737b0a02575ee53f6\"}]}";
    JsonElement E = gson.fromJson(line, JsonElement.class);
    sla_input_obj = E.getAsJsonObject( );

    // sample rules file
    ClassLoader classLoader = getClass().getClassLoader();
    File in_file = new File(classLoader.getResource("rules.drl").getFile());
    File out_file = tmp_dir.newFile("rules.drl");
    FileChannel in_ch = new FileInputStream(in_file).getChannel();
    FileChannel out_ch = new FileOutputStream(out_file).getChannel();
    out_ch.transferFrom(in_ch, 0, in_ch.size());
    rules_file = out_file.getPath();
  }

  @Test
  public void testRestrictions() {
    assertNotNull( restrictions );
    assertEquals( 10, restrictions.totalLimit, 0.1 );
  }

  @Test
  public void test( ) throws UnsupportedEncodingException {

    for(String type : targetTypes) {

      ArrayList<Target> t = new ArrayList<Target>();
      Target T = new Target();
      T.type = type;
      T.unit = "unit";
      T.restrictions = restrictions;
      t.add(T);

      assertNotEquals("", T.type);

      ArrayList<Service> s = new ArrayList<Service>( );

      Service S = new Service("id", "type", t);
      assertEquals("type", S.type);

      s.add(S);
      SlaNormalizations.priority_file = "sla_priorities.json";

      // load default rules
      Sla.rules_file = "";
      Sla.loadRules();

      Sla sla = new Sla("id", "customer", "provider", "start_date", "end_date", s );
      assertTrue( sla.toString().compareTo("") != 0);
      assertTrue( sla.rank != 0 );

      ArrayList<Sla> slas = Sla.fromJsonObject( sla_input_obj );

      assertTrue( slas.size() == 2);
      assertEquals( 3010.0, slas.get(0).rank, 0.01 );
      assertEquals( 53.12, slas.get(1).rank, 0.001 );

      // use rules file
      Sla.rules_file = rules_file;
      Sla.loadRules();

      slas = Sla.fromJsonObject( sla_input_obj );

      assertTrue( slas.size() == 2);
      assertEquals( 2010.0, slas.get(0).rank, 0.01 );
      assertEquals( 36.52, slas.get(1).rank, 0.001 );
    }
  }
}
