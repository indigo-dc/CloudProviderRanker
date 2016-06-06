package org.indigo.cloudproviderruleengine.test;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.indigo.cloudproviderruleengine.PaaSMetricRanked;

public class PaaSMetricRankedTest {
  @Test
  public void test( ) {
    PaaSMetricRanked p = new PaaSMetricRanked( );
    if(null!=p)
      assertTrue( null != p );
  }

}
