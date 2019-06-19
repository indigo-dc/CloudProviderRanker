FROM openjdk:8

LABEL maintainer "Marica Antonacci <marica.antonacci@ba.infn.it>"

RUN apt-get update && apt-get install -y --no-install-recommends maven
COPY . /cpr
WORKDIR /cpr
RUN mvn clean install -Dmaven.test.skip=true

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "target/CloudProviderRanker.jar" ]

