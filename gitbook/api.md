# API

The provided APIs are:

  * `/rank`
  

## `/rank`

This has been already introduced in the [Running the Cloud Provider Ranker](running.md) chapter.
It performs the ranking of cloud providers based on an internal algorithm which uses weights and normalization parameters (described in the [Ranking Algorithm](ranking_algorithm.md) chapter). The payload the client must send in a POST HTTP request is described in the [Ranking Algorithm](ranking_algorithm.md) chapter. In the [Ranking JSON request format](json_request_format.md) chapter is shown the request the user must submit to the ```/rank``` API, and in the [Ranking JSON response format](json_response_format.md) chapter is described the format of the returned response.


