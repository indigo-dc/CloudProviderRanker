# Service Reference Card


## Functional description:
A REST WEB Service which ranks cloud providers.


## Daemons running:

`cloudproviderranker` (Java Application): `org.indigo.cloudproviderranker.Main`


## Init scripts and options:

If installed through `.rpm/.deb` packages the server daemon can be
controlled by `/etc/init.d/cloudproviderranker (start|stop|status)`.

If started as a docker container (`docker run`) the server can be
controlled by standard docker commands (`pause`, `stop`, ...).


## Configuration files location with example template:

  * `/usr/share/java/cpr/sla_priorities.json`
    (https://raw.githubusercontent.com/indigo-dc/CloudProviderRanker/master/sla_priorities.json)
  * `/usr/share/java/cpr/paasmetric_normalization.json` (https://raw.githubusercontent.com/indigo-dc/CloudProviderRanker/master/paasmetric_normalization.json)
  * `/usr/share/java/cpr/DefaultRules.drl` (https://raw.githubusercontent.com/indigo-dc/CloudProviderRanker/master/DefaultRules.drl)


## Logfile locations:

Logs are saved in `/var/log/CloudProviderRanker.log`. Log messages are
also printed on stdout/stderr, therefore if running through docker, logs
can be accessed with the `docker logs` command.


## Open ports:

By default port tcp/8080 is used. The admin can change it by launching
the daemon with custom options.


## Possible unit test of the service:

Continuous unit testing is conducted during development
via [Jenkins](https://jenkins.indigo-datacloud.eu:8080).


## Where is service state held (and can it be rebuilt):

Service state is held in two files:

  * `/usr/share/java/cpr/sla_priorities-custom.json`
  * `/usr/share/java/cpr/paasmetric_normalization-custom.json`

The state can be rebuilt by copying the two files.

Alternatively, the content of the above files can be accessed and
recreated by using following the REST API, detailed in the [API](api.md)
chapter:

  * `/usr/share/java/cpr/sla_priorities-custom.json`
    * read: `/get-sla-parameters`
    * write: `/custom-sla-parameters`

  * `/usr/share/java/cpr/paasmetric_normalization-custom.json`
    * read: `/get-paas-parameters`
    * write: `/custom-paas-parameters`


## Cron jobs:

None.


## Security information:

If launched with a keystore file (which is not the default) the service
will listen on HTTPS.

Inbound and outbound traffic to the server port (default tcp/8080) must
be allowed from/to the Orchestrator.

It's advised to enable serving HTTPS traffic or to put the service
behind a reverse proxy with HTTPS enabled.
