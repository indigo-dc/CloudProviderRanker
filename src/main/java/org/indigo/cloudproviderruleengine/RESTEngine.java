package org.indigo.cloudproviderruleengine;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.concurrent.Executors;
import java.util.Map;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpsServer;

import java.net.InetSocketAddress;
import javax.net.ssl.SSLContext;
import com.sun.net.httpserver.HttpsConfigurator;
import java.security.KeyStore;
import javax.net.ssl.KeyManagerFactory;
import java.security.NoSuchAlgorithmException;
import java.security.KeyStoreException;

import java.security.cert.CertificateException;
import java.security.UnrecoverableKeyException;
import java.security.KeyManagementException;

/**
 * This is a sample class to launch a rule.
 */
public class RESTEngine {

    public static final void main(String[] args) {
    	
	Map<String, String> env = System.getenv();
	String home = env.get("HOME");

	int TCPPORT = 8080;
	boolean usessl = false;
	HttpServer server = null;
	String keystorepath = null;
	String password = null;

	if(args.length>0)
	    TCPPORT = Integer.parseInt( args[0] );
	if(args.length>=2 && args.length <3) {
	    System.err.println("If a keystore path is specified then must specify also a password. STOP!");
	    System.exit(1);
	}
	if(args.length == 3) {
	    usessl = true;
	    keystorepath = args[1];
	    password = args[2];
	}
	if(!usessl) {
	    
	    try {
		server = HttpServer.create(new InetSocketAddress(TCPPORT), 0);
	    } catch (IOException e) {
		System.err.println("Error in HttpServer.create: " + e.getMessage( ) );
		System.exit(1);
	    }
	} else {

	    // Use: keytool -genkey -alias webservice -keystore server_keystore.ks
	    // to create the keystore.

	    try {
		server = HttpsServer.create(new InetSocketAddress(TCPPORT), 0);
	    } catch (IOException e) {
		System.err.println("Error in HttpsServer.create: " + e.getMessage( ) );
		System.exit(1);
	    }
	    SSLContext sslContext = null;
	    try {
		sslContext = SSLContext.getInstance("TLS");
	    } catch( NoSuchAlgorithmException e ) {
		System.err.println("Error in SSLContext.getInstance(\"TLS\"): " + e.getMessage( ) );
		System.exit(1);
	    }
	    
	    char[] keystorePassword = password.toCharArray( );
	    KeyStore ks = null;
	    try {
		ks = KeyStore.getInstance("JKS");
	    } catch( KeyStoreException e ) {
		System.err.println("Error in KeyStore.getInstance(\"JKS\"): " + e.getMessage( ) );
		System.exit(1);
	    }
	    try {
		ks.load(new FileInputStream(keystorepath), keystorePassword);
	    } catch(FileNotFoundException e) {
		System.err.println("Error in KeyStore.load: " + e.getMessage( ) );
		System.exit(1);
	    } catch(IOException e) {
		System.err.println("Error in KeyStore.load: " + e.getMessage( ) );
		System.exit(1);
	    } catch(NoSuchAlgorithmException e){
		System.err.println("Error in KeyStore.load: " + e.getMessage( ) );
		System.exit(1);
	    } catch(CertificateException e){
		System.err.println("Error in KeyStore.load: " + e.getMessage( ) );
		System.exit(1);
	    }

	    KeyManagerFactory kmf = null;
	    try {
		kmf = KeyManagerFactory.getInstance("SunX509");
	    } catch(NoSuchAlgorithmException e) {
		System.err.println("Error in KeyManagerFactory.getInstance(\"SunX509\"): " + e.getMessage( ) );
		System.exit(1);
	    }
	    try {
		kmf.init(ks, keystorePassword);
	    } catch(KeyStoreException e) {
		System.err.println("Error in KeyManagerFactory.init: " + e.getMessage( ) );
		System.exit(1);
	    } catch(NoSuchAlgorithmException e) {
		System.err.println("Error in KeyManagerFactory.init: " + e.getMessage( ) );
		System.exit(1);
	    } catch(UnrecoverableKeyException e) {
		System.err.println("Error in KeyManagerFactory.init: " + e.getMessage( ) );
		System.exit(1);
	    }
	    
	    try {
		sslContext.init(kmf.getKeyManagers(), null, null);
	    } catch(KeyManagementException e) {
		System.err.println("Error in SSLContext.init: " + e.getMessage( ) );
		System.exit(1);
	    }

	    HttpsConfigurator configurator = new HttpsConfigurator(sslContext);
	    ((HttpsServer)server).setHttpsConfigurator(configurator);
	}

	server.createContext("/version", new VersionHandler());
	server.createContext("/rank", new RankHandler());
	server.createContext("/checkprovider", new CheckProviderHandler());
	server.setExecutor(Executors.newCachedThreadPool());
	server.start();
	System.out.println("HTTP" + (usessl ? "S" : "") + " Server is listening on port "+TCPPORT+"\n");
    }
}
