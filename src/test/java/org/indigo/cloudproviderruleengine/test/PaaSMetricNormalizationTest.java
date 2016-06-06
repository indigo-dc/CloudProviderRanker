package org.indigo.cloudproviderruleengine.test;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.indigo.cloudproviderruleengine.PaaSMetricNormalization;

public class PaaSMetricNormalizationTest {
  @Test
  public void test( ) {
    PaaSMetricNormalization p = new PaaSMetricNormalization(1,0.001f,1,1,0.001f,1,1,0.001f,1,1,0.001f,1);
    if(null != p)
      assertEquals( p.OCCI_Create_VM_availability, 1, 0 );
  }

}
