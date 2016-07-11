# Building a Docker container
To build a docker image which can spawn a container running the server, you have to build CloudProviderRanker before, by issuing the command

	mvn compile

then issue the command:

	docker build -t <IMAGE_NAME> .

To run the container:

	docker run -t -d -p 7443:7443 -p 8443:8443 <IMAGE_NAME>

To change the TCP port used inside the container edit the file ```Dockerfile``` in the ```EXPOSE``` section.

