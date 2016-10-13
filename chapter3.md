# Building and run a Docker container
To build a docker image which can spawn a container running the server, you have to build CloudProviderRanker before (see section "Build from source...", by issuing the command:

	mvn compile

then issue the command:

	docker build -t <IMAGE_NAME> .

To run the container:

	docker run --name CloudProviderRanker -p 8443:7443 -t -d -i <IMAGE_NAME> /usr/bin/java -jar /root/CloudProviderRanker-jar-with-dependencies.jar /root/sla_priorities.json /root/paasmetric_normalization.json 7443

The sources of the parameter files ```/root/sla_priorities.json``` and ```/root/paasmetric_normalization.json``` should be customized before the build phase; those sources can be found in the cloned git repo:

* CloudProviderRanker/paasmetric_normalization.json
* CloudProviderRanker/sla_priorities.json


# Run container from DockerHub
The container can be pulled from the central Docker Hub:
```
docker pull indigodatacloud/cloudproviderranker
```
and then run as explained above.