# Install and run Cloud Provider Ranker

## Installation
The CloudProviderRanker WEB Service is made of a single .jar file containing a Main class which implements the ```main``` method. It's about a stand-alone server, so no WEB container (JBOSS, Tomcat, ...) is required. The WEB server is implemented using the HttpServer Java library (http://goo.gl/QLBjiP).
To install the artifact just put it wherever you prefer.
## Launch the server
To run the standalone WEB Server just issue the command:

    java -jar [YOUR_PREFERRED_PATH]/CloudProviderRanker-jar-with-dependencies.jar  <SLA_PRIORITY_FILE> <PAASMETRIC_NORMALIZATION_FILE> [TCPPORT] [KeystoreFile password]

The meaning of the content of two files ```<SLA_PRIORITY_FILE>``` and ```<PAASMETRIC_NORMALIZATION_FILE>``` is explained in the algorithm section.

By default the server is started listening on plain HTTP (not encrypted). To switch on a HTTPS server, it must be started with the options ```KeystoreFile``` and ```password```; to generate a Keystore file, just issue the command:

    keytool -genkey -alias webservice -keystore <filepath>

a password will be asked, and must be used in the command line above.

If the ```TCPPORT``` parameter is not specified, the default port will be 8080.
If the couple of parameters ```KeystoreFile``` and ```password``` are not specified, the server will start listening on plain HTTP, otherwise it will start with HTTPS.

----------------------------
## Testing the server
To test the server (which responds at the address ```http[s]://<IP_WHERE_YOU_DEPLOYED_IT>:<CHOSEN_TCP_PORT>/rank```) at the client side just use ```cURL``` or Postaman to send a POST request with the following payload data (body):
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
			"metricValue": 0.0,
			"metricTime": "Instant null because no metrics were returned in the last 24hs",
			"metricUnit": "bit",
			"paasThresholds": [],
			"historyClocks": [],
			"historyValues": []
		}, {
			"metricName": "OCCI CreateVM Response Time",
			"metricKey": "Cloud_Providers.provider-RECAS-BARI..OCCI CreateVM Response Time",
			"metricValue": 0.0,
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
			"metricValue": 0.0,
			"metricTime": "Instant null because no metrics were returned in the last 24hs",
			"metricUnit": "ms",
			"paasThresholds": [],
			"historyClocks": [],
			"historyValues": []
		}, {
			"metricName": "OCCI DeleteVM Result",
			"metricKey": "Cloud_Providers.provider-RECAS-BARI..OCCI DeleteVM Result",
			"metricValue": 0.0,
			"metricTime": "Instant null because no metrics were returned in the last 24hs",
			"metricUnit": "bit",
			"paasThresholds": [],
			"historyClocks": [],
			"historyValues": []
		}, {
			"metricName": "General OCCI API Availability",
			"metricKey": "Cloud_Providers.provider-RECAS-BARI..General OCCI API Availability",
			"metricValue": 0.0,
			"metricTime": "Instant null because no metrics were returned in the last 24hs",
			"metricUnit": "bit",
			"paasThresholds": [],
			"historyClocks": [],
			"historyValues": []
		}, {
			"metricName": "General OCCI API Response Time",
			"metricKey": "Cloud_Providers.provider-RECAS-BARI..General OCCI API Response Time",
			"metricValue": 0.0,
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
			"metricValue": 0.0,
			"metricTime": "Instant null because no metrics were returned in the last 24hs",
			"metricUnit": "ms",
			"paasThresholds": [],
			"historyClocks": [],
			"historyValues": []
		}, {
			"metricName": "OCCI InspectVM Result",
			"metricKey": "Cloud_Providers.provider-RECAS-BARI..OCCI InspectVM Result",
			"metricValue": 0.0,
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
			"metricValue": 0.0,
			"metricTime": "Instant null because no metrics were returned in the last 24hs",
			"metricUnit": "bit",
			"paasThresholds": [],
			"historyClocks": [],
			"historyValues": []
		}, {
			"metricName": "OCCI CreateVM Response Time",
			"metricKey": "Cloud_Providers.provider-UPV-GRyCAP..OCCI CreateVM Response Time",
			"metricValue": 0.0,
			"metricTime": "Instant null because no metrics were returned in the last 24hs",
			"metricUnit": "ms",
			"paasThresholds": [],
			"historyClocks": [],
			"historyValues": []
		}, {
			"metricName": "OCCI CreateVM Result",
			"metricKey": "Cloud_Providers.provider-UPV-GRyCAP..OCCI CreateVM Result",
			"metricValue": 0.0,
			"metricTime": "Instant null because no metrics were returned in the last 24hs",
			"metricUnit": "bit",
			"paasThresholds": [],
			"historyClocks": [],
			"historyValues": []
		}, {
			"metricName": "OCCI Delete VM Availability",
			"metricKey": "Cloud_Providers.provider-UPV-GRyCAP..OCCI Delete VM Availability",
			"metricValue": 0.0,
			"metricTime": "Instant null because no metrics were returned in the last 24hs",
			"metricUnit": "bit",
			"paasThresholds": [],
			"historyClocks": [],
			"historyValues": []
		}, {
			"metricName": "OCCI DeleteVM Response Time",
			"metricKey": "Cloud_Providers.provider-UPV-GRyCAP..OCCI DeleteVM Response Time",
			"metricValue": 0.0,
			"metricTime": "Instant null because no metrics were returned in the last 24hs",
			"metricUnit": "ms",
			"paasThresholds": [],
			"historyClocks": [],
			"historyValues": []
		}, {
			"metricName": "OCCI DeleteVM Result",
			"metricKey": "Cloud_Providers.provider-UPV-GRyCAP..OCCI DeleteVM Result",
			"metricValue": 0.0,
			"metricTime": "Instant null because no metrics were returned in the last 24hs",
			"metricUnit": "bit",
			"paasThresholds": [],
			"historyClocks": [],
			"historyValues": []
		}, {
			"metricName": "General OCCI API Availability",
			"metricKey": "Cloud_Providers.provider-UPV-GRyCAP..General OCCI API Availability",
			"metricValue": 0.0,
			"metricTime": "Instant null because no metrics were returned in the last 24hs",
			"metricUnit": "bit",
			"paasThresholds": [],
			"historyClocks": [],
			"historyValues": []
		}, {
			"metricName": "General OCCI API Response Time",
			"metricKey": "Cloud_Providers.provider-UPV-GRyCAP..General OCCI API Response Time",
			"metricValue": 0.0,
			"metricTime": "Instant null because no metrics were returned in the last 24hs",
			"metricUnit": "ms",
			"paasThresholds": [],
			"historyClocks": [],
			"historyValues": []
		}, {
			"metricName": "General OCCI API Result",
			"metricKey": "Cloud_Providers.provider-UPV-GRyCAP..General OCCI API Result",
			"metricValue": 0.0,
			"metricTime": "Instant null because no metrics were returned in the last 24hs",
			"metricUnit": "bit",
			"paasThresholds": [],
			"historyClocks": [],
			"historyValues": []
		}, {
			"metricName": "OCCI Inspect VM availability",
			"metricKey": "Cloud_Providers.provider-UPV-GRyCAP..OCCI Inspect VM availability",
			"metricValue": 0.0,
			"metricTime": "Instant null because no metrics were returned in the last 24hs",
			"metricUnit": "bit",
			"paasThresholds": [],
			"historyClocks": [],
			"historyValues": []
		}, {
			"metricName": "OCCI InspectVM Response Time",
			"metricKey": "Cloud_Providers.provider-UPV-GRyCAP..OCCI InspectVM Response Time",
			"metricValue": 0.0,
			"metricTime": "Instant null because no metrics were returned in the last 24hs",
			"metricUnit": "ms",
			"paasThresholds": [],
			"historyClocks": [],
			"historyValues": []
		}, {
			"metricName": "OCCI InspectVM Result",
			"metricKey": "Cloud_Providers.provider-UPV-GRyCAP..OCCI InspectVM Result",
			"metricValue": 0.0,
			"metricTime": "Instant null because no metrics were returned in the last 24hs",
			"metricUnit": "bit",
			"paasThresholds": [],
			"historyClocks": [],
			"historyValues": []
		}]
	}]
}```

You'll receive an output like this:

    ```{{"name":"provider-RECAS-BARI","rank":57.02,"ranked":true,"errorReason":""},{"name":"provider-UPV-GRyCAP","rank":3017.123,"ranked":true,"errorReason":""}} ```

In the case of a JSON syntax error, the exception is reported to the client:

```Exception parsing JSON client request: com.google.gson.stream.MalformedJsonException: Unterminated object at line 1 column 6141 path $.monitoring[1].metrics[3].paasThresholds```

```Exception parsing JSON client request: com.google.gson.stream.MalformedJsonException: Unexpected value at line 1 column 6338 path $.monitoring[1].metrics[4].metricValue```

```Exception parsing JSON client request: com.google.gson.stream.MalformedJsonException: Unterminated array at line 1 column 4835 path $.monitoring[2]```

```Exception parsing JSON client request: com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed JSON at line 1 column 8 path $```

