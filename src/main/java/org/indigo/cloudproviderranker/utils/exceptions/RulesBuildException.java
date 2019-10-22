package org.indigo.cloudproviderranker.utils.exceptions;

import org.kie.api.builder.Results;

// TODO docs

/**
 * Doc TODO.
 */
public class RulesBuildException extends RuntimeException {

  /** serialVersionUID.
   * 
   */
  private static final long serialVersionUID = 1L;

  private Results results = null;

  /**
   * RulesBuildException.
   */
  public RulesBuildException(final Results results) {
    super("Rules Build Errors:\n" + results);
    this.results = results;
  }

  /** getResults() method.
   * 
   * @return
   */
  public Results getResults() {
    return results;
  }
}