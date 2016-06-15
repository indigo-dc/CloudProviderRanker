 package org.indigo.cloudproviderranker;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class PaaSMetricNormalization {
  public int   OCCI_Create_VM_availability 	= 1;
  public float OCCI_CreateVM_Response_Time 	= (float)0.001;
  public int   OCCI_CreateVM_Result 		= 1;
  public int   OCCI_Delete_VM_Availability 	= 1;
  public float OCCI_DeleteVM_Response_Time 	= (float)0.001;
  public int   OCCI_DeleteVM_Result 		= 1;
  public int   General_OCCI_API_Availability	= 1;
  public float General_OCCI_API_Response_Time 	= (float)0.001;
  public int   General_OCCI_API_Result 		= 1;
  public int   OCCI_Inspect_VM_availability 	= 1;
  public float OCCI_InspectVM_Response_Time 	= (float)0.001;
  public int   OCCI_InspectVM_Result 		= 1;

  public PaaSMetricNormalization( int   OCCI_Create_VM_availability,
				  float OCCI_CreateVM_Response_Time,
				  int   OCCI_CreateVM_Result,
				  int   OCCI_Delete_VM_Availability,
				  float OCCI_DeleteVM_Response_Time,
				  int   OCCI_DeleteVM_Result,
				  int   General_OCCI_API_Availability,
				  float General_OCCI_API_Response_Time,
				  int   General_OCCI_API_Result,
				  int   OCCI_Inspect_VM_availability,
				  float OCCI_InspectVM_Response_Time,
				  int   OCCI_InspectVM_Result )
  { 
    this.OCCI_Create_VM_availability    = OCCI_Create_VM_availability;
    this.OCCI_CreateVM_Response_Time    = OCCI_CreateVM_Response_Time;
    this.OCCI_CreateVM_Result 		= OCCI_CreateVM_Result;
    this.OCCI_Delete_VM_Availability    = OCCI_Delete_VM_Availability;
    this.OCCI_DeleteVM_Response_Time    = OCCI_DeleteVM_Response_Time;
    this.OCCI_DeleteVM_Result 		= OCCI_DeleteVM_Result;
    this.General_OCCI_API_Availability  = General_OCCI_API_Availability;
    this.General_OCCI_API_Response_Time = General_OCCI_API_Response_Time;
    this.General_OCCI_API_Result 	= General_OCCI_API_Result;
    this.OCCI_Inspect_VM_availability   = OCCI_Inspect_VM_availability;
    this.OCCI_InspectVM_Response_Time   = OCCI_InspectVM_Response_Time;
    this.OCCI_InspectVM_Result 		= OCCI_InspectVM_Result;
  }
  
  @Override
  public String toString( ) {
    return ToStringBuilder.reflectionToString(this);
  }
}
