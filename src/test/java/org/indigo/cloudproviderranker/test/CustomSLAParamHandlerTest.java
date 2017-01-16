package org.indigo.cloudproviderranker.test;

import org.junit.Test;
import static org.junit.Assert.*;
import org.indigo.cloudproviderranker.CustomSLAParamHandler;
import org.indigo.cloudproviderranker.ParseResult;
import java.io.StringBufferInputStream;

public class CustomSLAParamHandlerTest {
  @Test
  public void test( ) {
    CustomSLAParamHandler cpp = new CustomSLAParamHandler();
    StringBufferInputStream s = new StringBufferInputStream("{\"computing_time\":0.0166,\"num_cpus\":1,\"mem_size\":1,\"disk_size\":1,\"public_ip\":1,\"upload_bandwidth\":1,\"download_bandwidth\":1,\"upload_aggregated\":1,\"download_aggregated\":1,\"infinity_value\":1000}");
    
    //String newParams = "{\"computing_time\":0.0166,\"num_cpus\":1,\"mem_size\":1,\"disk_size\":1,\"public_ip\":1,\"upload_bandwidth\":1,\"download_bandwidth\":1,\"upload_aggregated\":1,\"download_aggregated\":1,\"infinity_value\":1000}";

    cpp.updateParams( s/*newParams*/ );

    if(cpp!=null) {
	ParseResult response = new ParseResult( "{\"response\":\"ok\"}", 200 );	
    }
    
  }
}
