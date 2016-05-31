package org.indigo.cloudproviderruleengine.test;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.indigo.cloudproviderruleengine.PaaSMetricNormalization;

public class PaaSMetricNormalizationTest {
  @Test
  public void test( ) {
    PaaSMetricNormalization p = new PaaSMetricNormalization(1,(float)0.001,1,1,(float)0.001,1,1,(float)0.001,1,1,(float)0.001,1);
    assertTrue( null != p );
  }

}
