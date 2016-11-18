# Ranking JSON Request format
Below a general form of the JSON request (or payload) the user must send with a POST HTTP request to the CloudProviderRanker's ```/rank``` API. There're three main sections: ```preferences```, ```sla```, ```monitoring```. How they're handled by CloudProviderRanker to rank the cloud providers is described in the [Ranking Algorithm](chapter4.md) chapter.

```
{
    "preferences": [{
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
        }],
	"sla": [{
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
	}],
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
		}, {
			"metricName": "OCCI CreateVM Response Time",
			"metricKey": "Cloud_Providers.provider-RECAS-BARI..OCCI CreateVM Response Time",
			"metricValue": 10.0,
			"metricTime": "Instant null because no metrics were returned in the last 24hs",
			"metricUnit": "ms",
			"paasThresholds": [],
			"historyClocks": [],
			"historyValues": []
		}, {
			"metricName": "OCCI CreateVM Result",
			"metricKey": "Cloud_Providers.provider-RECAS-BARI..OCCI CreateVM Result",
			"metricValue": 0.0,
			"metricTime": "Instant null because no metrics were returned in the last 24hs",
			"metricUnit": "bit",
			"paasThresholds": [],
			"historyClocks": [],
			"historyValues": []
		}, {
			"metricName": "OCCI Delete VM Availability",
			"metricKey": "Cloud_Providers.provider-RECAS-BARI..OCCI Delete VM Availability",
			"metricValue": 0.0,
			"metricTime": "Instant null because no metrics were returned in the last 24hs",
			"metricUnit": "bit",
			"paasThresholds": [],
			"historyClocks": [],
			"historyValues": []
		}, {
			"metricName": "OCCI DeleteVM Response Time",
			"metricKey": "Cloud_Providers.provider-RECAS-BARI..OCCI DeleteVM Response Time",
			"metricValue": 20.0,
			"metricTime": "Instant null because no metrics were returned in the last 24hs",
			"metricUnit": "ms",
			"paasThresholds": [],
			"historyClocks": [],
			"historyValues": []
		}, {
			"metricName": "OCCI DeleteVM Result",
			"metricKey": "Cloud_Providers.provider-RECAS-BARI..OCCI DeleteVM Result",
			"metricValue": 1.0,
			"metricTime": "Instant null because no metrics were returned in the last 24hs",
			"metricUnit": "bit",
			"paasThresholds": [],
			"historyClocks": [],
			"historyValues": []
		}, {
			"metricName": "General OCCI API Availability",
			"metricKey": "Cloud_Providers.provider-RECAS-BARI..General OCCI API Availability",
			"metricValue": 1.0,
			"metricTime": "Instant null because no metrics were returned in the last 24hs",
			"metricUnit": "bit",
			"paasThresholds": [],
			"historyClocks": [],
			"historyValues": []
		}, {
			"metricName": "General OCCI API Response Time",
			"metricKey": "Cloud_Providers.provider-RECAS-BARI..General OCCI API Response Time",
			"metricValue": 30.0,
			"metricTime": "Instant null because no metrics were returned in the last 24hs",
			"metricUnit": "ms",
			"paasThresholds": [],
			"historyClocks": [],
			"historyValues": []
		}, {
			"metricName": "General OCCI API Result",
			"metricKey": "Cloud_Providers.provider-RECAS-BARI..General OCCI API Result",
			"metricValue": 0.0,
			"metricTime": "Instant null because no metrics were returned in the last 24hs",
			"metricUnit": "bit",
			"paasThresholds": [],
			"historyClocks": [],
			"historyValues": []
		}, {
			"metricName": "OCCI Inspect VM availability",
			"metricKey": "Cloud_Providers.provider-RECAS-BARI..OCCI Inspect VM availability",
			"metricValue": 0.0,
			"metricTime": "Instant null because no metrics were returned in the last 24hs",
			"metricUnit": "bit",
			"paasThresholds": [],
			"historyClocks": [],
			"historyValues": []
		}, {
			"metricName": "OCCI InspectVM Response Time",
			"metricKey": "Cloud_Providers.provider-RECAS-BARI..OCCI InspectVM Response Time",
			"metricValue": 40.0,
			"metricTime": "Instant null because no metrics were returned in the last 24hs",
			"metricUnit": "ms",
			"paasThresholds": [],
			"historyClocks": [],
			"historyValues": []
		}, {
			"metricName": "OCCI InspectVM Result",
			"metricKey": "Cloud_Providers.provider-RECAS-BARI..OCCI InspectVM Result",
			"metricValue": 1.0,
			"metricTime": "Instant null because no metrics were returned in the last 24hs",
			"metricUnit": "bit",
			"paasThresholds": [],
			"historyClocks": [],
			"historyValues": []
		}]
	}, {
		"provider": "provider-UPV-GRyCAP",
		"metrics": [{
			"metricName": "OCCI Create VM availability",
			"metricKey": "Cloud_Providers.provider-UPV-GRyCAP..OCCI Create VM availability",
			"metricValue": 1.0,
			"metricTime": "Instant null because no metrics were returned in the last 24hs",
			"metricUnit": "bit",
			"paasThresholds": [],
			"historyClocks": [],
			"historyValues": []
		}, {
			"metricName": "OCCI CreateVM Response Time",
			"metricKey": "Cloud_Providers.provider-UPV-GRyCAP..OCCI CreateVM Response Time",
			"metricValue": 155.0,
			"metricTime": "Instant null because no metrics were returned in the last 24hs",
			"metricUnit": "ms",
			"paasThresholds": [],
			"historyClocks": [],
			"historyValues": []
		}, {
			"metricName": "OCCI CreateVM Result",
			"metricKey": "Cloud_Providers.provider-UPV-GRyCAP..OCCI CreateVM Result",
			"metricValue": 1.0,
			"metricTime": "Instant null because no metrics were returned in the last 24hs",
			"metricUnit": "bit",
			"paasThresholds": [],
			"historyClocks": [],
			"historyValues": []
		}, {
			"metricName": "OCCI Delete VM Availability",
			"metricKey": "Cloud_Providers.provider-UPV-GRyCAP..OCCI Delete VM Availability",
			"metricValue": 1.0,
			"metricTime": "Instant null because no metrics were returned in the last 24hs",
			"metricUnit": "bit",
			"paasThresholds": [],
			"historyClocks": [],
			"historyValues": []
		}, {
			"metricName": "OCCI DeleteVM Response Time",
			"metricKey": "Cloud_Providers.provider-UPV-GRyCAP..OCCI DeleteVM Response Time",
			"metricValue": 122.0,
			"metricTime": "Instant null because no metrics were returned in the last 24hs",
			"metricUnit": "ms",
			"paasThresholds": [],
			"historyClocks": [],
			"historyValues": []
		}, {
			"metricName": "OCCI DeleteVM Result",
			"metricKey": "Cloud_Providers.provider-UPV-GRyCAP..OCCI DeleteVM Result",
			"metricValue": 1.0,
			"metricTime": "Instant null because no metrics were returned in the last 24hs",
			"metricUnit": "bit",
			"paasThresholds": [],
			"historyClocks": [],
			"historyValues": []
		}, {
			"metricName": "General OCCI API Availability",
			"metricKey": "Cloud_Providers.provider-UPV-GRyCAP..General OCCI API Availability",
			"metricValue": 1.0,
			"metricTime": "Instant null because no metrics were returned in the last 24hs",
			"metricUnit": "bit",
			"paasThresholds": [],
			"historyClocks": [],
			"historyValues": []
		}, {
			"metricName": "General OCCI API Response Time",
			"metricKey": "Cloud_Providers.provider-UPV-GRyCAP..General OCCI API Response Time",
			"metricValue": 100.0,
			"metricTime": "Instant null because no metrics were returned in the last 24hs",
			"metricUnit": "ms",
			"paasThresholds": [],
			"historyClocks": [],
			"historyValues": []
		}, {
			"metricName": "General OCCI API Result",
			"metricKey": "Cloud_Providers.provider-UPV-GRyCAP..General OCCI API Result",
			"metricValue": 1.0,
			"metricTime": "Instant null because no metrics were returned in the last 24hs",
			"metricUnit": "bit",
			"paasThresholds": [],
			"historyClocks": [],
			"historyValues": []
		}, {
			"metricName": "OCCI Inspect VM availability",
			"metricKey": "Cloud_Providers.provider-UPV-GRyCAP..OCCI Inspect VM availability",
			"metricValue": 1.0,
			"metricTime": "Instant null because no metrics were returned in the last 24hs",
			"metricUnit": "bit",
			"paasThresholds": [],
			"historyClocks": [],
			"historyValues": []
		}, {
			"metricName": "OCCI InspectVM Response Time",
			"metricKey": "Cloud_Providers.provider-UPV-GRyCAP..OCCI InspectVM Response Time",
			"metricValue": 500.0,
			"metricTime": "Instant null because no metrics were returned in the last 24hs",
			"metricUnit": "ms",
			"paasThresholds": [],
			"historyClocks": [],
			"historyValues": []
		}, {
			"metricName": "OCCI InspectVM Result",
			"metricKey": "Cloud_Providers.provider-UPV-GRyCAP..OCCI InspectVM Result",
			"metricValue": 1.0,
			"metricTime": "Instant null because no metrics were returned in the last 24hs",
			"metricUnit": "bit",
			"paasThresholds": [],
			"historyClocks": [],
			"historyValues": []
		}]
	}]
}
```