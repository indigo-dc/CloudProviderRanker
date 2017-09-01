# Building and run a Docker container

To build a docker image you have to first build the Cloud Provider
Ranker `.jar`, as described in the
section [Build from source](chapter02.md).

Then issue the command:

```
docker build -t <IMAGE_NAME> .
```

To run the container:

```
docker run -d --name CloudProviderRanker -p 8080:8080 <IMAGE_NAME>
```

The container will use the default parameters and the customization
files included in the repository (which can be customized with docker
volume mounts).

For example, to start the container with `my_sla_priorities.json`,
`my_paasmetric_normalization.json`, and to make it listening on port
8443 with HTTPS, you can use:

```
docker run -d --name CloudProviderRanker -p 8443:8080 \
    -v my_sla_priorities.json:/cpr/sla_priorities.json \
    -v my_paasmetric_normalization.json:/cpr/paasmetric_normalization.json \
    <IMAGE_NAME>
```

# Run container from Docker Hub

The container can be pulled from the central Docker Hub:

```
docker run -d --name CloudProviderRanker -p 8080:8080 indigodatacloud/cloudproviderranker
```
