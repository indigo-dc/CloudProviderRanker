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
      //System.err.println("computing_time="+sn.computing_time);
      assertEquals( sn.computing_time, 1.0, 0);
  }
}
