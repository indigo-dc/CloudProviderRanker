package org.indigo.cloudproviderranker;

public final class CustomPaaSParams {

    public static final String OCCI_CREATE_VM_AVAILABILITY = "OCCI_Create_VM_availability";
    public static final String OCCI_CREATEVM_RESPONSE_TIME = "OCCI_CreateVM_Response_Time";
    public static final String OCCI_CREATEVM_RESULT = "OCCI_CreateVM_Result";
    public static final String OCCI_DELETE_VM_AVAILABILITY = "OCCI_Delete_VM_Availability";
    public static final String OCCI_DELETEVM_RESPONSE_TIME = "OCCI_DeleteVM_Response_Time";
    public static final String OCCI_DELETEVM_RESULT = "OCCI_DeleteVM_Result";
    public static final String GENERAL_OCCI_API_AVAILABILITY = "General_OCCI_API_Availability";
    public static final String GENERAL_OCCI_API_RESPONSE_TIME = "General_OCCI_API_Response_Time";
    public static final String GENERAL_OCCI_API_RESULT = "General_OCCI_API_Result";
    public static final String OCCI_INSPECT_VM_AVAILABILITY = "OCCI_Inspect_VM_availability";
    public static final String OCCI_INSPECTVM_RESPONSE_TIME = "OCCI_InspectVM_Response_Time";
    public static final String OCCI_INSPECTVM_RESULT = "OCCI_InspectVM_Result";

    /*
       Callers are prevented from constructing objects of this class,
       by declaring this private constructor.
    */
    private CustomPaaSParams() {
        throw new AssertionError();
    }
}
