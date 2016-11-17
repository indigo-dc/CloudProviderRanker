# Install and run Cloud Provider Ranker

## Installation
The CloudProviderRanker WEB Service is made of a single .jar file containing a Main class which implements the ```main``` method. It's about a stand-alone server, so no WEB container (JBOSS, Tomcat, ...) is required. The WEB server is implemented using the HttpServer Java library (http://goo.gl/QLBjiP).
To install the artifact just put it wherever you prefer.
## Launch the server
To run the standalone WEB Server just issue the command:

    java -jar [YOUR_PREFERRED_PATH]/CloudProviderRanker-jar-with-dependencies.jar  <SLA_PRIORITY_FILE> <PAASMETRIC_NORMALIZATION_FILE> [TCPPORT] [KeystoreFile password]

The meaning of the content of two files ```<SLA_PRIORITY_FILE>``` and ```<PAASMETRIC_NORMALIZATION_FILE>``` is explained in the algorithm section.

By default the server is started listening on plain HTTP (not encrypted). To switch on a HTTPS server, it must be started with the options ```KeystoreFile``` and ```password```; to generate a Keystore file, just issue the command:

    keytool -genkey -alias webservice -keystore <filepath>

a password will be asked, and must be used in the command line above.

If the ```TCPPORT``` parameter is not specified, the default port will be 8080.
If the couple of parameters ```KeystoreFile``` and ```password``` are not specified, the server will start listening on plain HTTP, otherwise it will start with HTTPS.

----------------------------
## Testing the server
To test the server (which responds at the address ```http[s]://<IP_WHERE_YOU_DEPLOYED_IT>:<CHOSEN_TCP_PORT>/rank```) at the client side just use ```cURL``` with the content saved at this URL:

```
[http://pastebin.com/KRWhC1aS](http://pastebin.com/KRWhC1aS)
```

Save the content of the previous URL into a file ```cpr-test.json```, then execute the command:

```
curl -d @cpr-test.json http[s]://<IP_WHERE_YOU_DEPLOYED_IT>:<CHOSEN_TCP_PORT>/rank
```

You'll receive an output like this:

    ```{{"name":"provider-RECAS-BARI","rank":57.02,"ranked":true,"errorReason":""},{"name":"provider-UPV-GRyCAP","rank":3017.123,"ranked":true,"errorReason":""}} ```

In the case of a JSON syntax error, the exception is reported to the client:

```Exception parsing JSON client request: com.google.gson.stream.MalformedJsonException: Unterminated object at line 1 column 6141 path $.monitoring[1].metrics[3].paasThresholds```

```Exception parsing JSON client request: com.google.gson.stream.MalformedJsonException: Unexpected value at line 1 column 6338 path $.monitoring[1].metrics[4].metricValue```

```Exception parsing JSON client request: com.google.gson.stream.MalformedJsonException: Unterminated array at line 1 column 4835 path $.monitoring[2]```

```Exception parsing JSON client request: com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed JSON at line 1 column 8 path $```

## Installing the RPM/DEB packages and launch the CloudProviderRanker
Install the rpm/deb package as usual:
```
yum install CloudProviderRanker
```
```
apt-get install cloudproviderranker
```
The RPM should automatically invoke the start script (which is located in `/etc/init.d/cloudproviderranker`). In Ubuntu the admin should invoke it manually:
```
/etc/init.d/cloudproviderranker start
```
The start/stop script set the service listening on port 8443 (without opening an SSL socket, but a plain one).
The the port 8443 should be exposed to, at least, the host running the Orchestrator.