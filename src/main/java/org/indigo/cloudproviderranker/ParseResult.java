package org.indigo.cloudproviderruleengine;

public class ParseResult {
  private String messageJson;
  private int httpCode;

  public ParseResult( String mex, int code ) {
    this.messageJson = mex;
    this.httpCode = code;
  }

  public String getMessage( ) { return messageJson; }
  public int getHTTPCode( ) { return httpCode; }
}
