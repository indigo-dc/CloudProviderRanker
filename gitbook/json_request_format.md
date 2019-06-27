# Ranking JSON Request format

Below a general form of the JSON request (or payload) the user must send
with a POST HTTP request to the Cloud Provider Ranker's `/rank` API.

There're three main sections:

  * `preferences`
  * `sla`
  * `monitoring`

How they're handled by Cloud Provider Ranker to rank the cloud providers
is described in the [Ranking Algorithm](ranking_algorithm.md) chapter.

```
{
  "preferences": [
    {
      "service_type": "compute",
      "priority": [
        {
          "sla_id": "5bfbc091a9a88f528bf5114b",
          "service_id": "eaa07cfcceb85cd11b8adacf350022ce",
          "weight": 0.5
        },
        {
          "sla_id": "5bfbc096a9a88f528bf5114c",
          "service_id": "eaa07cfcceb85cd11b8adacf35004814",
          "weight": 0.5
        },
        {
          "sla_id": "5bfbc09aa9a88f528bf5114d",
          "service_id": "eaa07cfcceb85cd11b8adacf35005a71",
          "weight": 0.5
        },
        {
          "sla_id": "5bfbc0a1a9a88f528bf5114e",
          "service_id": "eaa07cfcceb85cd11b8adacf35003aae",
          "weight": 0.5
        },
        {
          "sla_id": "5bfbc0a4a9a88f528bf5114f",
          "service_id": "eaa07cfcceb85cd11b8adacf350052b3",
          "weight": 0.5
        },
        {
          "sla_id": "5cefdd1bcb0e8f527dfb3c94",
          "service_id": "99f82787b0522954505d2bc835008849",
          "weight": 0.5
        },
        {
          "sla_id": "5cf12da6cb0e8f527dfb3c97",
          "service_id": "eaa07cfcceb85cd11b8adacf350066fe",
          "weight": 0.5
        }
      ]
    },
    {
      "service_type": "storage",
      "priority": []
    }
  ],
  "sla": [
    {
      "customer": "deep-hdc",
      "provider": "provider-RECAS-BARI",
      "start_date": "Mon Oct 08 22:00:00 UTC 2018",
      "end_date": "Tue Oct 08 22:00:00 UTC 2019",
      "services": [
        {
          "type": "computing",
          "service_id": "eaa07cfcceb85cd11b8adacf350022ce",
          "targets": [
            {
              "type": "public_ip",
              "unit": null,
              "restrictions": {
                "total_guaranteed": 100,
                "user_guaranteed": 5,
                "user_limit": 10,
                "total_limit": 100
              }
            },
            {
              "type": "computing_time",
              "unit": "h",
              "restrictions": {
                "total_guaranteed": 500,
                "instance_limit": 100,
                "user_guaranteed": 10,
                "user_limit": 10,
                "total_limit": 1000
              }
            }
          ]
        }
      ],
      "id": "5bfbc091a9a88f528bf5114b"
    },
    {
      "customer": "deep-hdc",
      "provider": "provider-RECAS-BARI",
      "start_date": "Mon Oct 08 22:00:00 UTC 2018",
      "end_date": "Mon Oct 07 22:00:00 UTC 2019",
      "services": [
        {
          "type": "computing",
          "service_id": "eaa07cfcceb85cd11b8adacf35004814",
          "targets": [
            {
              "type": "public_ip",
              "unit": null,
              "restrictions": {
                "total_guaranteed": 100,
                "user_guaranteed": 5,
                "user_limit": 10,
                "total_limit": 100
              }
            },
            {
              "type": "computing_time",
              "unit": "h",
              "restrictions": {
                "total_guaranteed": 50,
                "instance_limit": 10,
                "user_guaranteed": 5,
                "user_limit": 5,
                "total_limit": 100
              }
            }
          ]
        }
      ],
      "id": "5bfbc096a9a88f528bf5114c"
    },
    {
      "customer": "deep-hdc",
      "provider": "provider-RECAS-BARI",
      "start_date": "Sun Nov 25 23:00:00 UTC 2018",
      "end_date": "Tue Oct 08 22:00:00 UTC 2019",
      "services": [
        {
          "type": "computing",
          "service_id": "eaa07cfcceb85cd11b8adacf35005a71",
          "targets": [
            {
              "type": "public_ip",
              "unit": null,
              "restrictions": {
                "total_guaranteed": 100,
                "user_guaranteed": 5,
                "user_limit": 10,
                "total_limit": 100
              }
            },
            {
              "type": "computing_time",
              "unit": "h",
              "restrictions": {
                "total_guaranteed": 100,
                "instance_limit": 100,
                "user_guaranteed": 5,
                "user_limit": 10,
                "total_limit": 100
              }
            }
          ]
        }
      ],
      "id": "5bfbc09aa9a88f528bf5114d"
    },
    {
      "customer": "deep-hdc",
      "provider": "provider-RECAS-BARI",
      "start_date": "Mon Oct 08 22:00:00 UTC 2018",
      "end_date": "Tue Oct 08 22:00:00 UTC 2019",
      "services": [
        {
          "type": "computing",
          "service_id": "eaa07cfcceb85cd11b8adacf35003aae",
          "targets": [
            {
              "type": "public_ip",
              "unit": null,
              "restrictions": {
                "total_guaranteed": 100,
                "user_guaranteed": 10,
                "user_limit": 10,
                "total_limit": 100
              }
            },
            {
              "type": "computing_time",
              "unit": "h",
              "restrictions": {
                "total_guaranteed": 100,
                "instance_limit": 10,
                "user_guaranteed": 5,
                "user_limit": 10,
                "total_limit": 100
              }
            }
          ]
        }
      ],
      "id": "5bfbc0a1a9a88f528bf5114e"
    },
    {
      "customer": "deep-hdc",
      "provider": "provider-RECAS-BARI",
      "start_date": "Sun Nov 25 23:00:00 UTC 2018",
      "end_date": "Tue Oct 08 22:00:00 UTC 2019",
      "services": [
        {
          "type": "computing",
          "service_id": "eaa07cfcceb85cd11b8adacf350052b3",
          "targets": [
            {
              "type": "public_ip",
              "unit": null,
              "restrictions": {
                "total_guaranteed": 100,
                "user_guaranteed": 10,
                "user_limit": 10,
                "total_limit": 100
              }
            },
            {
              "type": "computing_time",
              "unit": "h",
              "restrictions": {
                "total_guaranteed": 100,
                "instance_limit": 100,
                "user_guaranteed": 50,
                "user_limit": 100,
                "total_limit": 100
              }
            }
          ]
        }
      ],
      "id": "5bfbc0a4a9a88f528bf5114f"
    },
    {
      "customer": "deep-hdc",
      "provider": "provider-PSNC",
      "start_date": "Wed May 29 22:00:00 UTC 2019",
      "end_date": "Fri May 29 22:00:00 UTC 2020",
      "services": [
        {
          "type": "computing",
          "service_id": "99f82787b0522954505d2bc835008849",
          "targets": [
            {
              "type": "public_ip",
              "unit": null,
              "restrictions": {
                "total_guaranteed": 0,
                "user_guaranteed": 1,
                "user_limit": 1,
                "total_limit": 1
              }
            },
            {
              "type": "computing_time",
              "unit": "h",
              "restrictions": {
                "total_guaranteed": 100,
                "instance_limit": 20,
                "user_guaranteed": 20,
                "user_limit": 20,
                "total_limit": 100
              }
            }
          ]
        }
      ],
      "id": "5cefdd1bcb0e8f527dfb3c94"
    },
    {
      "customer": "deep-hdc",
      "provider": "provider-IFCA-LCG2",
      "start_date": "Thu May 30 22:00:00 UTC 2019",
      "end_date": "Sat May 30 22:00:00 UTC 2020",
      "services": [
        {
          "type": "computing",
          "service_id": "eaa07cfcceb85cd11b8adacf350066fe",
          "targets": [
            {
              "type": "public_ip",
              "unit": null,
              "restrictions": {
                "total_guaranteed": 10,
                "user_guaranteed": 1,
                "user_limit": 3,
                "total_limit": 10
              }
            },
            {
              "type": "computing_time",
              "unit": "h",
              "restrictions": {
                "total_guaranteed": 20,
                "instance_limit": 5,
                "user_guaranteed": 10,
                "user_limit": 10,
                "total_limit": 5
              }
            }
          ]
        }
      ],
      "id": "5cf12da6cb0e8f527dfb3c97"
    }
  ],
  "monitoring": [
    {
      "provider": "provider-RECAS-BARI",
      "services": [
        {
          "service_id": "eaa07cfcceb85cd11b8adacf35008587",
          "type": "eu.indigo-datacloud.mesos",
          "metrics": [
            {
              "metricName": "master.cpus_percent",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35008587.Mesos.master.cpus_percent",
              "metricValue": 0.0625,
              "metricTime": "26-06-2019 07:00:06",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "master.dropped_messages",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35008587.Mesos.master.dropped_messages",
              "metricValue": 11,
              "metricTime": "26-06-2019 07:00:06",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "master.elected",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35008587.Mesos.master.elected",
              "metricValue": 1,
              "metricTime": "26-06-2019 07:00:06",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "master.frameworks_disconnected",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35008587.Mesos.master.frameworks_disconnected",
              "metricValue": 0,
              "metricTime": "26-06-2019 07:00:06",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "master.frameworks_inactive",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35008587.Mesos.master.frameworks_inactive",
              "metricValue": 1,
              "metricTime": "26-06-2019 07:00:06",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "master.slaves_active",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35008587.Mesos.master.slaves_active",
              "metricValue": 4,
              "metricTime": "26-06-2019 07:00:06",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "master.tasks_lost",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35008587.Mesos.master.tasks_lost",
              "metricValue": 16,
              "metricTime": "26-06-2019 07:00:06",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "master.uptime_secs",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35008587.Mesos.master.uptime_secs",
              "metricValue": 6569194.5,
              "metricTime": "26-06-2019 07:00:06",
              "metricUnit": "secs",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            }
          ]
        },
        {
          "service_id": "eaa07cfcceb85cd11b8adacf35008e72",
          "type": "eu.indigo-datacloud.mesos",
          "metrics": [
            {
              "metricName": "master.cpus_percent",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35008e72.Mesos.master.cpus_percent",
              "metricValue": 0,
              "metricTime": "26-06-2019 07:00:20",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "master.dropped_messages",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35008e72.Mesos.master.dropped_messages",
              "metricValue": 13,
              "metricTime": "26-06-2019 07:00:20",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "master.elected",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35008e72.Mesos.master.elected",
              "metricValue": 1,
              "metricTime": "26-06-2019 07:00:20",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "master.frameworks_disconnected",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35008e72.Mesos.master.frameworks_disconnected",
              "metricValue": 0,
              "metricTime": "26-06-2019 07:00:20",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "master.frameworks_inactive",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35008e72.Mesos.master.frameworks_inactive",
              "metricValue": 0,
              "metricTime": "26-06-2019 07:00:20",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "master.slaves_active",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35008e72.Mesos.master.slaves_active",
              "metricValue": 2,
              "metricTime": "26-06-2019 07:00:20",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "master.tasks_lost",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35008e72.Mesos.master.tasks_lost",
              "metricValue": 0,
              "metricTime": "26-06-2019 07:00:20",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "master.uptime_secs",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35008e72.Mesos.master.uptime_secs",
              "metricValue": 3018363.8,
              "metricTime": "26-06-2019 07:00:20",
              "metricUnit": "secs",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            }
          ]
        },
        {
          "service_id": "eaa07cfcceb85cd11b8adacf350022ce",
          "type": "IaaS",
          "metrics": [
            {
              "metricName": "clear_responseTime",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf350022ce. eu.egi.cloud.vm-management.openstack.clear_responseTime",
              "metricValue": 38049,
              "metricTime": "26-06-2019 07:40:14",
              "metricUnit": "ms",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "clear_result",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf350022ce. eu.egi.cloud.vm-management.openstack.clear_result",
              "metricValue": 1,
              "metricTime": "26-06-2019 07:40:14",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "clear_status",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf350022ce. eu.egi.cloud.vm-management.openstack.clear_status",
              "metricValue": 204,
              "metricTime": "26-06-2019 07:40:14",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "create_responseTime",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf350022ce. eu.egi.cloud.vm-management.openstack.create_responseTime",
              "metricValue": 777,
              "metricTime": "26-06-2019 07:40:14",
              "metricUnit": "ms",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "create_result",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf350022ce. eu.egi.cloud.vm-management.openstack.create_result",
              "metricValue": 1,
              "metricTime": "26-06-2019 07:40:14",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "create_status",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf350022ce. eu.egi.cloud.vm-management.openstack.create_status",
              "metricValue": 200,
              "metricTime": "26-06-2019 07:40:14",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "delete_responseTime",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf350022ce. eu.egi.cloud.vm-management.openstack.delete_responseTime",
              "metricValue": 1142,
              "metricTime": "26-06-2019 07:40:14",
              "metricUnit": "ms",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "delete_result",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf350022ce. eu.egi.cloud.vm-management.openstack.delete_result",
              "metricValue": 1,
              "metricTime": "26-06-2019 07:40:14",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "delete_status",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf350022ce. eu.egi.cloud.vm-management.openstack.delete_status",
              "metricValue": 200,
              "metricTime": "26-06-2019 07:40:14",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "openstack_responseTime",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf350022ce. eu.egi.cloud.vm-management.openstack.openstack_responseTime",
              "metricValue": 0,
              "metricTime": "Instant null because no metrics were returned in the last 24hs",
              "metricUnit": "ms",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "openstack_result",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf350022ce. eu.egi.cloud.vm-management.openstack.openstack_result",
              "metricValue": 0,
              "metricTime": "Instant null because no metrics were returned in the last 24hs",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "openstack_status",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf350022ce. eu.egi.cloud.vm-management.openstack.openstack_status",
              "metricValue": 0,
              "metricTime": "Instant null because no metrics were returned in the last 24hs",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "run_responseTime",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf350022ce. eu.egi.cloud.vm-management.openstack.run_responseTime",
              "metricValue": 565,
              "metricTime": "26-06-2019 07:40:14",
              "metricUnit": "ms",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "run_result",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf350022ce. eu.egi.cloud.vm-management.openstack.run_result",
              "metricValue": 1,
              "metricTime": "26-06-2019 07:40:14",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "run_status",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf350022ce. eu.egi.cloud.vm-management.openstack.run_status",
              "metricValue": 200,
              "metricTime": "26-06-2019 07:40:14",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            }
          ]
        },
        {
          "service_id": "eaa07cfcceb85cd11b8adacf35003aae",
          "service_parent_id": "eaa07cfcceb85cd11b8adacf35008587",
          "type": "eu.indigo-datacloud.marathon",
          "metrics": [
            {
              "metricName": "clear_responseTime",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35003aae..clear_responseTime",
              "metricValue": 299,
              "metricTime": "26-06-2019 07:30:07",
              "metricUnit": "ms",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "clear_result",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35003aae..clear_result",
              "metricValue": 1,
              "metricTime": "26-06-2019 07:30:07",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "clear_status",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35003aae..clear_status",
              "metricValue": 404,
              "metricTime": "26-06-2019 07:30:07",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "create_responseTime",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35003aae..create_responseTime",
              "metricValue": 982,
              "metricTime": "26-06-2019 07:30:07",
              "metricUnit": "ms",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "create_result",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35003aae..create_result",
              "metricValue": 1,
              "metricTime": "26-06-2019 07:30:07",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "create_status",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35003aae..create_status",
              "metricValue": 200,
              "metricTime": "26-06-2019 07:30:07",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "delete_responseTime",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35003aae..delete_responseTime",
              "metricValue": 174,
              "metricTime": "26-06-2019 07:30:07",
              "metricUnit": "ms",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "delete_result",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35003aae..delete_result",
              "metricValue": 1,
              "metricTime": "26-06-2019 07:30:07",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "delete_status",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35003aae..delete_status",
              "metricValue": 200,
              "metricTime": "26-06-2019 07:30:07",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "run_responseTime",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35003aae..run_responseTime",
              "metricValue": 52,
              "metricTime": "26-06-2019 07:30:07",
              "metricUnit": "ms",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "run_result",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35003aae..run_result",
              "metricValue": 1,
              "metricTime": "26-06-2019 07:30:07",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "run_status",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35003aae..run_status",
              "metricValue": 200,
              "metricTime": "26-06-2019 07:30:07",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            }
          ]
        },
        {
          "service_id": "eaa07cfcceb85cd11b8adacf350052b3",
          "service_parent_id": "eaa07cfcceb85cd11b8adacf35008e72",
          "type": "eu.indigo-datacloud.marathon",
          "metrics": [
            {
              "metricName": "clear_responseTime",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf350052b3..clear_responseTime",
              "metricValue": 132,
              "metricTime": "26-06-2019 07:30:22",
              "metricUnit": "ms",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "clear_result",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf350052b3..clear_result",
              "metricValue": 1,
              "metricTime": "26-06-2019 07:30:22",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "clear_status",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf350052b3..clear_status",
              "metricValue": 404,
              "metricTime": "26-06-2019 07:30:22",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "create_responseTime",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf350052b3..create_responseTime",
              "metricValue": 64,
              "metricTime": "26-06-2019 07:30:22",
              "metricUnit": "ms",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "create_result",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf350052b3..create_result",
              "metricValue": 1,
              "metricTime": "26-06-2019 07:30:22",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "create_status",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf350052b3..create_status",
              "metricValue": 200,
              "metricTime": "26-06-2019 07:30:22",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "delete_responseTime",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf350052b3..delete_responseTime",
              "metricValue": 31,
              "metricTime": "26-06-2019 07:30:22",
              "metricUnit": "ms",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "delete_result",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf350052b3..delete_result",
              "metricValue": 1,
              "metricTime": "26-06-2019 07:30:22",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "delete_status",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf350052b3..delete_status",
              "metricValue": 200,
              "metricTime": "26-06-2019 07:30:22",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "run_responseTime",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf350052b3..run_responseTime",
              "metricValue": 10,
              "metricTime": "26-06-2019 07:30:22",
              "metricUnit": "ms",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "run_result",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf350052b3..run_result",
              "metricValue": 1,
              "metricTime": "26-06-2019 07:30:22",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "run_status",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf350052b3..run_status",
              "metricValue": 200,
              "metricTime": "26-06-2019 07:30:22",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            }
          ]
        },
        {
          "service_id": "eaa07cfcceb85cd11b8adacf35004814",
          "service_parent_id": "eaa07cfcceb85cd11b8adacf35008587",
          "type": "eu.indigo-datacloud.chronos",
          "metrics": [
            {
              "metricName": "clear_responseTime",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35004814..clear_responseTime",
              "metricValue": 372,
              "metricTime": "26-06-2019 07:15:05",
              "metricUnit": "ms",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "clear_result",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35004814..clear_result",
              "metricValue": 1,
              "metricTime": "26-06-2019 07:15:05",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "clear_status",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35004814..clear_status",
              "metricValue": 404,
              "metricTime": "26-06-2019 07:15:05",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "create_responseTime",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35004814..create_responseTime",
              "metricValue": 281,
              "metricTime": "26-06-2019 07:15:05",
              "metricUnit": "ms",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "create_result",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35004814..create_result",
              "metricValue": 1,
              "metricTime": "26-06-2019 07:15:05",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "create_status",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35004814..create_status",
              "metricValue": 200,
              "metricTime": "26-06-2019 07:15:05",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "delete_responseTime",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35004814..delete_responseTime",
              "metricValue": 0,
              "metricTime": "26-06-2019 07:15:05",
              "metricUnit": "ms",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "delete_result",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35004814..delete_result",
              "metricValue": 0,
              "metricTime": "26-06-2019 07:15:05",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "delete_status",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35004814..delete_status",
              "metricValue": 503,
              "metricTime": "26-06-2019 07:15:05",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "run_responseTime",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35004814..run_responseTime",
              "metricValue": 97,
              "metricTime": "26-06-2019 07:15:05",
              "metricUnit": "ms",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "run_result",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35004814..run_result",
              "metricValue": 0,
              "metricTime": "26-06-2019 07:15:05",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "run_status",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35004814..run_status",
              "metricValue": 404,
              "metricTime": "26-06-2019 07:15:05",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            }
          ]
        },
        {
          "service_id": "eaa07cfcceb85cd11b8adacf35005a71",
          "service_parent_id": "eaa07cfcceb85cd11b8adacf35008e72",
          "type": "eu.indigo-datacloud.chronos",
          "metrics": [
            {
              "metricName": "clear_responseTime",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35005a71..clear_responseTime",
              "metricValue": 150,
              "metricTime": "26-06-2019 07:15:21",
              "metricUnit": "ms",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "clear_result",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35005a71..clear_result",
              "metricValue": 1,
              "metricTime": "26-06-2019 07:15:21",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "clear_status",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35005a71..clear_status",
              "metricValue": 404,
              "metricTime": "26-06-2019 07:15:21",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "create_responseTime",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35005a71..create_responseTime",
              "metricValue": 37,
              "metricTime": "26-06-2019 07:15:21",
              "metricUnit": "ms",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "create_result",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35005a71..create_result",
              "metricValue": 1,
              "metricTime": "26-06-2019 07:15:21",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "create_status",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35005a71..create_status",
              "metricValue": 200,
              "metricTime": "26-06-2019 07:15:21",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "delete_responseTime",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35005a71..delete_responseTime",
              "metricValue": 29,
              "metricTime": "26-06-2019 07:15:21",
              "metricUnit": "ms",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "delete_result",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35005a71..delete_result",
              "metricValue": 1,
              "metricTime": "26-06-2019 07:15:21",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "delete_status",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35005a71..delete_status",
              "metricValue": 200,
              "metricTime": "26-06-2019 07:15:21",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "run_responseTime",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35005a71..run_responseTime",
              "metricValue": 10,
              "metricTime": "26-06-2019 07:15:21",
              "metricUnit": "ms",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "run_result",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35005a71..run_result",
              "metricValue": 1,
              "metricTime": "26-06-2019 07:15:21",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            },
            {
              "metricName": "run_status",
              "metricKey": "provider-RECAS-BARI.eaa07cfcceb85cd11b8adacf35005a71..run_status",
              "metricValue": 200,
              "metricTime": "26-06-2019 07:15:21",
              "metricUnit": "",
              "paasThresholds": [],
              "historyClocks": [],
              "historyValues": []
            }
          ]
        }
      ]
    },
{
    "provider": "provider-IFCA-LCG2",
    "services": [
      {
        "service_id": "eaa07cfcceb85cd11b8adacf350066fe",
        "type": "eu.egi.cloud.vm-management.openstack",
        "metrics": [
          {
            "metricName": "clear_responseTime",
            "metricKey": "provider-IFCA-LCG2.eaa07cfcceb85cd11b8adacf350066fe. eu.egi.cloud.vm-management.openstack.clear_responseTime",
            "metricValue": 24441,
            "metricTime": "26-06-2019 08:11:04",
            "metricUnit": "ms",
            "paasThresholds": [],
            "historyClocks": [],
            "historyValues": []
          },
          {
            "metricName": "clear_result",
            "metricKey": "provider-IFCA-LCG2.eaa07cfcceb85cd11b8adacf350066fe. eu.egi.cloud.vm-management.openstack.clear_result",
            "metricValue": 1,
            "metricTime": "26-06-2019 08:11:04",
            "metricUnit": "",
            "paasThresholds": [],
            "historyClocks": [],
            "historyValues": []
          },
          {
            "metricName": "clear_status",
            "metricKey": "provider-IFCA-LCG2.eaa07cfcceb85cd11b8adacf350066fe. eu.egi.cloud.vm-management.openstack.clear_status",
            "metricValue": 204,
            "metricTime": "26-06-2019 08:11:04",
            "metricUnit": "",
            "paasThresholds": [],
            "historyClocks": [],
            "historyValues": []
          },
          {
            "metricName": "create_responseTime",
            "metricKey": "provider-IFCA-LCG2.eaa07cfcceb85cd11b8adacf350066fe. eu.egi.cloud.vm-management.openstack.create_responseTime",
            "metricValue": 933,
            "metricTime": "26-06-2019 08:11:04",
            "metricUnit": "ms",
            "paasThresholds": [],
            "historyClocks": [],
            "historyValues": []
          },
          {
            "metricName": "create_result",
            "metricKey": "provider-IFCA-LCG2.eaa07cfcceb85cd11b8adacf350066fe. eu.egi.cloud.vm-management.openstack.create_result",
            "metricValue": 1,
            "metricTime": "26-06-2019 08:11:04",
            "metricUnit": "",
            "paasThresholds": [],
            "historyClocks": [],
            "historyValues": []
          },
          {
            "metricName": "create_status",
            "metricKey": "provider-IFCA-LCG2.eaa07cfcceb85cd11b8adacf350066fe. eu.egi.cloud.vm-management.openstack.create_status",
            "metricValue": 200,
            "metricTime": "26-06-2019 08:11:04",
            "metricUnit": "",
            "paasThresholds": [],
            "historyClocks": [],
            "historyValues": []
          },
          {
            "metricName": "delete_responseTime",
            "metricKey": "provider-IFCA-LCG2.eaa07cfcceb85cd11b8adacf350066fe. eu.egi.cloud.vm-management.openstack.delete_responseTime",
            "metricValue": 1387,
            "metricTime": "26-06-2019 08:11:04",
            "metricUnit": "ms",
            "paasThresholds": [],
            "historyClocks": [],
            "historyValues": []
          },
          {
            "metricName": "delete_result",
            "metricKey": "provider-IFCA-LCG2.eaa07cfcceb85cd11b8adacf350066fe. eu.egi.cloud.vm-management.openstack.delete_result",
            "metricValue": 1,
            "metricTime": "26-06-2019 08:11:04",
            "metricUnit": "",
            "paasThresholds": [],
            "historyClocks": [],
            "historyValues": []
          },
          {
            "metricName": "delete_status",
            "metricKey": "provider-IFCA-LCG2.eaa07cfcceb85cd11b8adacf350066fe. eu.egi.cloud.vm-management.openstack.delete_status",
            "metricValue": 200,
            "metricTime": "26-06-2019 08:11:04",
            "metricUnit": "",
            "paasThresholds": [],
            "historyClocks": [],
            "historyValues": []
          },
          {
            "metricName": "openstack_responseTime",
            "metricKey": "provider-IFCA-LCG2.eaa07cfcceb85cd11b8adacf350066fe. eu.egi.cloud.vm-management.openstack.openstack_responseTime",
            "metricValue": 0,
            "metricTime": "Instant null because no metrics were returned in the last 24hs",
            "metricUnit": "ms",
            "paasThresholds": [],
            "historyClocks": [],
            "historyValues": []
          },
          {
            "metricName": "openstack_result",
            "metricKey": "provider-IFCA-LCG2.eaa07cfcceb85cd11b8adacf350066fe. eu.egi.cloud.vm-management.openstack.openstack_result",
            "metricValue": 0,
            "metricTime": "Instant null because no metrics were returned in the last 24hs",
            "metricUnit": "",
            "paasThresholds": [],
            "historyClocks": [],
            "historyValues": []
          },
          {
            "metricName": "openstack_status",
            "metricKey": "provider-IFCA-LCG2.eaa07cfcceb85cd11b8adacf350066fe. eu.egi.cloud.vm-management.openstack.openstack_status",
            "metricValue": 0,
            "metricTime": "Instant null because no metrics were returned in the last 24hs",
            "metricUnit": "",
            "paasThresholds": [],
            "historyClocks": [],
            "historyValues": []
          },
          {
            "metricName": "run_responseTime",
            "metricKey": "provider-IFCA-LCG2.eaa07cfcceb85cd11b8adacf350066fe. eu.egi.cloud.vm-management.openstack.run_responseTime",
            "metricValue": 788,
            "metricTime": "26-06-2019 08:11:04",
            "metricUnit": "ms",
            "paasThresholds": [],
            "historyClocks": [],
            "historyValues": []
          },
          {
            "metricName": "run_result",
            "metricKey": "provider-IFCA-LCG2.eaa07cfcceb85cd11b8adacf350066fe. eu.egi.cloud.vm-management.openstack.run_result",
            "metricValue": 1,
            "metricTime": "26-06-2019 08:11:04",
            "metricUnit": "",
            "paasThresholds": [],
            "historyClocks": [],
            "historyValues": []
          },
          {
            "metricName": "run_status",
            "metricKey": "provider-IFCA-LCG2.eaa07cfcceb85cd11b8adacf350066fe. eu.egi.cloud.vm-management.openstack.run_status",
            "metricValue": 200,
            "metricTime": "26-06-2019 08:11:04",
            "metricUnit": "",
            "paasThresholds": [],
            "historyClocks": [],
            "historyValues": []
          }
        ]
      }
    ]
  }
  ]
}
```
