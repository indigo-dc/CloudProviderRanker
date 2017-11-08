package org.indigo.cloudproviderranker;

import org.kie.api.builder.Results;


/**
 * Doc TODO.
 */
public class RulesBuildException extends RuntimeException {
  /**
   * Doc TODO.
   */
  public RulesBuildException(final Results results) {
    super("Rules Build Errors:\n" + results);
  }
}
