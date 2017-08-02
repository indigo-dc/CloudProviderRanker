FROM openjdk:8

# MAINTAINER Alvise Dorigo <alvise.dorigo@pd.infn.it>
LABEL maintainer "Fabrizio Chiarello <fabrizio.chiarello@pd.infn.it>"

EXPOSE 22 7443 8443
#ADD docker_start_script /root/docker_start_script
WORKDIR /cpr

#CMD /usr/bin/java -jar /root/CloudProviderRanker-jar-with-dependencies.jar /root/sla_priorities.json /root/paasmetric_normalization.json 8443 >> /var/log/CloudProviderRanker.log 2>&1 &
#CMD ls -l /var
#RUN chmod +x /root/docker_start_script
#CMD /root/docker_start_script
ADD target/CloudProviderRanker-jar-with-dependencies.jar /cpr
ADD sla_priorities.json /cpr
ADD paasmetric_normalization.json /cpr
