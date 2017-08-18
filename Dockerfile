FROM openjdk:8

# MAINTAINER Alvise Dorigo <alvise.dorigo@pd.infn.it>
LABEL maintainer "Fabrizio Chiarello <fabrizio.chiarello@pd.infn.it>"

WORKDIR /cpr

ADD target/CloudProviderRanker-jar-with-dependencies.jar /cpr
ADD sla_priorities.json /cpr
ADD paasmetric_normalization.json /cpr

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "CloudProviderRanker-jar-with-dependencies.jar" ]
CMD [ "sla_priorities.json", "paasmetric_normalization.json" ]
