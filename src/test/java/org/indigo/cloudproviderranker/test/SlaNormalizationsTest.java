package org.indigo.cloudproviderranker.test;

import java.util.ArrayList;
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
}
