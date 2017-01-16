package org.indigo.cloudproviderranker;
import com.sun.net.httpserver.HttpHandler;

abstract public class RequestHandler implements HttpHandler {
  protected String clientHostName = "";
}
