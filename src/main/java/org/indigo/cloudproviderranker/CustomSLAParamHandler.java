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

public class CustomSLAParamHandler extends RequestHandler {

  //_________________________________________________________________________________________
  @Override
  public void handle(HttpExchange httpExchange) throws IOException {
    if(httpExchange.getRequestMethod( ).compareToIgnoreCase("POST")!=0) {
      String response = "API \"custom-sla-parameters\" only supports POST method";
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
    updateParams( Line );
/*
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format( new java.util.Date() );

	Logger.getLogger("").log(Level.INFO, timeStamp + " [" + clientHostName + "] New request for /custom-sla-parameters API from this client... "); 
	SlaNormalizations slaNormalizations = new SlaNormalizations( );
	slaNormalizations.fromDefaultFile( );
	slaNormalizations.fromCustomFile( );

	Gson gson = new Gson( );
	
        JsonObject obj = gson.fromJson( Line, JsonElement.class ).getAsJsonObject( );
	if(obj.has("computing_time")) {
	  slaNormalizations.computing_time = obj.get( "computing_time" ).getAsFloat( );
	}
	if(obj.has("num_cpus")) {
	  slaNormalizations.num_cpus = obj.get( "num_cpus" ).getAsFloat( ) ;
	}
	if(obj.has("mem_size")) {
	  slaNormalizations.mem_size = obj.get( "mem_size" ).getAsFloat( );
	}
	if(obj.has("disk_size")) {
	  slaNormalizations.disk_size = obj.get( "disk_size" ).getAsFloat( );
	}
	if(obj.has("public_ip")) {
	  slaNormalizations.public_ip = obj.get( "public_ip" ).getAsFloat( );
	}
	if(obj.has("upload_bandwidth")) {
	  slaNormalizations.upload_bandwidth = obj.get( "upload_bandwidth" ).getAsFloat( );
	}
	if(obj.has("download_bandwidth")) {
	  slaNormalizations.download_bandwidth = obj.get( "download_bandwidth" ).getAsFloat( );
	}
	if(obj.has("upload_aggregated")) {
	  slaNormalizations.upload_aggregated = obj.get( "upload_aggregated" ).getAsFloat( );
	}
	if(obj.has("download_aggregated")) {
	  slaNormalizations.download_aggregated = obj.get( "download_aggregated" ).getAsFloat( );
	}
	if(obj.has("infinity_value")) {
	  slaNormalizations.infinity_value = obj.get( "infinity_value" ).getAsFloat( );
	}

	slaNormalizations.toCustomFile( );
*/
	Headers responseHeaders = httpExchange.getResponseHeaders();
        responseHeaders.set("Content-Type", "application/json");
        httpExchange.sendResponseHeaders( 200, 0);
	
        OutputStream os = httpExchange.getResponseBody();
        os.write( "".getBytes() );
        os.close();
    }

    public void updateParams( String line ) {

        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format( new java.util.Date() );

	Logger.getLogger("").log(Level.INFO, timeStamp + " [" + clientHostName + "] New request for /custom-sla-parameters API from this client... "); 
	SlaNormalizations slaNormalizations = new SlaNormalizations( );
	slaNormalizations.fromDefaultFile( );
	slaNormalizations.fromCustomFile( );

	Gson gson = new Gson( );
	
        JsonObject obj = gson.fromJson( line, JsonElement.class ).getAsJsonObject( );
	if(obj.has("computing_time")) {
	  slaNormalizations.computing_time = obj.get( "computing_time" ).getAsFloat( );
	}
	if(obj.has("num_cpus")) {
	  slaNormalizations.num_cpus = obj.get( "num_cpus" ).getAsFloat( ) ;
	}
	if(obj.has("mem_size")) {
	  slaNormalizations.mem_size = obj.get( "mem_size" ).getAsFloat( );
	}
	if(obj.has("disk_size")) {
	  slaNormalizations.disk_size = obj.get( "disk_size" ).getAsFloat( );
	}
	if(obj.has("public_ip")) {
	  slaNormalizations.public_ip = obj.get( "public_ip" ).getAsFloat( );
	}
	if(obj.has("upload_bandwidth")) {
	  slaNormalizations.upload_bandwidth = obj.get( "upload_bandwidth" ).getAsFloat( );
	}
	if(obj.has("download_bandwidth")) {
	  slaNormalizations.download_bandwidth = obj.get( "download_bandwidth" ).getAsFloat( );
	}
	if(obj.has("upload_aggregated")) {
	  slaNormalizations.upload_aggregated = obj.get( "upload_aggregated" ).getAsFloat( );
	}
	if(obj.has("download_aggregated")) {
	  slaNormalizations.download_aggregated = obj.get( "download_aggregated" ).getAsFloat( );
	}
	if(obj.has("infinity_value")) {
	  slaNormalizations.infinity_value = obj.get( "infinity_value" ).getAsFloat( );
	}

	slaNormalizations.toCustomFile( );
    }

}
