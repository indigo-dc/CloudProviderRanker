package org.indigo.cloudproviderranker;

public class ParseResult {
  private String messageJson;
  private int httpCode;

  public ParseResult(final String mex, final int code) {
    this.messageJson = mex;
    this.httpCode = code;
  }

  public final String getMessage() {
    return messageJson;
  }
  public final int getHTTPCode() {
    return httpCode;
  }
}
