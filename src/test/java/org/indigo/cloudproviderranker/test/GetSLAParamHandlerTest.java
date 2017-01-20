package org.indigo.cloudproviderranker.test;

import org.junit.Test;
import static org.junit.Assert.*;
import org.indigo.cloudproviderranker.GetSlaParamHandler;
import org.indigo.cloudproviderranker.ParseResult;

public class GetSLAParamHandlerTest {
  @Test
  public void test( ) {
    GetSlaParamHandler cpp = new GetSlaParamHandler();
    
//    String newParams = "{\"computing_time\":0.0166,\"num_cpus\":1,\"mem_size\":1,\"disk_size\":1,\"public_ip\":1,\"upload_bandwidth\":1,\"download_bandwidth\":1,\"upload_aggregated\":1,\"download_aggregated\":1,\"infinity_value\":1000}";

    cpp.getParams( );

    if(cpp!=null) {
	ParseResult response = new ParseResult( "{\"response\":\"ok\"}", 200 );	
    }
    
  }
}
