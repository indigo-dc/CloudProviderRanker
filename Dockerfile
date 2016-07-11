FROM java
EXPOSE 22 7443 8443
MAINTAINER Alvise Dorigo <alvise.dorigo@pd.infn.it>
ADD ./target/CloudProviderRanker-jar-with-dependencies.jar /root/CloudProviderRanker-jar-with-dependencies.jar
ADD sla_priorities.json /root/sla_priorities.json
ADD paasmetric_normalization.json /root/paasmetric_normalization.json
#ADD docker_start_script /root/docker_start_script

#CMD /usr/bin/java -jar /root/CloudProviderRanker-jar-with-dependencies.jar /root/sla_priorities.json /root/paasmetric_normalization.json 8443 >> /var/log/CloudProviderRanker.log 2>&1 &
#CMD ls -l /var
#RUN chmod +x /root/docker_start_script
#CMD /root/docker_start_script
