package org.indigo.cloudproviderranker;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.text.SimpleDateFormat;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

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
    if (obj.has("occi_create_vm_availability")) {
      int vma = obj.get("occi_create_vm_availability").getAsInt();
      paaSMetricNormalization.setocciCreatevmAvailability(vma);
    }
    if (obj.has("occi_createvm_Response_Time")) {
      float vmrt = obj.get("occi_createvm_Response_Time").getAsFloat();
      paaSMetricNormalization.setocciCreatevmResponseTime(vmrt);
    }
    if (obj.has("occi_createvm_Result")) {
      int vmr = obj.get("occi_createvm_Result").getAsInt();
      paaSMetricNormalization.setocciCreatevmResult(vmr);
    }
    if (obj.has("occi_delete_vm_Availability")) {
      int vma = obj.get("occi_delete_vm_Availability").getAsInt();
      paaSMetricNormalization.setocciDeletevmAvailability(vma);
    }
    if (obj.has("occi_deletevm_Response_Time")) {
      float vmrt = obj.get("occi_deletevm_Response_Time").getAsFloat();
      paaSMetricNormalization.setocciDeletevmResponseTime(vmrt);
    }
    if (obj.has("occi_deletevm_Result")) {
      int vmr = obj.get("occi_deletevm_Result").getAsInt();
      paaSMetricNormalization.setocciDeletevmResult(vmr);
    }
    if (obj.has("general_occi_api_Availability")) {
      int vma = obj.get("general_occi_api_Availability").getAsInt();
      paaSMetricNormalization.setgeneralOcciApiAvailability(vma);
    }
    if (obj.has("general_occi_api_Response_Time")) {
      float vmrt = obj.get("general_occi_api_Response_Time").getAsFloat();
      paaSMetricNormalization.setgeneralOcciApiResponseTime(vmrt);
    }
    if (obj.has("general_occi_api_Result")) {
      int vmr = obj.get("general_occi_api_Result").getAsInt();
      paaSMetricNormalization.setgeneralOcciApiResult(vmr);
    }
    if (obj.has("occi_inspect_vm_availability")) {
      int vma = obj.get("occi_inspect_vm_availability").getAsInt();
      paaSMetricNormalization.setocciInspectVmAvailability(vma);
    }
    if (obj.has("occi_inspectvm_Response_Time")) {
      float vmrt = obj.get("occi_inspectvm_Response_Time").getAsFloat();
      paaSMetricNormalization.setocciInspectVmResponseTime(vmrt);
    }
    if (obj.has("occi_inspectvm_Result")) {
      int vmr = obj.get("occi_inspectvm_Result").getAsInt();
      paaSMetricNormalization.setocciInspectVmResult(vmr);
    }

    paaSMetricNormalization.toCustomFile();
  }
}
