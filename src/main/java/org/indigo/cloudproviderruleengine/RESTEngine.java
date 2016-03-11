package org.indigo.cloudproviderruleengine;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

/**
 * This is a sample class to launch a rule.
 */
public class RESTEngine {

    public static final void main(String[] args) {
    	
	int TCPPORT = 8080;
	if(args.length>0)
	    TCPPORT = Integer.parseInt( args[0] );

    	HttpServer server = null;
    	try {
	    server = HttpServer.create(new InetSocketAddress(TCPPORT), 0);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    	if(server != null) {
	    server.createContext("/version", new versionHandler());
	    server.createContext("/rank", new RankHandler());
	    server.setExecutor(null); // creates a default executor
	    server.start();
	    System.out.println("HTTP Server is listening on port "+TCPPORT+"\n");
    	}
    }
    
    
}
