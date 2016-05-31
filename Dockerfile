FROM java
EXPOSE 22 7443
MAINTAINER Alvise Dorigo <alvise.dorigo@pd.infn.it>
ADD ./target/CloudProviderRanker-0.3-jar-with-dependencies.jar /root/CloudProviderRanker-0.3-jar-with-dependencies.jar
ADD sla_priorities.json /root/sla_priorities.json
ADD paasmetric_normalization.json /root/paasmetric_normalization.json

RUN java -cp /root/CloudProviderRanker-0.3-jar-with-dependencies.jar org.indigo.cloudproviderruleengine.Main /root/sla_priorities.json /root/paasmetric_normalization.json 8443 >> /var/log/CloudProviderRanker.log 2>&1 &
