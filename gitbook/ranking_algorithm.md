# Ranking algorithm

## Ranking protocol overview

The Cloud Provider Ranker follows this protocol to rank the services listed in the input request:

the services are ordered 

The ranking algorithm uses the following information:

- SLA weight
- SLA targets
- monitoring metrics

The services are sorted using first of all the SLA weight. If two services have the same weight, then they are compared computing a score that combines the SLA targets and the monitoring metrics.

The computation of the score is performed through the embedded Rule Engine (based on Drools) and can be modified without re-compiling the code by changing the Rules files (see [Service Configuration](service_configuration.md)); then you need just to restart the service and the new rules will be applied.

The Rules files must be compliant with the [Drools Rule Language](https://www.drools.org/learn/documentation.html). 


