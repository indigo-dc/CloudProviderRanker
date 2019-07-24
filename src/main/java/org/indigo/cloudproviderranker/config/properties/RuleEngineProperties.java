package org.indigo.cloudproviderranker.config.properties;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;


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
