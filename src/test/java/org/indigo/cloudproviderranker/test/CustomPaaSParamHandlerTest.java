package org.indigo.cloudproviderranker.test;

import org.junit.Test;
import static org.junit.Assert.*;
import org.indigo.cloudproviderranker.CustomPaaSParamHandler;
import org.indigo.cloudproviderranker.ParseResult;

public class CustomPaaSParamHandlerTest {
  @Test
  public void test( ) {
    CustomPaaSParamHandler cpp = new CustomPaaSParamHandler();
    
    if(cpp!=null) {
	ParseResult response = new ParseResult( "{\"response\":\"ok\"}", 200 );	
    }
    
  }
}
