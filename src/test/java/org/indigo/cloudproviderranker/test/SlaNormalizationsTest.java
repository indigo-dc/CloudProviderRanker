package org.indigo.cloudproviderranker.test;

import org.junit.Test;
import static org.junit.Assert.*;

import org.indigo.cloudproviderranker.SlaNormalizations;

public class SlaNormalizationsTest {
  @Test
  public void test( ) {
    SlaNormalizations sn = new SlaNormalizations( );// 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1000.0f );
    SlaNormalizations.priority_file = null;
    sn.fromDefaultFile( );
    sn.fromCustomFile( );   
    assertEquals( sn.get_computing_time(), 0.0166f, 0.000001f);
    
    sn = new SlaNormalizations( 3.5f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1000.0f );
    
    assertEquals( sn.get_computing_time(), 3.5f, 0.0001f);

    SlaNormalizations.priority_file = "sla_priorities.json";
    sn.fromDefaultFile( );
    sn.fromCustomFile( );
//    System.out.println("\n\ncomp tile=" + sn.get_computing_time() + "\n\n");
    assertEquals( sn.get_computing_time(), 0.0166f, 0.000001f);

    SlaNormalizations.priority_file = "non_existing_file.json";
    sn.fromDefaultFile( );
//    if(null!=sn)
//	assertEquals( sn.get_computing_time( ), 1.0f, 0.00001f);
//    sn = new SlaNormalizations( 7.5f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1000.0f );

    //assertEquals( sn.get_computing_time(), 7.5f, 0.0001f);
    sn.toCustomFile( );
    String p = sn.getParams( );
  }

  @Test
  public void testGetByName() {
    SlaNormalizations sn = new SlaNormalizations(0.0166f, 0.1f, 0.4f, 0.2f, 0.2f, 0.1f, 0.15f, 0.25f, 0.5f, 5000f);

    float value;

    value = sn.getByName("public_ip");
    assertEquals("public_ip", 0.2f, value, 0);

    value = sn.getByName("computing_time");
    assertEquals("computing_time", 0.0166f, value, 0);

    value = sn.getByName("num_cpus");
    assertEquals("num_cpus", 0.1f, value, 0);

    value = sn.getByName("mem_size");
    assertEquals("mem_size", 0.4f, value, 0);

    value = sn.getByName("disk_size");
    assertEquals("disk_size", 0.2f, value, 0);

    value = sn.getByName("upload_bandwidth");
    assertEquals("upload_bandwidth", 0.1f, value, 0);

    value = sn.getByName("download_bandwidth");
    assertEquals("download_bandwidth", 0.15f, value, 0);

    value = sn.getByName("upload_aggregated");
    assertEquals("upload_aggregated",  0.25f, value, 0);

    value = sn.getByName("download_aggregated");
    assertEquals("download_aggregated", 0.5f, value, 0);
  }
}
