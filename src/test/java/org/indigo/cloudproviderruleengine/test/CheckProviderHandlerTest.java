package org.indigo.cloudproviderruleengine.test;

import org.junit.Test;
import static org.junit.Assert.*;
import org.indigo.cloudproviderruleengine.CheckProviderHandler;

public class CheckProviderHandlerTest {
  @Test
  public void testCheckProviderHandlerTest( ) {
    CheckProviderHandler v = new CheckProviderHandler();
    assertNotEquals(null,v);
  }
}
