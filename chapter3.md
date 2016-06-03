# Ranking algorithm
The CloudProviderRanker follows this protocol to rank the providers coming from the orchestrator:
1. It check if preferences have been specified; if they have, then they have absolute priority over any other provider's status (like monitoring data). 
2. Otherwise a rank will be calculated for each provider inserted in the "sla" JSON block coming from the orchestrator

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
    
In addition to the SLA, the JSON can have monitoring information that is used for the providers' ranking. In the following an example:

```"monitoring": [{
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
        
        }]```
        

        
        


