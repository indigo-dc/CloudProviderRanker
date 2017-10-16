package org.indigo.cloudproviderranker;

import com.sun.net.httpserver.HttpHandler;

/**
 * Doc TODO.
 */
public abstract class RequestHandler implements HttpHandler {

  /**
   * Doc TODO.
   */
  protected String clientHostName = "";

  protected RequestHandler() { }
}
