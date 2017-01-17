package org.indigo.cloudproviderranker;

import com.sun.net.httpserver.HttpExchange;
//import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.Headers;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
//import java.util.Date;
//import com.google.gson.JsonArray;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.text.SimpleDateFormat;

public class CustomSLAParamHandler extends RequestHandler {

  //_________________________________________________________________________________________
  @Override
  public final void handle(final HttpExchange httpExchange) throws IOException {
    if (httpExchange.getRequestMethod().compareToIgnoreCase("POST") != 0) {
      String response = "API \"custom-sla-parameters\" only supports POST method";
      httpExchange.sendResponseHeaders(405,  response.getBytes().length);
      OutputStream os = httpExchange.getResponseBody();
      os.write(response.getBytes());
      os.close();
      return;
    }

    clientHostName = httpExchange.getRemoteAddress().getHostName();

    updateParams(httpExchange.getRequestBody()/*Line*/);

    Headers responseHeaders = httpExchange.getResponseHeaders();
    responseHeaders.set("Content-Type",  "application/json");
    httpExchange.sendResponseHeaders(200,  0);

    OutputStream os = httpExchange.getResponseBody();
    os.write("".getBytes());
    os.close();
  }

  public final void updateParams(final InputStream is/*String line*/) {
    String line = "";
    try {
      InputStreamReader inputReader = new InputStreamReader(is, "utf-8");
      BufferedReader buffReader = new BufferedReader(inputReader);
      String sline = "";
      while ((sline = buffReader.readLine()) != null) {
        line += sline;
      }
    } catch (IOException ioe) {

    }

    String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());

    Logger.getLogger("").log(Level.INFO,  timeStamp
                             + " ["
                             + clientHostName
                             + "] New request for "
                             + "/custom-sla-parameters API from this client... ");
    SlaNormalizations slaNormalizations = new SlaNormalizations();
    slaNormalizations.fromDefaultFile();
    slaNormalizations.fromCustomFile();

    Gson gson = new Gson();

    JsonObject obj = gson.fromJson(line,  JsonElement.class).getAsJsonObject();
    if (obj.has("computing_time")) {
      slaNormalizations.computingTime = obj.get("computing_time").getAsFloat();
    }
    if (obj.has("num_cpus")) {
      slaNormalizations.numCpus = obj.get("num_cpus").getAsFloat();
    }
    if (obj.has("mem_size")) {
      slaNormalizations.memSize = obj.get("mem_size").getAsFloat();
    }
    if (obj.has("disk_size")) {
      slaNormalizations.diskSize = obj.get("disk_size").getAsFloat();
    }
    if (obj.has("public_ip")) {
      slaNormalizations.publicIp = obj.get("public_ip").getAsFloat();
    }
    if (obj.has("upload_bandwidth")) {
      slaNormalizations.uploadBandwidth = obj.get("upload_bandwidth").getAsFloat();
    }
    if (obj.has("download_bandwidth")) {
      slaNormalizations.downloadBandwidth = obj.get("download_bandwidth").getAsFloat();
    }
    if (obj.has("upload_aggregated")) {
      slaNormalizations.uploadAggregated = obj.get("upload_aggregated").getAsFloat();
    }
    if (obj.has("download_aggregated")) {
      slaNormalizations.downloadAggregated = obj.get("download_aggregated").getAsFloat();
    }
    if (obj.has("infinity_value")) {
      slaNormalizations.infinityValue = obj.get("infinity_value").getAsFloat();
    }

    slaNormalizations.toCustomFile();
  }

}
