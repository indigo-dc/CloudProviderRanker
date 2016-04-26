package org.indigo.cloudproviderruleengine;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 *
 * Handler class to return the server's version number to the client
 *
 */
public class VersionHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {

	if(t.getRequestMethod( ).compareToIgnoreCase("GET")!=0) {
	    String response = "API \"version\" only supports GET method";
	    t.sendResponseHeaders(405, response.getBytes().length);
	    OutputStream os = t.getResponseBody();
	    os.write(response.getBytes());
	    os.close();
	} else {
	    String response = "{\"version\": \"0.1\"}";
	    t.sendResponseHeaders(200, response.getBytes().length);
	    OutputStream os = t.getResponseBody();
	    os.write(response.getBytes());
	    os.close();
	}

    }
}
