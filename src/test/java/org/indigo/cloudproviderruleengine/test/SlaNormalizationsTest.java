package org.indigo.cloudproviderruleengine.test;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.indigo.cloudproviderruleengine.SlaNormalizations;

public class SlaNormalizationsTest {
  @Test
  public void test( ) {
    SlaNormalizations sn = new SlaNormalizations( (float)1.0, (float)1.0, (float)1.0, (float)1.0, (float)1.0, (float)1.0, (float)1.0, (float)1.0, (float)1.0 );    
    assertTrue(null!=sn);
  }
}
