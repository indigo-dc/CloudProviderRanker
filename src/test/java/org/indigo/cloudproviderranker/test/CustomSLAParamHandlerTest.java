package org.indigo.cloudproviderranker.test;

import org.junit.Test;
import static org.junit.Assert.*;
import org.indigo.cloudproviderranker.CustomSLAParamHandler;
import org.indigo.cloudproviderranker.ParseResult;

public class CustomSLAParamHandlerTest {
  @Test
  public void test( ) {
    CustomSLAParamHandler cpp = new CustomSLAParamHandler();
    
    if(cpp!=null) {
	ParseResult response = new ParseResult( "{\"response\":\"ok\"}", 200 );	
    }
    
  }
}
