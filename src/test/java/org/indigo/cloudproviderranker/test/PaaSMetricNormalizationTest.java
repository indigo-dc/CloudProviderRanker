package org.indigo.cloudproviderranker.test;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.indigo.cloudproviderranker.PaaSMetricNormalization;

public class PaaSMetricNormalizationTest {
  @Test
  public void test( ) {
    PaaSMetricNormalization.normalization_file = "paasmetric_normalization.json";
    PaaSMetricNormalization p = new PaaSMetricNormalization( true );
    PaaSMetricNormalization tmp = new PaaSMetricNormalization( false );
    
    if(null != p) {
      assertEquals( p.getocci_create_vm_availability(), 1, 0 );
    }
    String checkString = "[OCCI_Create_VM_availability=1,OCCI_CreateVM_Response_Time=0.001,OCCI_CreateVM_Result=1,OCCI_Delete_VM_Availability=1,OCCI_DeleteVM_Response_Time=0.001,OCCI_DeleteVM_Result=1,General_OCCI_API_Availability=1,General_OCCI_API_Response_Time=0.001,General_OCCI_API_Result=1,OCCI_Inspect_VM_availability=1,OCCI_InspectVM_Response_Time=0.001,OCCI_InspectVM_Result=1]";
    //System.err.println(p.toString());
    assertTrue(p.toString( ).endsWith( checkString ) );
    p.printParams( );
    String getP = p.getParams( );
    p.toCustomFile( );
    p.setocciCreatevmAvailability( 1 );
    p.setocciCreatevmResponseTime( 1.0f );
    p.setocciCreatevmResult( 1 );
    p.setocciDeletevmAvailability( 1 );
    p.setocciDeletevmResponseTime( 1.0f );
    p.setocciDeletevmResult( 1 );
    p.setgeneralOcciApiAvailability( 1 );
    p.setgeneralOcciApiResponseTime( 1.0f );
    p.setgeneralOcciApiResult( 1 );
    p.setocciInspectVmAvailability( 1 );
    p.setocciInspectVmResponseTime( 1.0f );
    p.setocciInspectVmResult( 1 );
    p.getocci_create_vm_availability( );
    p.getocciCreatevmResponseTime( );
    p.getocciCreatevmResult( );
    p.getocciDeletevmAvailability( );
    p.getocciDeletevmResponseTime( );
    p.getocciDeletevmResult( );
    p.getgeneralOcciApiAvailability( );
    p.getgeneralOcciApiResponseTime( );
    p.getgeneralOcciApiResult( );
    p.getocciInspectVmAvailability( );
    p.getocciInspectVmResponseTime( );
    p.getocciInspectVmResult( );
  }
}
