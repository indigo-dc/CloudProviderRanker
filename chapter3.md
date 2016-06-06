# Ranking algorithm
## Ranking protocolo overview
The CloudProviderRanker follows this protocol to rank the providers coming from the orchestrator:
1. It checks if preferences have been specified; if they have, then they have absolute priority over any other provider's status (like monitoring data). 
2. Otherwise a rank will be calculated for each provider inserted in the "sla" JSON block coming from the orchestrator.

In the following an example of "preferences":

```"preferences": [{
       "service_type": "compute",
       "priority": [{
          "sla_id": "4401ac5dc8cfbbb737b0a02575ee53f6",
          "service_id": "4401ac5dc8cfbbb737b0a02575e8040f",
          "weight": 0.5
       }, {
          "sla_id": "4401ac5dc8cfbbb737b0a02575ee3b58",
          "service_id": "4401ac5dc8cfbbb737b0a02575e6f4bc",
          "weight": 0.5
       }]
     }]```
    
In the following an example of "sla":
```"sla": [{
		"customer": "indigo-dc",
		"provider": "provider-UPV-GRyCAP",
		"start_date": "11.01.2016+15:50:00",
		"end_date": "11.02.2016+15:50:00",
		"services": [{
			"type": "compute",
			"service_id": "4401ac5dc8cfbbb737b0a02575e6f4bc",
			"targets": [{
				"type": "public_ip",
				"unit": "none",
				"restrictions": {
					"total_guaranteed": 10
				}
			}]
		}],
		"id": "4401ac5dc8cfbbb737b0a02575ee3b58"
	}, {
		"customer": "indigo-dc",
		"provider": "provider-RECAS-BARI",
		"start_date": "11.01.2016+15:50:00",
		"end_date": "11.02.2016+15:50:00",
		"services": [{
			"type": "compute",
			"service_id": "4401ac5dc8cfbbb737b0a02575e8040f",
			"targets": [{
				"type": "computing_time",
				"unit": "h",
				"restrictions": {
					"total_guaranteed": 200
				}
			}]
		}],
		"id": "4401ac5dc8cfbbb737b0a02575ee53f6"
	}]```
    
In addition to the SLAs, the JSON can have monitoring information that may be used for the providers' ranking. In the following an example:

```
"monitoring": [{
		"provider": "provider-RECAS-BARI",
		"metrics": [{
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
        "metrics": [{ ...
        
        }]
```   

---

## Ranking without "preferences"
If preferences are not specified, for each provider the rank is calculated as sum of SLA's rank and a combination of monitoring data.

### SLA's rank
Each SLA in the JSON corresponds to **one and only one** cloud provider to rank.

SLA's rank is calculated as sum of its fields specified in this document: https://goo.gl/GZnl8P.

Each SLA can specify multiple type of services, and each type of service can specify an array of targets (```public_ip```, ```num_cpu```, ```computing_time```, etc..). Each target's has six restrictions:

```
total_guaranteed,
total_limit,
user_guaranteed,
user_limit,
instance_guaranteed,
instance_limit
```

If a ```*_guaranteed``` is missing in the request JSON, it is assumed to be 0. If a ```*_limit``` restriction is missing in the JSON request, it is assumed to be infinity.

Each restriction's value can be prioritized in an input file fed to the CloudProviderRanker when launching it; furthermore the ranker's admin must valorize the "infinity" value, which of course would be not usable as a number. In the following an example of priority file <> (see the "Installation, test and configuration" chapter) for the SLA:

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
}```

Prioritization can be conceived even as a "normalization" factor; the choice is up to the ranker's admin. 
The only thing to know is that the SLA's rank is calculated in this way:

```rank = (total_limit + total_guaranteed + user_limit + user_guaranteed
        + instance_total + instance_guaranteed ) * norm_factor```
        
and norm_factor is specified by the admin for each kind of target. Remember that _limit is the actual value or the "infinity_value" specified in the SLA prioritization file in case it was missing in the JSON request.
        
### TO BE CONTINUED


