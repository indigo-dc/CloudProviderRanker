package org.indigo.cloudproviderranker;

import com.sun.net.httpserver.HttpExchange;
//import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.Headers;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.io.IOException;
import java.io.OutputStream;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.BufferedReader;
//import java.util.Date;
//import com.google.gson.JsonArray;
//import com.google.gson.Gson;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
import java.text.SimpleDateFormat;

public class GetPaaSParamHandler extends RequestHandler {

  //_________________________________________________________________________________________
  @Override
  public final void handle(final HttpExchange httpExchange) throws IOException {
    if (httpExchange.getRequestMethod().compareToIgnoreCase("GET") != 0) {
      String response = "API \"get-paas-parameters\" only supports GET method";
      httpExchange.sendResponseHeaders(405,  response.getBytes().length);
      OutputStream os = httpExchange.getResponseBody();
      os.write(response.getBytes());
      os.close();
      return;
    }

    clientHostName = httpExchange.getRemoteAddress().getHostName();

    ParseResult pr = new ParseResult(getParams(),  200);

    Headers responseHeaders = httpExchange.getResponseHeaders();
    responseHeaders.set("Content-Type",  "application/json");
    httpExchange.sendResponseHeaders(pr.getHttpCode(),  pr.getMessage().getBytes().length);

    OutputStream os = httpExchange.getResponseBody();
    os.write(pr.getMessage().getBytes());
    os.close();
  }

  /**
   * Doc Todo.
   */
  public String getParams() {
    String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());

    Logger.getLogger("").log(Level.INFO,  timeStamp
                             + " ["
                             + clientHostName
                             + "] New request for /get-paas-parameters API from this client... ");
    PaaSMetricNormalization paaSMetricNormalization = null;
    try {
      paaSMetricNormalization = new PaaSMetricNormalization(true);
    } catch (Exception e) {
      Logger.getLogger("").log(Level.SEVERE, timeStamp
                               + " - PaaSMetricNormalization object initialization failed: "
                               + e);
    }
    String params = paaSMetricNormalization.getParams();
    Logger.getLogger("").log(Level.INFO,  timeStamp
                             + " ["
                             + clientHostName
                             + "] Returning normalization parameters to the client: "
                             + params
                             + "\n\n");
    return params;
  }
}
