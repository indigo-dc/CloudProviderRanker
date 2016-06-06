package org.indigo.cloudproviderruleengine.test;

import org.junit.Test;
import static org.junit.Assert.*;
import org.indigo.cloudproviderruleengine.RESTEngine;
import org.indigo.cloudproviderruleengine.ServerException;

public class RESTEngineTest {
  @Test
  public void testRESTEngineTest( ) {
    RESTEngine re = new RESTEngine( );
    try {
      re.initHttpServer( false, 30000, "", "" );
      
    } catch(ServerException se) {
      assertEquals("a", "z");
    }
  }
}
