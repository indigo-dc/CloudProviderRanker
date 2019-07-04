# Running the Cloud Provider Ranker

## Installation
The Cloud Provider Ranker Service is a Spring Boot Application.
It consists of a single `.jar` file containing a `Main` class 
which implements the `main` method.


## Launch the server

The Cloud Provider Ranker can be executed by:

```
java -jar [YOUR_PREFERRED_PATH]/CloudProviderRanker.jar
```

The service listens on the default port 8080.


## Testing the server

To test the server (which responds at the address
`http://<IP_WHERE_YOU_DEPLOYED_IT>:8080/rank`) at the
client side just use `cURL`:

```
curl -H 'Content-Type: application/json' -X POST http://<IP_WHERE_YOU_DEPLOYED_IT>:8080/rank -d@cpr-test.json
```

The content of the file `cpr-test.json` is described in
the [Ranking JSON request format](json_request_format.md).

The repository contains sample files which can be used.


## Running a Docker container

To run the container:

```
docker run -d --name CloudProviderRanker -p 8080:8080 <IMAGE_NAME>
```

### Run container from Docker Hub

The pre-built container can also be pulled from the central Docker Hub:

```
docker run -d --name CloudProviderRanker -p 8080:8080 indigodatacloud/cloudproviderranker
```
