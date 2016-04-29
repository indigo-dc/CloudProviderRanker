# Install and run Cloud Provider Ranker

To install the artifact (a single jar) just put it wherever you prefer. What you need are the two dependencies **gson** and **drools** runtime.

They can be downloaded from:
* Drools: http://download.jboss.org/drools/release/6.3.0.Final/droolsjbpm-tools-distribution-6.3.0.Final.zip
* GSon: http://goo.gl/D0q3dl

Uncompress the zip files and put the jars somewhere, e.g. ```/usr/local/share/java/drools``` and ```/usr/local/share/java/gson```.

To run the standalone WEB Server just issue the command:


```java -cp [SOMEPATH]/CloudProviderRanker-[VERSION].jar:/usr/local/share/java/gson/gson-2.6.2.jar:/usr/local/share/java/drools/ant-1.8.2.jar:/usr/local/share/java/droo-launcher-1.8.2.jar:/usr/local/share/java/drools/antlr-runtime-3.5.jar:/usr/local/share/java/drools/commons-codec-1.4.jar:/usr/local/share/java/drools/commons-logging-1.1.1.jar:/usr/local/share/java/drools/dom4j-1.6.1.jar:/usr/local/share/java/drools/drools-beliefs-6.3.0.Final.jar:/usr/local/share/java/drools/drools-compiler-6.3.0.Final.jar:/usr/local/share/java/drools/drools-core-6.3.0.Final.jar:/usr/local/share/java/drools/drools-decisiontables-6.3.0.Final.jar:/usr/local/share/java/drools/drools-persistence-jpa-6.3.0.Final.jar:/usr/local/share/java/drools/drools-pmml-6.3.0.Final.jar:/usr/local/share/java/drools/drools-reteoo-6.3.0.Final.jar:/usr/local/share/java/drools/drools-scorecards-6.3.0.Final.jar:/usr/local/share/java/drools/drools-templates-6.3.0.Final.jar:/usr/local/share/java/drools/drools-verifier-6.3.0.Final.jar:/usr/local/share/java/drools/ecj-4.3.1.jar:/usr/local/share/java/drools/hibernate-jpa-2.0-api-1.0.1.Final.jar:/usr/local/share/java/drools/httpcore-4.3.3.jar:/usr/local/share/java/drools/javassist-3.18.1-GA.jar:/usr/local/share/java/drools/kie-api-6.3.0.Final.jar:/usr/local/share/java/drools/kie-ci-6.3.0.Final.jar:/usr/local/share/java/drools/kie-internal-6.3.0.Final.jar:/usr/local/share/java/drools/knowledge-api-6.3.0.Final.jar:/usr/local/share/java/drools/mvel2-2.2.6.Final.jar:/usr/local/share/java/drools/slf4j-api-1.7.2.jar:/usr/local/share/java/drools/xstream-1.4.7.jar org.indigo.cloudproviderruleengine.RESTEngine [TCPPORT] [KeystoreFile password]```

To generate a Keystore file, just issue the command:

```keytool -genkey -alias webservice -keystore <filepath>```

----------------------------

To test it at the client side just use ```cURL``` in this way:

```curl -k -X POST -d '{"cloudproviders":[{"id":1, "totalVEPHDISK":10, "inUseVEPHDISK": 1, "name":"padova", "totalVCPU":10, "totalVRAM":32, "totalVDISK":10, "inUseVCPU":7, "inUseVRAM":8, "inUseVDISK":1 }, {"id":2, "totalVEPHDISK":10, "inUseVEPHDISK": 9, "name":"legnaro", "totalVCPU":5, "totalVRAM":7, "totalVDISK":5, "inUseVCPU":1, "inUseVRAM":4, "inUseVDISK":2 }, {"id":3, "totalVEPHDISK":10, "inUseVEPHDISK": 10, "name":"torino", "totalVCPU":10, "totalVRAM":16, "totalVDISK":10, "inUseVCPU":3, "inUseVRAM":8, "inUseVDISK":3}]}' http://localhost:8443/rank```

The JSON request must be an array of cloud providers. Each cloud provider must contain a certain number of mandatory fields:

```{  
    "cloudproviders":[  
        {  
            "id":1,
            "name":"padova",
	    "totalVEPHDISK":10,
            "totalVCPU":10,
            "totalVRAM":32,
            "totalVDISK":10,
	    "inUseVEPHDISK":1,
            "inUseVCPU":3,
            "inUseVRAM":8,
            "inUseVDISK":1
        },
        { 
		... 
        },
	...
    ]
}```

You'll receive an output like this:
```{  
    "rankedcloudproviders":[  
        {  
            "id":1,
            "name":"padova",
            "rank":2.85,
            "ranked":true,
            "errorReason":""
        },
        {  
            "id":2,
            "name":"legnaro",
            "rank":1.9285715,
            "ranked":true,
            "errorReason":""
        },
        ...
    ]
}```

In case of a missing field in the user's request, an output like this will be returned:

```{  
    "rankedcloudproviders":[  
        {  
            ...
        },
	...
        {  
            "id":3,
            "name":"torino",
            "rank":0.0,
	    "ranked":false,
            "errorReason":"Not ranked provider: Missing inUseVDISK field"
        }
    ]
}```

--------------------


## Making a Docker container
To build a docker image which can spawn a container running the server, you have to build CloudProviderRanker before, by issuing the command

	mvn install

then issue the command:

	docker build -t [IMAGE_NAME]

To run the container:

	docker run -t -d -p 7443:7443 -p 8443:8443 dorigoa/cloudprovider-ranker

To change the TCP port used inside the container, edit the ```startup-cpre``` script and re-build the docker image and also the file ```Dockerfile``` in the ```EXPOSE``` section.

To debug the server, an SSHD daemon is started by the ```startup-cpre``` script. It also sets the user "centos" with password "centos".

------------------------------

## Basic current ranking algorithm
Current ranking logic is quite trivial and can be used as proof of concept.
The higher the ranking, the better the provider.
The rank is equal to the sum of the following pieces



* ```(TOTAL VCPU - USED VCPU)/TOTAL VCPU```
* ```(TOTAL VDISK - USED VDISK)/TOTAL VDISK```
* ```(TOTAL VRAM - USED VRAM)/TOTAL VRAM```
* ```(TOTAL VEPHDISK - USED VEPHDISK)/TOTAL VEPHDISK```

```VDISK``` is the block storage (Cinder), ```VEPHDISK``` is the ephemeral storage disk available for instances.

If at least one of the couples

* ```(TOTAL VCPU - USED VCPU)```
* ```(TOTAL VDISK - USED VDISK)```
* ```(TOTAL VRAM - USED VRAM)```
* ```(TOTAL VEPHDISK - USED VEPHDISK)```

is zero (a particular virtual resource is exhausted), then the provider receives a ZERO rank.

The ranking is implemented in the rule file (and not in the Java code) which is handled by the Drools runtime framework.