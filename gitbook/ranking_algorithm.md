# Ranking algorithm

## Ranking protocol overview

The Cloud Provider Ranker follows this protocol to rank the providers
coming from the Orchestrator:

1. It checks if preferences have been specified; if they have, then they
   have absolute priority over any other provider's status (like
   monitoring data).

2. Otherwise a rank will be calculated for each provider inserted in the
   "sla" JSON block coming from the Orchestrator.

In the following an example of "preferences":

```
"preferences": [
    {
        "service_type": "compute",
        "priority": [
            {
                "sla_id": "4401ac5dc8cfbbb737b0a02575ee53f6",
                "service_id": "4401ac5dc8cfbbb737b0a02575e8040f",
                "weight": 0.5
            },
            {
                "sla_id": "4401ac5dc8cfbbb737b0a02575ee3b58",
                "service_id": "4401ac5dc8cfbbb737b0a02575e6f4bc",
                "weight": 0.5
            }
        ]
    }
]
```

In the following an example of "sla":

```
"sla": [
    {
        "customer": "indigo-dc",
	      "provider": "provider-UPV-GRyCAP",
	      "start_date": "11.01.2016+15:50:00",
	      "end_date": "11.02.2016+15:50:00",
	      "services": [
            {
	              "type": "compute",
		            "service_id": "4401ac5dc8cfbbb737b0a02575e6f4bc",
		            "targets": [
                    {
		                    "type": "public_ip",
			                  "unit": "none",
			                  "restrictions": {
			                      "total_guaranteed": 10
			                  }
		                }
                ]
            }
        ],
	      "id": "4401ac5dc8cfbbb737b0a02575ee3b58"
    },
    {
		    "customer": "indigo-dc",
		    "provider": "provider-RECAS-BARI",
		    "start_date": "11.01.2016+15:50:00",
		    "end_date": "11.02.2016+15:50:00",
		    "services": [
            {
			          "type": "compute",
			          "service_id": "4401ac5dc8cfbbb737b0a02575e8040f",
			          "targets": [
                    {
				                "type": "computing_time",
				                "unit": "h",
				                "restrictions": {
					                  "total_guaranteed": 200
				                }
			              }
                ]
		        }
        ],
		    "id": "4401ac5dc8cfbbb737b0a02575ee53f6"
	  }
]
```

In addition to the SLAs, the JSON can have monitoring information that
may be used for the providers' ranking. In the following an example:

```
"monitoring": [
    {
		    "provider": "provider-RECAS-BARI",
		    "metrics": [
            {
			          "metricName": "OCCI Create VM availability",
			          "metricKey": "Cloud_Providers.provider-RECAS-BARI..OCCI Create VM availability",
			          "metricValue": 1.0,
			          "metricTime": "Instant null because no metrics were returned in the last 24hs",
			          "metricUnit": "bit",
			          "paasThresholds": [],
			          "historyClocks": [],
			          "historyValues": []
		        },
            ...
        ],
        "provider": "..."
        "metrics": [ { ... } ]
    }
]
```

## Ranking without "preferences"

If preferences are not specified, for each provider the rank is
calculated as sum of SLA's rank and a combination of monitoring data.

## SLA's rank
Each SLA in the JSON corresponds to **one and only one** cloud provider to rank.

By default SLA's rank is calculated as sum of its fields specified in
this document: https://indigo-dc.gitbooks.io/slam/content/rest.html

Each SLA can specify multiple type of services, and each type of service
can specify an array of targets (`public_ip`, `num_cpu`,
`computing_time`, etc..). Each target's has six restrictions:

  * `total_guaranteed`
  * `total_limit`
  * `user_guaranteed`
  * `user_limit`
  * `instance_guaranteed`
  * `instance_limit`

If a `*_guaranteed` is missing in the request JSON, it is assumed to
be 0. If a `*_limit` restriction is missing in the JSON request, it is
assumed to be infinity.

Each restriction's value can be prioritized in an input file fed to the
Cloud Provider Ranker when launching it; furthermore the ranker's admin
must valorize the `infinity` value, which of course would be not usable
as a number.

In the following an example of priority file `<SLA_PRIORITY_FILE>` (see
the [Building from source](building.md) chapter) for the SLA:

```
{
    "computing_time": 0.0166,
    "num_cpus": 1,
    "mem_size": 1,
    "disk_size": 1,
    "public_ip": 1,
    "upload_bandwidth": 1,
    "download_bandwidth": 1,
    "upload_aggregated": 1,
    "download_aggregated": 1,
    "infinity_value": 1000
}
```

Prioritization can be conceived even as a "normalization"; the choice is
up to the ranker's admin.

The SLA's rank logic is described in the [Drools Rule
Language](https://www.drools.org/learn/documentation.html). Such logic
can be customized by specifying a `.drl` file using the `--rules-file`
command line option.  If the option is not given, then the default rules
(contained in the file `DefaultRules.drl`.

### Default rules

The content of the default rules file is the following:

```
package org.indigo.cloudproviderruleengine;

import org.indigo.cloudproviderranker.Sla;
import org.indigo.cloudproviderranker.Target;

rule "SLA"
  when
    $sla : Sla()
  then
    for( Target t : $sla.services.get(0).targets ) {
      float normalizationFactor = $sla.slaNormalizations.getByName(t.type);
      float infinityValue = $sla.slaNormalizations.getInfinityValue();

      $sla.rank += (
        (t.restrictions.totalLimit < Double.POSITIVE_INFINITY ? t.restrictions.totalLimit : infinityValue) + t.restrictions.totalGuaranteed
        +
        (t.restrictions.userLimit < Double.POSITIVE_INFINITY ? t.restrictions.userLimit : infinityValue) + t.restrictions.userGuaranteed
        +
        (t.restrictions.instanceLimit < Double.POSITIVE_INFINITY ? t.restrictions.instanceLimit : infinityValue) + t.restrictions.instanceGuaranteed
      ) * normalizationFactor;
    }
end
```

The SLA's rank, for a single target, is calculated in this way:

```
sla_rank = ( total_limit + total_guaranteed
           + user_limit + user_guaranteed
           + instance_total + instance_guaranteed
           ) * norm_factor
```

where `norm_factor` depends on the current target's type and is
specified by the admin in `<SLA_PRIORITY_FILE>` for each kind of target.

If a SLA contains multiple targets, all the `sla_rank` are added for a
final SLA's rank.

Remember that each `*_limit` is the actual value specified in the JSON
request or the `infinity_value` specified in the SLA prioritization file
in case it was missing in the JSON request.


## Monitoring based rank

If the JSON request contains also monitoring data, they are used
(together with the SLA information) to vote each cloud provider.

The monitoring information is an array of couple (`provider`,
 `metrics`).

The provider must be the same string `ID` as specified in each SLA.

The metrics is in turn an array of status information:

```
"monitoring": [
    {
        "provider": "provider-RECAS-BARI",
        "metrics": [...]
    },
    ...
]
```

Each metrics is a flat structure like this:

```
{
    "metricName": "OCCI Create VM availability",
    "metricKey": "Cloud_Providers.provider-RECAS-BARI..OCCI Create VM availability",
    "metricValue": 1.0,
    "metricTime": "Instant null because no metrics were returned in the last 24hs",
    "metricUnit": "bit",
    "paasThresholds": [],
    "historyClocks": [],
    "historyValues": []
}
```

For each metrics the relevant information is the `metricValue`.

As in the case of the SLAs' target types, for each `metricName` the
Ranker needs to know the normalization factor.

All the normalization factors must be specified in a file
(`<PAASMETRIC_NORMALIZATION_FILE>`) whose name is a mandatory command
line argument when launching the Cloud Provider Ranker server
(see [Building from source](building.md)).

In the following the content of the normalization file:

```
{
    "OCCI_Create_VM_availability": 1,
    "OCCI_CreateVM_Response_Time": 0.001,
    "OCCI_CreateVM_Result": 1,
    "OCCI_Delete_VM_Availability": 1,
    "OCCI_DeleteVM_Response_Time": 0.001,
    "OCCI_DeleteVM_Result": 1,
    "General_OCCI_API_Availability": 1,
    "General_OCCI_API_Response_Time": 0.001,
    "General_OCCI_API_Result": 1,
    "OCCI_Inspect_VM_availability": 1,
    "OCCI_InspectVM_Response_Time": 0.001,
    "OCCI_InspectVM_Result":1
}
```

For each metrics of one particular provider, the `metricValue` fields
are normalized multiplying them by the normalization factor which
relates to the `metricName` (as specified in the
`<PAASMETRIC_NORMALIZATION_FILE>`.

Then all the normalized `metricValue` are added (or subtracted in the case
of `*_Response_time`) to calculate the final rank for the current
provider.
