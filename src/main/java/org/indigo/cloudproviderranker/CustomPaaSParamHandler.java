package org.indigo.cloudproviderranker;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.Headers;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.util.Date;
import com.google.gson.JsonArray;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.text.SimpleDateFormat;

public class CustomPaaSParamHandler extends RequestHandler {

  //_________________________________________________________________________________________
  @Override
  public void handle(HttpExchange httpExchange) throws IOException {
    if(httpExchange.getRequestMethod().compareToIgnoreCase("POST")!=0) {
      String response = "API \"custom-monitoring-parameters\" only supports POST method";
	httpExchange.sendResponseHeaders(405, response.getBytes().length);
	OutputStream os = httpExchange.getResponseBody();
	os.write(response.getBytes());
	os.close();
	return;
    }
	
    clientHostName = httpExchange.getRemoteAddress().getHostName();

//    InputStream is = httpExchange.getRequestBody();
    updateParams(httpExchange.getRequestBody());

	 
    Headers responseHeaders = httpExchange.getResponseHeaders();
    responseHeaders.set("Content-Type", "application/json");
    httpExchange.sendResponseHeaders(200, 0);
	
    OutputStream os = httpExchange.getResponseBody();
    os.write("".getBytes());
    os.close();
    
    
  }
    
  public void updateParams(InputStream is /*String line*/) {
    String Line = "";
    try {
      //InputStream is = httpExchange.getRequestBody();
      InputStreamReader inputReader = new InputStreamReader(is,"utf-8");
      BufferedReader buffReader = new BufferedReader(inputReader);
      String line = "";
      while((line = buffReader.readLine()) != null) {
	Line += line;
      }
    } catch(IOException ioe) {
	
    }
    
    String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
    
    Logger.getLogger("").log(Level.INFO, timeStamp +
			     " [" + clientHostName +
			     "] New request for /custom-monitoring-parameters" +
			     " API from this client... "); 
    PaaSMetricNormalization paaSMetricNormalization = new PaaSMetricNormalization(true);
    
    Gson gson = new Gson();
    
    JsonObject obj = gson.fromJson(Line, JsonElement.class).getAsJsonObject( );
    if(obj.has("OCCI_Create_VM_availability")) {
      int vma = obj.get("OCCI_Create_VM_availability").getAsInt();
      paaSMetricNormalization.setOCCI_Create_VM_availability(vma);
    }
    if(obj.has("OCCI_CreateVM_Response_Time")) {
      float vmrt = obj.get("OCCI_CreateVM_Response_Time").getAsFloat();
      paaSMetricNormalization.setOCCI_CreateVM_Response_Time(vmrt);
    }
    if(obj.has("OCCI_CreateVM_Result")) {
      int vmr = obj.get("OCCI_CreateVM_Result").getAsInt();
      paaSMetricNormalization.setOCCI_CreateVM_Result(vmr);
    }
    if(obj.has("OCCI_Delete_VM_Availability")) {
      int vma = obj.get("OCCI_Delete_VM_Availability").getAsInt();
      paaSMetricNormalization.setOCCI_Delete_VM_Availability(vma);
    }
    if(obj.has("OCCI_DeleteVM_Response_Time")) {
      float vmrt = obj.get("OCCI_DeleteVM_Response_Time").getAsFloat();
      paaSMetricNormalization.setOCCI_DeleteVM_Response_Time(vmrt);
    }
    if(obj.has("OCCI_DeleteVM_Result")) {
      int vmr = obj.get("OCCI_DeleteVM_Result").getAsInt();
      paaSMetricNormalization.setOCCI_DeleteVM_Result(vmr);
    }
    if(obj.has("General_OCCI_API_Availability")) {
      int vma = obj.get("General_OCCI_API_Availability").getAsInt();
      paaSMetricNormalization.setGeneral_OCCI_API_Availability(vma);
    }
    if(obj.has("General_OCCI_API_Response_Time")) {
      float vmrt = obj.get("General_OCCI_API_Response_Time").getAsFloat() ;
      paaSMetricNormalization.setGeneral_OCCI_API_Response_Time(vmrt);
    }
    if(obj.has("General_OCCI_API_Result")) {
      int vmr = obj.get("General_OCCI_API_Result").getAsInt();
      paaSMetricNormalization.setGeneral_OCCI_API_Result(vmr);
    }
    if(obj.has("OCCI_Inspect_VM_availability")) {
      int vma = obj.get("OCCI_Inspect_VM_availability").getAsInt();
      paaSMetricNormalization.setOCCI_Inspect_VM_availability(vma);
    }
    if(obj.has("OCCI_InspectVM_Response_Time")) {
      float vmrt = obj.get("OCCI_InspectVM_Response_Time").getAsFloat();
      paaSMetricNormalization.setOCCI_InspectVM_Response_Time(vmrt);
    }
    if(obj.has("OCCI_InspectVM_Result")) {
      int vmr = obj.get("OCCI_InspectVM_Result").getAsInt();
      paaSMetricNormalization.setOCCI_InspectVM_Result(vmr);
    }
    
    paaSMetricNormalization.toCustomFile();
  }
}
