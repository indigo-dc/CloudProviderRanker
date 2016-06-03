package org.indigo.cloudproviderruleengine.test;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.indigo.cloudproviderruleengine.SlaNormalizations;

public class SlaNormalizationsTest {
  @Test
  public void test( ) {
    SlaNormalizations sn = new SlaNormalizations( 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1000.0f );    
    assertTrue(null!=sn);
  }
}
