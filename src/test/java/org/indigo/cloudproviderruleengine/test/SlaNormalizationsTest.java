package org.indigo.cloudproviderruleengine.test;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.indigo.cloudproviderruleengine.SlaNormalizations;

public class SlaNormalizationsTest {
  @Test
  public void test( ) {
    SlaNormalizations sn = new SlaNormalizations( 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1000.0f );    
    if(null!=sn)
	assertEquals( sn.computing_time, 1.0, 0);
    
    SlaNormalizations.priority_file = null;
    sn = SlaNormalizations.fromFile( );
    assertTrue(null==sn);
    SlaNormalizations.priority_file = "sla_priorities.json";
    sn = SlaNormalizations.fromFile( );
    assertTrue(null!=sn);
    SlaNormalizations.priority_file = "non_existing_file.json";
    sn = SlaNormalizations.fromFile( );
    if(null!=sn)
	assertEquals( sn.computing_time, 1.0f, 0.00001f);
  }
}
