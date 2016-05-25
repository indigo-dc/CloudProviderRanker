package org.indigo.cloudproviderruleengine.test;

import org.junit.Test;
import static org.junit.Assert.*;
import org.indigo.cloudproviderruleengine.VersionHandler;

public class VersionHandlerTest {
  @Test
  public void test( ) {
    VersionHandler v = new VersionHandler();
    //System.err.println("v="+v);
    //v=null;
    //System.err.println("v="+v);
    assertFalse(v==null);
  }
}
