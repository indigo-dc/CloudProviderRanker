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
    StringBufferInputStream s = new StringBufferInputStream("{\"occi_create_vm_availability\":\"1\",\"occi_Createvm_Response_Time\":\"0.001\",\"occi_createvm_Result\":\"1\",\"occi_delete_vm_Availability\":\"1\",\"occi_deletevm_Response_Time\":\"0.001\",\"occi_deletevm_Result\":\"1\",\"General_occi_api_Availability\":\"1\",\"general_occi_api_Response_Time\":\"0.001\",\"general_occi_api_Result\":\"1\",\"occi_inspect_vm_availability\":\"1\",\"occi_inspectvm_Response_Time\":\"0.001\",\"occi_inspectvm_Result\":\"1\"}");
    //String p = "{\"occi_Create_VM_availability\":\"1\",\"occi_CreateVM_Response_Time\":\"0.001\",\"occi_CreateVM_Result\":\"1\",\"occi_Delete_VM_Availability\":\"1\",\"occi_DeleteVM_Response_Time\":\"0.001\",\"occi_DeleteVM_Result\":\"1\",\"General_occi_API_Availability\":\"1\",\"General_occi_API_Response_Time\":\"0.001\",\"General_occi_API_Result\":\"1\",\"occi_Inspect_VM_availability\":\"1\",\"occi_InspectVM_Response_Time\":\"0.001\",\"occi_InspectVM_Result\":\"1\"}";

    cpp.updateParams( s );

    if(cpp!=null) {
	ParseResult response = new ParseResult( "{\"response\":\"ok\"}", 200 );	
    }
    
  }
}
