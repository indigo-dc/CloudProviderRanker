
## API

(work in progress)

The provided API are:
* ```/rank```
* ```/get-sla-parameters```
* ```/get-paas-parameters```
* ```/custom-sla-parameters```
* ```/custom-paas-parameters```



### /rank
This has been already introduced in the [Install and run Cloud Provider Ranker](chapter2.md) chapter.
It performs the ranking of cloud providers based on an internal algorithm which uses weights and normalization parameters (described in the [Ranking Algorithm](chapter4.md) chapter). The payload the client must send in a POST HTTP request is described in the [Ranking Algorithm](chapter4.md) chapter. In the [Ranking JSON Request format](chapter8.md) chapter is shown the request the user must submit to the ```/rank``` API, and in the [Ranking JSON response format](chapter9.md) chapter is described the format of the returned response.

### /get-sla-parameters
This API returns the priority parameters used by the ranking algorithm to weight the SLA's values. They can be customised to give an higher priority, for example, to the number of public IP a user can obtain from a certain provider with respect to the computing time or other entities, or vice-versa. The service CloudProviderRanker is distributed with default parameters that are loaded at the service's startup.
This API doesn't accept any payload and must be contacted using the GET HTTP request.

### /get-paas-parameters
This API returns the normalization parameters used by the ranking algorithm to weight the PaaS monitoring's values. They can be customised to give an higher priority, for example, to the VM creation availability with OCCI interface with respect VM creation response time, or vice-versa. The service CloudProviderRanker is distributed with default normalization that are loaded at the service's startup.
This API doesn't accept any payload and must be contacted using the GET HTTP request.
### /custom-sla-parameters
### /custom-paas-parameters



