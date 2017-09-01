# Install and run Cloud Provider Ranker

## Installation
The Cloud Provider Ranker WEB Service is made of a single `.jar` file
containing a `Main` class which implements the `main` method.

It acts as a stand-alone server, so no WEB container (JBOSS, Tomcat,
...) is required. The WEB server is implemented using the `HttpServer`
Java library (http://goo.gl/QLBjiP).  To install the artifact just put
it wherever you prefer.

## Launch the server

The Cloud Provider Ranker can be executed by:

```
java -jar [YOUR_PREFERRED_PATH]/CloudProviderRanker-jar-with-dependencies.jar [options] <SLA_PRIORITY_FILE> <PAASMETRIC_NORMALIZATION_FILE>
```

It requires the presence of two arguments pointing to two files
 `<SLA_PRIORITY_FILE>` and `<PAASMETRIC_NORMALIZATION_FILE>`. The
 meaning of content of such files is explained in
 the [Ranking Algorithm](chapter4.md) section.

The optional parameters are:

| Option                 | Description       | Type    | Default |
|------------------------|-------------------|---------|---------|
| `--keystore-path [-k]` | Keystore path     | string  | ""      |
| `--password [-P]`      | Keystore password | string  | ""      |
| `--port [-p]`          | The server port   | integer | 8080    |
| `--rules-file [-r]`    | Rules file        | string  | ""      |

If the `--port TCPPORT` parameter is not specified, the default port
will be 8080.

The `--rules-file` option can be used to specify a file containing
custom ranking rules, as explained in
the [Ranking Algorithm](chapter4.md) section.

### Serving on HTTPS

By default the server starts listening on plain HTTP (not encrypted).

To switch to HTTPS, the options `--keystore-path <filepath>` and
`--password <password>` must be given; to generate a `Keystore` file,
just issue the command:

```
keytool -genkey -alias webservice -keystore <filepath>
```

a password will be asked, and must be used in the command line above.

## Testing the server

To test the server (which responds at the address
`http[s]://<IP_WHERE_YOU_DEPLOYED_IT>:<CHOSEN_TCP_PORT>/rank`) at the
client side just use `cURL`:

```
curl -d @cpr-test.json http[s]://<IP_WHERE_YOU_DEPLOYED_IT>:<CHOSEN_TCP_PORT>/rank
```

The content of the file `cpr-test.json` is described in
the [Ranking JSON Request format](chapter8.md).

The repository contains sample files which can be used. For example,
after starting the server with:

```
$ java -jar target/CloudProviderRanker-jar-with-dependencies.jar sla_priorities.json paasmetric_normalization.json
```

and you can verify it is working with:

```
$ curl -d @request_template_full.json http://127.0.0.1:8080/rank
[{"name":"provider-RECAS-BARI","rank":2.0,"ranked":true,"errorReason":""}, {"name":"provider-UPV-GRyCAP","rank":1.0,"ranked":true,"errorReason":""}]
```

## Installing the RPM/DEB packages and launch the Cloud Provider Ranker

Install the `.rpm` package with:

```
# yum localinstall target/CloudProviderRanker/RPMS/noarch/CloudProviderRanker-0.6.0-1.noarch.rpm
```

or the `.deb` package with:

```
# dpkg -i target/CloudProviderRanker-0.6.0-1_all.deb
```

The `.rpm` should automatically invoke the start script (which is
located in `/etc/init.d/cloudproviderranker`). On Ubuntu the admin
should invoke it manually:

```
# /etc/init.d/cloudproviderranker start
```

By default the service listens on port 8443, without opening an SSL
  socket. The the port 8443 should be exposed to, at least, the host
  running the Orchestrator.
