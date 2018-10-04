FROM openjdk:8

# MAINTAINER Alvise Dorigo <alvise.dorigo@pd.infn.it>
LABEL maintainer "Fabrizio Chiarello <fabrizio.chiarello@pd.infn.it>"

RUN apt-get update && apt-get install -y --no-install-recommends maven
COPY . /cpr
WORKDIR /cpr
RUN mvn compile

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "CloudProviderRanker-jar-with-dependencies.jar" ]
CMD [ "sla_priorities.json", "paasmetric_normalization.json" ]

