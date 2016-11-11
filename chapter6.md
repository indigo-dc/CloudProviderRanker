
### Functional description:
A REST WEB Service which ranks cloud providers


### Daemons running:
cloudproviderranker (Java Application): org.indigo.cloudproviderranker.Main


### Init scripts and options:
/etc/init.d/cloudproviderranker (start|stop|status)


### Configuration files location with example template:
/usr/share/java/cpr/sla_priorities.json (https://raw.githubusercontent.com/indigo-dc/CloudProviderRanker/master/sla_priorities.json)
/usr/share/java/cpr/paasmetric_normalization.json (https://raw.githubusercontent.com/indigo-dc/CloudProviderRanker/master/paasmetric_normalization.json)


### Logfile locations:
/var/log/CloudProviderRanker.log


### Open ports:
Default 8443
The admin can change it by launching the daemon with custom options


### Possible unit test of the service:
None


### Where is service state held (and can it be rebuilt):
Service is stateless


### Cron jobs:
None


### Security information:
if launched with a keystore file (which is not the default, i.e. the init.d scripts launch it without the keystore file) the service will listen on HTTPS.