package org.indigo.cloudproviderranker;

/**
 * Doc TODO.
 */
public class ParseResult {
  /**
   * Doc TODO.
   */
  private String messageJson;
  /**
   * Doc TODO.
   */
  private int httpCode;
  /**
   * Doc TODO.
   */
  public ParseResult(final String mex, final int code) {
    this.messageJson = mex;
    this.httpCode = code;
  }
  /**
   * Doc TODO.
   */
  public final String getMessage() {
    return messageJson;
  }
  /**
   * Doc TODO.
   */
  public final int getHttpCode() {
    return httpCode;
  }
}
