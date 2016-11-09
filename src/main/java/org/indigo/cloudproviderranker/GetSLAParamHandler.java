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

public class GetSLAParamHandler extends RequestHandler {

  //_________________________________________________________________________________________
  @Override
  public void handle(HttpExchange httpExchange) throws IOException {
    if(httpExchange.getRequestMethod( ).compareToIgnoreCase("GET")!=0) {
      String response = "API \"get-sla-parameters\" only supports GET method";
      httpExchange.sendResponseHeaders(405, response.getBytes().length);
      OutputStream os = httpExchange.getResponseBody();
      os.write(response.getBytes());
      os.close();
      return;
    }

    clientHostName = httpExchange.getRemoteAddress( ).getHostName( );
    String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format( new java.util.Date( ) );

    Logger.getLogger("").log(Level.INFO, timeStamp + " [" + clientHostName + "] New request for /get-sla-parameters API from this client... "); 
    SlaNormalizations slaNormalizations = new SlaNormalizations( );
    slaNormalizations.fromDefaultFile( );
    slaNormalizations.fromCustomFile( );
    //try {sla = new Sla( );}
    //catch(Exception e) { Logger.getLogger("").log(Level.SEVERE, timeStamp + " - Sla object initialization failed: "+e);}
    String params = slaNormalizations.getParams();
    ParseResult pr = new ParseResult(params, 200);

    Headers responseHeaders = httpExchange.getResponseHeaders();
    responseHeaders.set("Content-Type", "application/json");
    httpExchange.sendResponseHeaders( pr.getHTTPCode(), pr.getMessage().getBytes().length);
    timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format( new java.util.Date() );
    Logger.getLogger("").log(Level.INFO, timeStamp + " [" + clientHostName + "] Returning SLA priority parameters to the client: " + params + "\n\n"); 
	
    OutputStream os = httpExchange.getResponseBody();
    os.write( pr.getMessage().getBytes() );
    os.close();
  }
}
