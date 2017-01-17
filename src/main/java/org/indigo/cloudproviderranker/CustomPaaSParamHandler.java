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

  public final void updateParams(final InputStream is /*String line*/) {
    String Line = "";
    try {
      //InputStream is = httpExchange.getRequestBody();
      InputStreamReader inputReader = new InputStreamReader(is, "utf-8");
      BufferedReader buffReader = new BufferedReader(inputReader);
      String line = "";
      while ((line = buffReader.readLine()) != null) {
        Line += line;
      }
    } catch (IOException ioe) {

    }

    String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());

    Logger.getLogger("").log(Level.INFO,  timeStamp
                             + " [" + clientHostName
                             + "] New request for /custom-monitoring-parameters"
                             + " api from this client... ");
    PaaSMetricNormalization paaSMetricNormalization = new PaaSMetricNormalization(true);

    Gson gson = new Gson();

    JsonObject obj = gson.fromJson(Line,  JsonElement.class).getAsJsonObject();
    if (obj.has("occi_create_vm_availability")) {
      int vma = obj.get("occi_create_vm_availability").getAsInt();
      paaSMetricNormalization.setocci_create_vm_availability(vma);
    }
    if (obj.has("occi_createvm_Response_Time")) {
      float vmrt = obj.get("occi_createvm_Response_Time").getAsFloat();
      paaSMetricNormalization.setocci_createvm_Response_Time(vmrt);
    }
    if (obj.has("occi_createvm_Result")) {
      int vmr = obj.get("occi_createvm_Result").getAsInt();
      paaSMetricNormalization.setocci_createvm_Result(vmr);
    }
    if (obj.has("occi_delete_vm_Availability")) {
      int vma = obj.get("occi_delete_vm_Availability").getAsInt();
      paaSMetricNormalization.setocci_delete_vm_Availability(vma);
    }
    if (obj.has("occi_deletevm_Response_Time")) {
      float vmrt = obj.get("occi_deletevm_Response_Time").getAsFloat();
      paaSMetricNormalization.setocci_deletevm_Response_Time(vmrt);
    }
    if (obj.has("occi_deletevm_Result")) {
      int vmr = obj.get("occi_deletevm_Result").getAsInt();
      paaSMetricNormalization.setocci_deletevm_Result(vmr);
    }
    if (obj.has("general_occi_api_Availability")) {
      int vma = obj.get("general_occi_api_Availability").getAsInt();
      paaSMetricNormalization.setgeneral_occi_api_Availability(vma);
    }
    if (obj.has("general_occi_api_Response_Time")) {
      float vmrt = obj.get("general_occi_api_Response_Time").getAsFloat();
      paaSMetricNormalization.setgeneral_occi_api_Response_Time(vmrt);
    }
    if (obj.has("general_occi_api_Result")) {
      int vmr = obj.get("general_occi_api_Result").getAsInt();
      paaSMetricNormalization.setgeneral_occi_api_Result(vmr);
    }
    if (obj.has("occi_inspect_vm_availability")) {
      int vma = obj.get("occi_inspect_vm_availability").getAsInt();
      paaSMetricNormalization.setocci_inspect_vm_availability(vma);
    }
    if (obj.has("occi_inspectvm_Response_Time")) {
      float vmrt = obj.get("occi_inspectvm_Response_Time").getAsFloat();
      paaSMetricNormalization.setocci_inspectvm_Response_Time(vmrt);
    }
    if (obj.has("occi_inspectvm_Result")) {
      int vmr = obj.get("occi_inspectvm_Result").getAsInt();
      paaSMetricNormalization.setocci_inspectvm_Result(vmr);
    }

    paaSMetricNormalization.toCustomFile();
  }
}
