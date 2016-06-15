package org.indigo.cloudproviderranker.test;

import org.junit.Test;
import static org.junit.Assert.*;
import org.indigo.cloudproviderranker.ServerException;

public class ServerExceptionTest {
  @Test
  public void testServerExceptionTest( ) {
    ServerException se = new ServerException("Test Error");
    assertNotEquals(null,se);
    assertEquals("Test Error", se.getMessage( ) );
  }
}
