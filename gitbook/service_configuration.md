# Service Configuration

The Cloud Provider Ranker comes with two default rules files embedded in the jar.

You can provide custom rules, external to the jar, specifying their paths in the application.properties file, using the list field `rule-engine.rules`, e.g.:

````
rule-engine.rules[0]=/cpr/config/rules/SlaScoreRule.drl
rule-engine.rules[1]=/cpr/config/resources/rules/MetricsScoreRule.drl
````

Note that you can provide a custom `application.properties` file using the environment variable `SPRING_CONFIG_LOCATION`:

````
export SPRING_CONFIG_LOCATION=/cpr/config
java -jar CloudProviderRanker.jar 
````

If you are using the docker image, you have to bind-mount the configuration and rules files. 

For example, if you are using the properties above, you could create the folder `config` with the application.properties and the rules files:

````
config
├── application.properties
└── rules
    ├── MetricsScoreRule.drl
    └── SlaScoreRule.drl
`````

and then run the container as follows:

````
docker run -d --name cpr -p 8080:8080 -e SPRING_CONFIG_LOCATION=/cpr/config -v $PWD/config:/cpr/config <IMAGE_NAME>
````




