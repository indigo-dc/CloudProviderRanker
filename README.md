# CloudProviderRanker
A standalone REST WEB service which ranks cloud provider basing on rules implemented with the drools framework

To install the artifact (a single jar) just put it wherever you prefer. What you need are the two dependecies gson and drools runtime.

They can be downloaded from:

Drools: http://goo.gl/jvXYI5

and

GSon: http://goo.gl/D0q3dl

Uncompress the zip and put the jars somewhere, e.g. /usr/local/share/java/drools and /usr/local/share/java/gson.

To run the standalone WEB Server just issue the command:


java -cp <SOMEPATH>/cpre-0.1.jar:/usr/local/share/java/gson/gson-2.6.2.jar:/usr/local/share/java/drools/ant-1.8.2.jar:/usr/local/share/java/drools/ant-launcher-1.8.2.jar:/usr/local/share/java/drools/antlr-runtime-3.5.jar:/usr/local/share/java/drools/commons-codec-1.4.jar:/usr/local/share/java/drools/commons-logging-1.1.1.jar:/usr/local/share/java/drools/dom4j-1.6.1.jar:/usr/local/share/java/drools/drools-beliefs-6.3.0.Final.jar:/usr/local/share/java/drools/drools-compiler-6.3.0.Final.jar:/usr/local/share/java/drools/drools-core-6.3.0.Final.jar:/usr/local/share/java/drools/drools-decisiontables-6.3.0.Final.jar:/usr/local/share/java/drools/drools-jsr94-6.3.0.Final.jar:/usr/local/share/java/drools/drools-persistence-jpa-6.3.0.Final.jar:/usr/local/share/java/drools/drools-pmml-6.3.0.Final.jar:/usr/local/share/java/drools/drools-reteoo-6.3.0.Final.jar:/usr/local/share/java/drools/drools-scorecards-6.3.0.Final.jar:/usr/local/share/java/drools/drools-templates-6.3.0.Final.jar:/usr/local/share/java/drools/drools-verifier-6.3.0.Final.jar:/usr/local/share/java/drools/ecj-4.3.1.jar:/usr/local/share/java/drools/guava-13.0.1.jar:/usr/local/share/java/drools/hibernate-jpa-2.0-api-1.0.1.Final.jar:/usr/local/share/java/drools/httpcore-4.3.3.jar:/usr/local/share/java/drools/itext-2.1.2.jar:/usr/local/share/java/drools/javassist-3.18.1-GA.jar:/usr/local/share/java/drools/javax.inject-1.jar:/usr/local/share/java/drools/jcl-over-slf4j-1.7.2.jar:/usr/local/share/java/drools/jsr94-1.1.jar:/usr/local/share/java/drools/kie-api-6.3.0.Final.jar:/usr/local/share/java/drools/kie-ci-6.3.0.Final.jar:/usr/local/share/java/drools/kie-internal-6.3.0.Final.jar:/usr/local/share/java/drools/knowledge-api-6.3.0.Final.jar:/usr/local/share/java/drools/mvel2-2.2.6.Final.jar:/usr/local/share/java/drools/poi-ooxml-3.10.1.jar:/usr/local/share/java/drools/poi-ooxml-schemas-3.10.1.jar:/usr/local/share/java/drools/slf4j-api-1.7.2.jar:/usr/local/share/java/drools/xml-apis-1.3.04.jar:/usr/local/share/java/drools/xstream-1.4.7.jar org.indigo.cloudproviderruleengine.RESTEngine [TCPPORT]
