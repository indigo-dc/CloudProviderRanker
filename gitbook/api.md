# API

The provided APIs are:

  * POST `/rank`
  

## `/rank`

As already introduced in the [Running the Cloud Provider Ranker](running.md) chapter, it performs the ranking of the services provided by cloud providers based on an internal algorithm which uses weights and normalization parameters (described in the [Ranking Algorithm](ranking_algorithm.md) chapter). 

The payload the client must send in a POST HTTP request is described in the [Ranking Algorithm](ranking_algorithm.md) chapter. 

The [Ranking JSON request format](json_request_format.md) chapter describes the request the user must submit to the ```/rank``` API, and the [Ranking JSON response format](json_response_format.md) chapter describes the format of the returned response.


