package org.indigo.cloudproviderranker.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;



@ConfigurationProperties(prefix = "rule-engine")
public class RuleEngineProperties {
    private List<String> rules;

    public List<String> getRules() {
        return rules;
    }

    public void setRules(List<String> rules) {
        this.rules = rules;
    }
}
