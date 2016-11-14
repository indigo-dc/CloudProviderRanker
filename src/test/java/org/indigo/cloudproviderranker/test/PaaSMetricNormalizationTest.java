package org.indigo.cloudproviderranker.test;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.indigo.cloudproviderranker.PaaSMetricNormalization;

public class PaaSMetricNormalizationTest {
  @Test
  public void test( ) {
    PaaSMetricNormalization p = new PaaSMetricNormalization( true );
    PaaSMetricNormalization.normalization_file = "paasmetric_normalization.json";
    if(null != p)
      assertEquals( p.getOCCI_Create_VM_availability(), 1, 0 );
    String checkString = "[OCCI_Create_VM_availability=1,OCCI_CreateVM_Response_Time=0.001,OCCI_CreateVM_Result=1,OCCI_Delete_VM_Availability=1,OCCI_DeleteVM_Response_Time=0.001,OCCI_DeleteVM_Result=1,General_OCCI_API_Availability=1,General_OCCI_API_Response_Time=0.001,General_OCCI_API_Result=1,OCCI_Inspect_VM_availability=1,OCCI_InspectVM_Response_Time=0.001,OCCI_InspectVM_Result=1]";
    //System.err.println(p.toString());
    assertTrue(p.toString( ).endsWith( checkString ) );
  }

}
