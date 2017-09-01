# Cloud Provider Ranker

The
[Cloud Provider Ranker](https://github.com/indigo-dc/CloudProviderRanker) is
a standalone REST WEB Service which ranks cloud providers basing on
rules implemented with the [Drools framework](https://drools.org).

The [INDIGO PaaS Orchestrator](https://github.com/indigo-dc/orchestrator)
interacts with it in order to obtain a ranking of two or more cloud
providers basing on particular rules.

The aim of this micro component is to fully decouple the ranking logic
from the Orchestrator's business logic.

The Cloud Provider Ranker can be installed on any machine which is
reachable from the Orchestrator via TCP connection (including the
machine running the Orchestrator itself). It can be set up to listen on
plain or on SSL socket, using HTTP as transport.

It is a fully stateless service; it can be used by several
Orchestrators, or by any other REST client complying with the expected
JSON request.
