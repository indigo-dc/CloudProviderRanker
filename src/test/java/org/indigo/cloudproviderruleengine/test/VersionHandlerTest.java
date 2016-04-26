package org.indigo.cloudproviderruleengine.test;

import org.junit.Test;
import static org.junit.Assert.*;
import org.indigo.cloudproviderruleengine.VersionHandler;

public class VersionHandlerTest {
  @Test
  public void testVersionHandlerTest( ) {
    VersionHandler v = new VersionHandler();
    assertNotEquals(null,v);
  }
}
