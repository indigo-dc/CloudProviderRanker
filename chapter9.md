# Ranking JSON response format
Below the general format of a CloudProviderRanker's JSON response to a ranking request (performed by contacting the ```/rank``` API).
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