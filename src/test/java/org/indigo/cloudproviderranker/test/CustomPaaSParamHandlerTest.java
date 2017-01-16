package org.indigo.cloudproviderranker.test;

import org.junit.Test;
import static org.junit.Assert.*;
import org.indigo.cloudproviderranker.CustomPaaSParamHandler;
import org.indigo.cloudproviderranker.ParseResult;
import java.io.StringBufferInputStream;

public class CustomPaaSParamHandlerTest {
  @Test
  public void test( ) {
    CustomPaaSParamHandler cpp = new CustomPaaSParamHandler();
    StringBufferInputStream s = new StringBufferInputStream("{\"OCCI_Create_VM_availability\":\"1\",\"OCCI_CreateVM_Response_Time\":\"0.001\",\"OCCI_CreateVM_Result\":\"1\",\"OCCI_Delete_VM_Availability\":\"1\",\"OCCI_DeleteVM_Response_Time\":\"0.001\",\"OCCI_DeleteVM_Result\":\"1\",\"General_OCCI_API_Availability\":\"1\",\"General_OCCI_API_Response_Time\":\"0.001\",\"General_OCCI_API_Result\":\"1\",\"OCCI_Inspect_VM_availability\":\"1\",\"OCCI_InspectVM_Response_Time\":\"0.001\",\"OCCI_InspectVM_Result\":\"1\"}");
    //String p = "{\"OCCI_Create_VM_availability\":\"1\",\"OCCI_CreateVM_Response_Time\":\"0.001\",\"OCCI_CreateVM_Result\":\"1\",\"OCCI_Delete_VM_Availability\":\"1\",\"OCCI_DeleteVM_Response_Time\":\"0.001\",\"OCCI_DeleteVM_Result\":\"1\",\"General_OCCI_API_Availability\":\"1\",\"General_OCCI_API_Response_Time\":\"0.001\",\"General_OCCI_API_Result\":\"1\",\"OCCI_Inspect_VM_availability\":\"1\",\"OCCI_InspectVM_Response_Time\":\"0.001\",\"OCCI_InspectVM_Result\":\"1\"}";

    cpp.updateParams( s );

    if(cpp!=null) {
	ParseResult response = new ParseResult( "{\"response\":\"ok\"}", 200 );	
    }
    
  }
}
