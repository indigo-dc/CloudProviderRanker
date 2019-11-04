# Cloud Provider Ranker

The
[Cloud Provider Ranker](https://github.com/indigo-dc/CloudProviderRanker) is
a standalone REST WEB Service which ranks cloud provider services using
rules implemented with the [Drools framework](https://drools.org).

The [INDIGO PaaS Orchestrator](https://github.com/indigo-dc/orchestrator)
interacts with this service in order to obtain the rank of two or more cloud
services depending on the match with specific rules.

The aim of this micro component is to fully decouple the ranking logic
from the Orchestrator's business logic.

The Cloud Provider Ranker can be installed on any machine which is
reachable from the Orchestrator via TCP connection (including the
machine running the Orchestrator itself). 

It is a fully stateless service; it can be used by several
Orchestrators, or by any other REST client complying with the expected
JSON request.
