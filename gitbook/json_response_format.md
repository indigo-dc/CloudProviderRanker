# Ranking JSON response format

Below the general format of a Cloud Provider Ranker's JSON response to a
ranking request (performed by contacting the `/rank` API).

```
[
    {
      "name": "PROVIDER_NAME_1",
      "rank": 96000,
      "ranked": true,
      "errorReason": ""
    },
    {
      "name": "PROVIDER_NAME_2",
      "rank": 33110,
      "ranked": true,
      "errorReason": ""
    }
]
```

If an error occurs and for a particular cloud provider the ranking
cannot be done, the `ranked` field will be `false` and the reason will
be inserted into the `errorReason` field of the above response.
