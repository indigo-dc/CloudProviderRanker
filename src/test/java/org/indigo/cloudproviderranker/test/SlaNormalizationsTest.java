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
    //assertTrue(null==sn);
    SlaNormalizations.priority_file = "sla_priorities.json";
    sn.fromDefaultFile( );
    sn.fromCustomFile( );
    //assertTrue(null!=sn);
    SlaNormalizations.priority_file = "non_existing_file.json";
    sn.fromDefaultFile( );
//    if(null!=sn)
//	assertEquals( sn.get_computing_time( ), 1.0f, 0.00001f);
  }
}
