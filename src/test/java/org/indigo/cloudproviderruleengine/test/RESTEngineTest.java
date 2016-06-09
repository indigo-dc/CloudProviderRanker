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
      re.initHttpServer( false, 9000, "", "" );
      re.initHttpServer( true, 9001, "server_keystore.ks", "abcdef" );
      
    } catch(ServerException se) {
      //assertEquals("a", "z");
    }
  }
}
