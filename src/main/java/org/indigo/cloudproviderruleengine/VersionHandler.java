package org.indigo.cloudproviderruleengine;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

class versionHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {
	String response = "v0.1\n";
	t.sendResponseHeaders(200, response.getBytes().length);
	OutputStream os = t.getResponseBody();
	os.write(response.getBytes());
	os.close();
    }
}