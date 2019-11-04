# Service Reference Card


## Functional description:
A REST Web Service which ranks cloud services on specific monitoring and SLA based critera.


## Daemons running:

`cloudproviderranker` (Java Application): `org.indigo.cloudproviderranker.Application`

## Supported environment

RPM/DEB package are provided for the following Operating Systems:
- Ubuntu 16.04
- Centos 7
Their installation require the presence of jdk 8.

On other platforms the provided init scripts may need modifications to work properly. Anyway you can always run the service using directly the installed jar file or run it as docker container

### Init scripts and options:

If installed through `.rpm/.deb` packages the server daemon can be
controlled by `systemctl (start|stop|status) cloudproviderranker`.

If started as a docker container (`docker run`) the server can be
controlled by standard docker commands (`pause`, `stop`, ...).


## Logfile locations:

Logs are saved in `/var/log/CloudProviderRanker.log`. Log messages are
also printed on stdout/stderr, therefore if running through docker, logs
can be accessed with the `docker logs` command.


## Open ports:

By default port tcp/8080 is used. The admin can change it by launching
the daemon with custom options.


## Security information:

This services is available without authentication and does not handle sensitive data. Anyway, it can be put behind a reverse proxy to provide HTTPS support.

Inbound and outbound traffic to the server port (default tcp/8080) must be allowed from the Orchestrator.
