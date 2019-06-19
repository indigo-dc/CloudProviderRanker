FROM openjdk:8

LABEL maintainer "Marica Antonacci <marica.antonacci@ba.infn.it>"

RUN apt-get update && apt-get install -y --no-install-recommends maven
COPY . /cpr
WORKDIR /cpr
RUN mvn compile

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "CloudProviderRanker-jar-with-dependencies.jar" ]

