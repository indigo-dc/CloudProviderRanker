package org.indigo.cloudproviderranker;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.text.SimpleDateFormat;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomPaaSParamHandler extends RequestHandler {

  //_________________________________________________________________________________________
  @Override
  public final void handle(final HttpExchange httpExchange) throws IOException {
    if (httpExchange.getRequestMethod().compareToIgnoreCase("POST") != 0) {
      String response = "api \"custom-monitoring-parameters\" only supports POST method";
      httpExchange.sendResponseHeaders(405,  response.getBytes().length);
      OutputStream os = httpExchange.getResponseBody();
      os.write(response.getBytes());
      os.close();
      return;
    }

    clientHostName = httpExchange.getRemoteAddress().getHostName();

    updateParams(httpExchange.getRequestBody());


    Headers responseHeaders = httpExchange.getResponseHeaders();
    responseHeaders.set("Content-Type",  "application/json");
    httpExchange.sendResponseHeaders(200,  0);

    OutputStream os = httpExchange.getResponseBody();
    os.write("".getBytes());
    os.close();
  }
  /**
   * Doc TODO.
   */
  public final void updateParams(final InputStream is /*String line*/) {
    String line = "";
    try {
      //InputStream is = httpExchange.getRequestBody();
      InputStreamReader inputReader = new InputStreamReader(is, "utf-8");
      BufferedReader buffReader = new BufferedReader(inputReader);
      String sline = "";
      while ((sline = buffReader.readLine()) != null) {
        line += sline;
      }
    } catch (IOException ioe) {
      // Nothing todo
    }

    String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());

    Logger.getLogger("").log(Level.INFO,  timeStamp
                             + " [" + clientHostName
                             + "] New request for /custom-monitoring-parameters"
                             + " api from this client... ");
    PaaSMetricNormalization paaSMetricNormalization = new PaaSMetricNormalization(true);

    Gson gson = new Gson();

    JsonObject obj = gson.fromJson(line,  JsonElement.class).getAsJsonObject();
    if (obj.has(CustomPaaSParams.OCCI_CREATE_VM_AVAILABILITY)) {
      int vma = obj.get(CustomPaaSParams.OCCI_CREATE_VM_AVAILABILITY).getAsInt();
      paaSMetricNormalization.setocciCreatevmAvailability(vma);
    }
    if (obj.has(CustomPaaSParams.OCCI_CREATEVM_RESPONSE_TIME)) {
      float vmrt = obj.get(CustomPaaSParams.OCCI_CREATEVM_RESPONSE_TIME).getAsFloat();
      paaSMetricNormalization.setocciCreatevmResponseTime(vmrt);
    }
    if (obj.has(CustomPaaSParams.OCCI_CREATEVM_RESULT)) {
      int vmr = obj.get(CustomPaaSParams.OCCI_CREATEVM_RESULT).getAsInt();
      paaSMetricNormalization.setocciCreatevmResult(vmr);
    }
    if (obj.has(CustomPaaSParams.OCCI_DELETE_VM_AVAILABILITY)) {
      int vma = obj.get(CustomPaaSParams.OCCI_DELETE_VM_AVAILABILITY).getAsInt();
      paaSMetricNormalization.setocciDeletevmAvailability(vma);
    }
    if (obj.has(CustomPaaSParams.OCCI_DELETEVM_RESPONSE_TIME)) {
      float vmrt = obj.get(CustomPaaSParams.OCCI_DELETEVM_RESPONSE_TIME).getAsFloat();
      paaSMetricNormalization.setocciDeletevmResponseTime(vmrt);
    }
    if (obj.has(CustomPaaSParams.OCCI_DELETEVM_RESULT)) {
      int vmr = obj.get(CustomPaaSParams.OCCI_DELETEVM_RESULT).getAsInt();
      paaSMetricNormalization.setocciDeletevmResult(vmr);
    }
    if (obj.has(CustomPaaSParams.GENERAL_OCCI_API_AVAILABILITY)) {
      int vma = obj.get(CustomPaaSParams.GENERAL_OCCI_API_AVAILABILITY).getAsInt();
      paaSMetricNormalization.setgeneralOcciApiAvailability(vma);
    }
    if (obj.has(CustomPaaSParams.GENERAL_OCCI_API_RESPONSE_TIME)) {
      float vmrt = obj.get(CustomPaaSParams.GENERAL_OCCI_API_RESPONSE_TIME).getAsFloat();
      paaSMetricNormalization.setgeneralOcciApiResponseTime(vmrt);
    }
    if (obj.has(CustomPaaSParams.GENERAL_OCCI_API_RESULT)) {
      int vmr = obj.get(CustomPaaSParams.GENERAL_OCCI_API_RESULT).getAsInt();
      paaSMetricNormalization.setgeneralOcciApiResult(vmr);
    }
    if (obj.has(CustomPaaSParams.OCCI_INSPECT_VM_AVAILABILITY)) {
      int vma = obj.get(CustomPaaSParams.OCCI_INSPECT_VM_AVAILABILITY).getAsInt();
      paaSMetricNormalization.setocciInspectVmAvailability(vma);
    }
    if (obj.has(CustomPaaSParams.OCCI_INSPECTVM_RESPONSE_TIME)) {
      float vmrt = obj.get(CustomPaaSParams.OCCI_INSPECTVM_RESPONSE_TIME).getAsFloat();
      paaSMetricNormalization.setocciInspectVmResponseTime(vmrt);
    }
    if (obj.has(CustomPaaSParams.OCCI_INSPECTVM_RESULT)) {
      int vmr = obj.get(CustomPaaSParams.OCCI_INSPECTVM_RESULT).getAsInt();
      paaSMetricNormalization.setocciInspectVmResult(vmr);
    }

    paaSMetricNormalization.toCustomFile();
  }
}
