# Ranking JSON response format

Below the general format of a Cloud Provider Ranker's JSON response to a
ranking request (performed by contacting the `/rank` API).

```
[
    {
      "provider": "provider-RECAS-BARI",
      "rank": -1,
      "ranked": false,
      "service_id": "eaa07cfcceb85cd11b8adacf35008587",
      "sla_weight": null,
      "total_score": 0.0
    },
    {
      "provider": "provider-PSNC",
      "rank": 7,
      "ranked": true,
      "service_id": "99f82787b0522954505d2bc835008849",
      "sla_weight": 0.5,
      "total_score": 7.316
    },
    {
      "provider": "provider-IFCA-LCG2",
      "rank": 6,
      "ranked": true,
      "service_id": "eaa07cfcceb85cd11b8adacf350066fe",
      "sla_weight": 0.5,
      "total_score": 14.83
    },
    ...
    {
      "provider": "provider-RECAS-BARI",
      "rank": 1,
      "ranked": true,
      "service_id": "eaa07cfcceb85cd11b8adacf350022ce",
      "sla_weight": 0.5,
      "total_score": 241.892
    }
]
```

If an error occurs and for a particular service the ranking
cannot be done, the `ranked` field will be set to `false`.
