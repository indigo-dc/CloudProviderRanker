
## API

(work in progress)

The provided API are:
* ```/rank```
* ```/get-sla-parameters```
* ```/get-paas-parameters```
* ```/custom-sla-parameters```
* ```/custom-paas-parameters```



### ```/rank```
This has been already introduced in the [Install and run Cloud Provider Ranker](chapter2.md) chapter.
It performs the ranking of cloud providers based on an internal algorithm which uses weights and normalization parameters (described in the [Ranking Algorithm](chapter4.md) chapter). The payload the client must send in a POST HTTP request is described in the [Ranking Algorithm](chapter4.md) chapter. In the [Ranking JSON Request format](chapter8.md) chapter is shown the request the user must submit to the ```/rank``` API, and in the [Ranking JSON response format](chapter9.md) chapter is described the format of the returned response.

### ```/get-sla-parameters```
This API returns the priority parameters used by the ranking algorithm to weight the SLA's values. They can be customised to give an higher priority, for example, to the number of public IP a user can obtain from a certain provider with respect to the computing time or other entities, or vice-versa. The service CloudProviderRanker is distributed with default parameters that are loaded at the service's startup.
This API doesn't accept any payload and must be contacted using the GET HTTP request.

### ```/get-paas-parameters```
This API returns the normalization parameters used by the ranking algorithm to weight the PaaS monitoring's values. They can be customised to give an higher priority, for example, to the VM creation availability with OCCI interface with respect VM creation response time, or vice-versa. The service CloudProviderRanker is distributed with default normalization that are loaded at the service's startup.
This API doesn't accept any payload and must be contacted using the GET HTTP request.
### ```/custom-sla-parameters```
This API enables user to change the SLA's priority parameters as outlined above. He/she must send a POST HTTP request with the following payload to override from zero to all parameters:
```
{
    "computing_time":0.0166,
    "num_cpus":1,
    "mem_size":1,
    "disk_size":1,
    "public_ip":1,
    "upload_bandwidth":1,
    "download_bandwidth":1,
    "upload_aggregated":1,
    "download_aggregated":1,
    "infinity_value":1000
}
```
Not all parameters need to be specified. Those specified will be saved in a custom paas normalization file which the CloudProviderRanker will use to override the appearing parameters' values.

### ```/custom-paas-parameters```

The same considerations of the previous API apply to this one. The payload must be:
```
{
"OCCI_Create_VM_availability":"1",
"OCCI_CreateVM_Response_Time":"0.001",
"OCCI_CreateVM_Result":"1",
"OCCI_Delete_VM_Availability":"1",
"OCCI_DeleteVM_Response_Time":"0.001",
"OCCI_DeleteVM_Result":"1",
"General_OCCI_API_Availability":"1",
"General_OCCI_API_Response_Time":"0.001",
"General_OCCI_API_Result":"1",
"OCCI_Inspect_VM_availability":"1",
"OCCI_InspectVM_Response_Time":"0.001",
"OCCI_InspectVM_Result":"1"
}
```
Also in this case not all parameters need to be specified.

