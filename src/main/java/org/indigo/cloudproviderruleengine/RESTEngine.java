package org.indigo.cloudproviderruleengine;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpsServer;
import javax.net.ssl.SSLContext;
import com.sun.net.httpserver.HttpsConfigurator;
import java.security.KeyStore;
import java.io.FileInputStream;


/**
 * This is a sample class to launch a rule.
 */
public class RESTEngine {

    public static final void main(String[] args) {
    	
	int TCPPORT = 8080;
	boolean usessl = false;

	if(args.length>0)
	    TCPPORT = Integer.parseInt( args[0] );
	if(args.length>1)
	    usessl = true;
	if(!usessl) {
	    HttpServer server = null;
	    try {
		server = HttpServer.create(new InetSocketAddress(TCPPORT), 0);
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    if(server != null) {
		server.createContext("/version", new VersionHandler());
		server.createContext("/rank", new RankHandler());
		server.setExecutor(Executors.newCachedThreadPool());
		server.start();
		System.out.println("HTTP Server is listening on port "+TCPPORT+"\n");
	    }
	} else {
	    
	}
    }
}
