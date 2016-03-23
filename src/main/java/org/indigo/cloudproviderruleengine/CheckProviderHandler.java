package org.indigo.cloudproviderruleengine;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
//import java.util.List;
//import java.util.Vector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.google.gson.*;

//import org.kie.api.KieServices;
//import org.kie.api.runtime.KieContainer;
//import org.kie.api.runtime.KieSession;

/**
 * 
 * Class to parse the request's POST payload and convert into CloudProvider Objects and rank them
 * 
 * @author dorigoa
 *
 */
class CheckProviderHandler implements HttpHandler {
    
    private String clientHostName = "";
    
    @Override
    public void handle(HttpExchange t) throws IOException {
	
	if(t.getRequestMethod( ).compareToIgnoreCase("POST")!=0) {
	    String response = "API \"checkprovider\" only supports POST method";
	    t.sendResponseHeaders(405, response.getBytes().length);
	    OutputStream os = t.getResponseBody();
	    os.write(response.getBytes());
	    os.close();
	    return;
	}
	
	clientHostName = t.getRemoteAddress( ).getHostName( );
	
	try {
	    InputStream is = t.getRequestBody();
	    InputStreamReader inputReader = new InputStreamReader(is,"utf-8");
	    BufferedReader buffReader = new BufferedReader(inputReader);
	    String line = buffReader.readLine();
	    Gson gson = new Gson();
	    JsonElement E = gson.fromJson(line, JsonElement.class);
	    JsonObject obj = E.getAsJsonObject( );
	    if(!obj.has(CloudProvider.ID)) {
		String response = "Missing \"" + CloudProvider.ID + "\" field";
		System.err.println();
		t.sendResponseHeaders(400, response.getBytes().length);
		OutputStream os = t.getResponseBody();
		os.write(response.getBytes());
		os.close();
		return;
	    }
	    if(!obj.has(CloudProvider.NAME)) {
		String response = "Missing \"" + CloudProvider.NAME + "\" field";
		System.err.println();
		t.sendResponseHeaders(400, response.getBytes().length);
		OutputStream os = t.getResponseBody();
		os.write(response.getBytes());
		os.close();
		return;
	    }
	    if(!obj.has(CloudProvider.TOTALVCPU)) {
		String response = "Missing \"" + CloudProvider.TOTALVCPU + "\" field";
		System.err.println();
		t.sendResponseHeaders(400, response.getBytes().length);
		OutputStream os = t.getResponseBody();
		os.write(response.getBytes());
		os.close();
		return;
	    }
	    if(!obj.has(CloudProvider.TOTALVRAM)) {
		String response = "Missing \"" + CloudProvider.TOTALVRAM + "\" field";
		System.err.println();
		t.sendResponseHeaders(400, response.getBytes().length);
		OutputStream os = t.getResponseBody();
		os.write(response.getBytes());
		os.close();
		return;
	    }
	    if(!obj.has(CloudProvider.TOTALVDISK)) {
		String response = "Missing \"" + CloudProvider.TOTALVDISK + "\" field";
		System.err.println();
		t.sendResponseHeaders(400, response.getBytes().length);
		OutputStream os = t.getResponseBody();
		os.write(response.getBytes());
		os.close();
		return;
	    }
	    if(!obj.has(CloudProvider.TOTALVEPHDISK)) {
		String response = "Missing \"" + CloudProvider.TOTALVEPHDISK + "\" field";
		System.err.println();
		t.sendResponseHeaders(400, response.getBytes().length);
		OutputStream os = t.getResponseBody();
		os.write(response.getBytes());
		os.close();
		return;
	    }
	    if(!obj.has(CloudProvider.INUSEVCPU)) {
		String response = "Missing \"" + CloudProvider.TOTALVEPHDISK + "\" field";
		System.err.println();
		t.sendResponseHeaders(400, response.getBytes().length);
		OutputStream os = t.getResponseBody();
		os.write(response.getBytes());
		os.close();
		return;
	    }
	    if(!obj.has(CloudProvider.INUSEVRAM)) {
		String response = "Missing \"" + CloudProvider.INUSEVRAM + "\" field";
		System.err.println();
		t.sendResponseHeaders(400, response.getBytes().length);
		OutputStream os = t.getResponseBody();
		os.write(response.getBytes());
		os.close();
		return;
	    }
	    if(!obj.has(CloudProvider.INUSEVDISK)) {
		String response = "Missing \"" + CloudProvider.INUSEVDISK + "\" field";
		System.err.println();
		t.sendResponseHeaders(400, response.getBytes().length);
		OutputStream os = t.getResponseBody();
		os.write(response.getBytes());
		os.close();
		return;
	    }
	    if(!obj.has(CloudProvider.INUSEVEPHDISK)) {
		String response = "Missing \"" + CloudProvider.INUSEVEPHDISK + "\" field";
		System.err.println();
		t.sendResponseHeaders(400, response.getBytes().length);
		OutputStream os = t.getResponseBody();
		os.write(response.getBytes());
		os.close();
		return;
	    }
	    String response = "JSON description for provider [" + obj.get(CloudProvider.NAME) + "] is OK.";
	    System.err.println("[" + clientHostName + "] "+ response + "\n\n");
	    t.sendResponseHeaders(200, response.getBytes().length);
	    OutputStream os = t.getResponseBody();
	    os.write(response.getBytes());
	    os.close();
	    return;
	} catch(Exception e) {
	    String err = "Exception parsing JSON client request: " + e.getMessage() + "\n";
	    t.sendResponseHeaders(400, err.getBytes().length);
	    OutputStream os = t.getResponseBody();
	    os.write(err.getBytes());
	    os.close();
	    e.printStackTrace();
	    return;
	} catch(Throwable e) {
	    String err = "Throwable parsing JSON client request: " + e.getMessage() + "\n";
	    t.sendResponseHeaders(400, err.getBytes().length);
	    OutputStream os = t.getResponseBody();
	    os.write(err.getBytes());
	    os.close();
	    e.printStackTrace();
	    return;
	}
    }
}
