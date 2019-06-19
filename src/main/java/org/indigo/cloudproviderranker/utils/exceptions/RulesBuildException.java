package org.indigo.cloudproviderranker.utils.exceptions;

import org.kie.api.builder.Results;


/**
 * Doc TODO.
 */
public class RulesBuildException extends RuntimeException {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

/**
   * Doc TODO.
   */
  public RulesBuildException(final Results results) {
    super("Rules Build Errors:\n" + results);
  }
}