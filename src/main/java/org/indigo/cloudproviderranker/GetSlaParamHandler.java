package org.indigo.cloudproviderranker;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GetSlaParamHandler extends RequestHandler {

  //_________________________________________________________________________________________
  @Override
  public final void handle(final HttpExchange httpExchange) throws IOException {
    if (httpExchange.getRequestMethod().compareToIgnoreCase("GET") != 0) {
      String response = "API \"get-sla-parameters\" only supports GET method";
      httpExchange.sendResponseHeaders(405, response.getBytes("UTF8").length);
      OutputStream os = httpExchange.getResponseBody();
      os.write(response.getBytes("UTF8"));
      os.close();
      return;
    }

    clientHostName = httpExchange.getRemoteAddress().getHostName();

    ParseResult pr = new ParseResult(getParams(),  200);

    Headers responseHeaders = httpExchange.getResponseHeaders();
    responseHeaders.set("Content-Type",  "application/json");
    httpExchange.sendResponseHeaders(pr.getHttpCode(),  pr.getMessage().getBytes("UTF8").length);

    OutputStream os = httpExchange.getResponseBody();
    os.write(pr.getMessage().getBytes("UTF8"));
    os.close();
  }

  /**
   * Doc TODO.
   */
  public final String getParams() {
    String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());

    Logger.getLogger("").log(Level.INFO,  timeStamp
                                          + " ["
                                          + clientHostName
                                          + "] New request for "
                                          + "/get-sla-parameters API from this client... ");
    SlaNormalizations slaNormalizations = new SlaNormalizations();
    slaNormalizations.fromDefaultFile();
    slaNormalizations.fromCustomFile();
    String params = slaNormalizations.getParams();
    Logger.getLogger("").log(Level.INFO,  timeStamp
                                          + " ["
                                          + clientHostName
                                          + "] Returning SLA priority parameters to the client: "
                                          + params + "\n\n");
    return params;
  }
}
