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
	    System.out.println("If a keystore path is specified then must specify also a password. STOP!");
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
		e.printStackTrace();
	    }
	} else {

	    // Use: keytool -genkey -alias webservice -keystore server_keystore.ks
	    // to create the keystore.

	    try {
		server = HttpsServer.create(new InetSocketAddress(TCPPORT), 0);
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	    SSLContext sslContext = null;
	    try {
		sslContext = SSLContext.getInstance("TLS");
	    } catch( NoSuchAlgorithmException e ) {
		e.printStackTrace();
	    }
	    
	    char[] keystorePassword = password.toCharArray( );
	    KeyStore ks = null;
	    try {
		ks = KeyStore.getInstance("JKS");
	    } catch( KeyStoreException e ) {
		e.printStackTrace();
	    }
	    try {
		ks.load(new FileInputStream(keystorepath), keystorePassword);
	    } catch(FileNotFoundException e) {
		e.printStackTrace();
	    } catch(IOException e) {
		e.printStackTrace();
	    } catch(NoSuchAlgorithmException e){
		e.printStackTrace();
	    } catch(CertificateException e){
		e.printStackTrace();
	    }

	    KeyManagerFactory kmf = null;
	    try {
		kmf = KeyManagerFactory.getInstance("SunX509");
	    } catch(NoSuchAlgorithmException e) {
		e.printStackTrace();
	    }
	    try {
		kmf.init(ks, keystorePassword);
	    } catch(KeyStoreException e) {
		e.printStackTrace();
	    } catch(NoSuchAlgorithmException e) {
		e.printStackTrace();
	    } catch(UnrecoverableKeyException e) {
		e.printStackTrace();
	    }
	    
	    try {
		sslContext.init(kmf.getKeyManagers(), null, null);
	    } catch(KeyManagementException e) {
		e.printStackTrace();
	    }

	    HttpsConfigurator configurator = new HttpsConfigurator(sslContext);
	    ((HttpsServer)server).setHttpsConfigurator(configurator);
	}

	server.createContext("/version", new VersionHandler());
	server.createContext("/rank", new RankHandler());
	server.setExecutor(Executors.newCachedThreadPool());
	server.start();
	System.out.println("HTTP" + (usessl ? "S" : "") + " Server is listening on port "+TCPPORT+"\n");
    }
}
