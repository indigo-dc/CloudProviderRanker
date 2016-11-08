package org.indigo.cloudproviderranker;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.Headers;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.io.*;
import java.util.Date;
import com.google.gson.*;
import java.text.SimpleDateFormat;

public class CustomParamHandler extends RequestHandler {

  //_________________________________________________________________________________________
  @Override
  public void handle(HttpExchange httpExchange) throws IOException {
    if(httpExchange.getRequestMethod( ).compareToIgnoreCase("POST")!=0) {
      String response = "API \"customparam\" only supports POST method";
	httpExchange.sendResponseHeaders(405, response.getBytes().length);
	OutputStream os = httpExchange.getResponseBody();
	os.write(response.getBytes());
	os.close();
	return;
    }
	
    clientHostName = httpExchange.getRemoteAddress( ).getHostName( );
    String Line = "";
    try {
	    InputStream is = httpExchange.getRequestBody();
	    InputStreamReader inputReader = new InputStreamReader(is,"utf-8");
	    BufferedReader buffReader = new BufferedReader(inputReader);
	    String line = "";
	    while( (line = buffReader.readLine()) != null) {
       		Line += line;
            }
    } catch(IOException ioe) {

    }

    String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format( new java.util.Date() );

	Logger.getLogger("").log(Level.INFO, timeStamp + " [" + clientHostName + "] New request for /customparam API from this client... "); 
	PaaSMetricNormalization paaSMetricNormalization = new PaaSMetricNormalization( true );
	

	Gson gson = new Gson( );
	
        JsonObject obj = gson.fromJson( Line, JsonElement.class ).getAsJsonObject( );
	if(obj.has("OCCI_Create_VM_availability")) {
	  paaSMetricNormalization.setOCCI_Create_VM_availability( obj.get( "OCCI_Create_VM_availability" ).getAsInt( ) );
	}
	if(obj.has("OCCI_CreateVM_Response_Time")) {
	  paaSMetricNormalization.setOCCI_CreateVM_Response_Time( obj.get( "OCCI_CreateVM_Response_Time" ).getAsFloat( ) );
	}
	if(obj.has("OCCI_CreateVM_Result")) {
	  paaSMetricNormalization.setOCCI_CreateVM_Result( obj.get( "OCCI_CreateVM_Result" ).getAsInt( ) );
	}
	if(obj.has("OCCI_Delete_VM_Availability")) {
	  paaSMetricNormalization.setOCCI_Delete_VM_Availability( obj.get( "OCCI_Delete_VM_Availability" ).getAsInt( ) );
	}
	if(obj.has("OCCI_DeleteVM_Response_Time")) {
	  paaSMetricNormalization.setOCCI_DeleteVM_Response_Time( obj.get( "OCCI_DeleteVM_Response_Time" ).getAsFloat( ) );
	}
	if(obj.has("OCCI_DeleteVM_Result")) {
	  paaSMetricNormalization.setOCCI_DeleteVM_Result( obj.get( "OCCI_DeleteVM_Result" ).getAsInt( ) );
	}
	if(obj.has("General_OCCI_API_Availability")) {
	  paaSMetricNormalization.setGeneral_OCCI_API_Availability( obj.get( "General_OCCI_API_Availability" ).getAsInt( ) );
	}
	if(obj.has("General_OCCI_API_Response_Time")) {
	  paaSMetricNormalization.setGeneral_OCCI_API_Response_Time( obj.get( "General_OCCI_API_Response_Time" ).getAsFloat( ) );
	}
	if(obj.has("General_OCCI_API_Result")) {
	  paaSMetricNormalization.setGeneral_OCCI_API_Result( obj.get( "General_OCCI_API_Result" ).getAsInt( ) );
	}
	if(obj.has("OCCI_Inspect_VM_availability")) {
	  paaSMetricNormalization.setOCCI_Inspect_VM_availability( obj.get( "OCCI_Inspect_VM_availability" ).getAsInt( ) );
	}
	if(obj.has("OCCI_InspectVM_Response_Time")) {
	  paaSMetricNormalization.setOCCI_InspectVM_Response_Time( obj.get( "OCCI_InspectVM_Response_Time" ).getAsFloat( ) );
	}
	if(obj.has("OCCI_InspectVM_Result")) {
	  paaSMetricNormalization.setOCCI_InspectVM_Result( obj.get( "OCCI_InspectVM_Result" ).getAsInt( ) );
	}

	paaSMetricNormalization.toCustomFile( );

//	timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format( new java.util.Date() );
	 
	Headers responseHeaders = httpExchange.getResponseHeaders();
        responseHeaders.set("Content-Type", "application/json");
        httpExchange.sendResponseHeaders( 200, 0);
//        timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format( new java.util.Date() );
//        Logger.getLogger("").log(Level.INFO, timeStamp + " [" + clientHostName + "] Returning normalization parameters to the client: " + params + "\n\n"); 
	
        OutputStream os = httpExchange.getResponseBody();
        os.write( "".getBytes() );
        os.close();
	
	
    }
}
