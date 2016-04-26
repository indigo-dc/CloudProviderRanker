package org.indigo.cloudproviderruleengine;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.concurrent.Executors;

// doc: https://goo.gl/AJ792o
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpsServer;
import com.sun.net.httpserver.HttpHandler;

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
 *
 * Entry point class (main loop) which setup an http listener and a thread pool 
 * to execute it; then starts the Http server.
 *
 */
public class RESTEngine {

    private HttpServer	server       = null;
    
    
    
    //---------------------------------------------------------------------------------------
    public void startServer( )
    {
      server.setExecutor(Executors.newCachedThreadPool());
      server.start();
    } 
    
    //---------------------------------------------------------------------------------------
    public void addHandlerToContext( String contextPath, HttpHandler handler )
    {
      server.createContext(contextPath, handler);
    }
    
    //---------------------------------------------------------------------------------------
    
    public void initHttpServer( boolean usessl,int TCPPORT,String keystorepath,String password )
    throws ServerException {
      if(!usessl) {
	    
	    try {
		server = HttpServer.create(new InetSocketAddress(TCPPORT), 0);
	    } catch (IOException e) {
	    	throw new ServerException("Error in HttpServer.create: " + e.getMessage( ) );
//		System.err.println("Error in HttpServer.create: " + e.getMessage( ) );
//		System.exit(1);
	    }
	} else {

	    // Use: keytool -genkey -alias webservice -keystore server_keystore.ks
	    // to create the keystore.

	    try {
		server = HttpsServer.create(new InetSocketAddress(TCPPORT), 0);
	    } catch (IOException e) {
	    	throw new ServerException("Error in HttpServer.create: " + e.getMessage( ) );
//		System.err.println("Error in HttpsServer.create: " + e.getMessage( ) );
//		System.exit(1);
	    }
	    SSLContext sslContext = null;
	    try {
		sslContext = SSLContext.getInstance("TLS");
	    } catch( NoSuchAlgorithmException e ) {
	        throw new ServerException("Error in SSLContext.getInstance(\"TLS\"): " + e.getMessage( ) );
//		System.err.println("Error in SSLContext.getInstance(\"TLS\"): " + e.getMessage( ) );
//		System.exit(1);
	    }
	    
	    char[] keystorePassword = password.toCharArray( );
	    KeyStore ks = null;
	    try {
		ks = KeyStore.getInstance("JKS");
	    } catch( KeyStoreException e ) {
	        throw new ServerException("Error in KeyStore.getInstance(\"JKS\"): " + e.getMessage( ) );
//		System.err.println("Error in KeyStore.getInstance(\"JKS\"): " + e.getMessage( ) );
//		System.exit(1);
	    }
	    try {
		ks.load(new FileInputStream(keystorepath), keystorePassword);
	    } catch(FileNotFoundException e) {
	        throw new ServerException("Error in KeyStore.load: " + e.getMessage( ) );
		//System.err.println("Error in KeyStore.load: " + e.getMessage( ) );
		//System.exit(1);
	    } catch(IOException e) {
	        throw new ServerException("Error in KeyStore.load: " + e.getMessage( ) );
		//System.err.println("Error in KeyStore.load: " + e.getMessage( ) );
		//System.exit(1);
	    } catch(NoSuchAlgorithmException e){
	        throw new ServerException("Error in KeyStore.load: " + e.getMessage( ) );
		//System.err.println("Error in KeyStore.load: " + e.getMessage( ) );
		//System.exit(1);
	    } catch(CertificateException e){
	        throw new ServerException("Error in KeyStore.load: " + e.getMessage( ) );
		//System.err.println("Error in KeyStore.load: " + e.getMessage( ) );
		//System.exit(1);
	    }

	    KeyManagerFactory kmf = null;
	    try {
		kmf = KeyManagerFactory.getInstance("SunX509");
	    } catch(NoSuchAlgorithmException e) {
	        throw new ServerException("Error in KeyManagerFactory.getInstance(\"SunX509\"): " + e.getMessage( ) );
		//System.err.println("Error in KeyManagerFactory.getInstance(\"SunX509\"): " + e.getMessage( ) );
		//System.exit(1);
	    }
	    try {
		kmf.init(ks, keystorePassword);
	    } catch(KeyStoreException e) {
	        throw new ServerException("Error in KeyManagerFactory.init: " + e.getMessage( ) );
		//System.err.println("Error in KeyManagerFactory.init: " + e.getMessage( ) );
		//System.exit(1);
	    } catch(NoSuchAlgorithmException e) {
	        throw new ServerException("Error in KeyManagerFactory.init: " + e.getMessage( ) );
		//System.err.println("Error in KeyManagerFactory.init: " + e.getMessage( ) );
		//System.exit(1);
	    } catch(UnrecoverableKeyException e) {
	        throw new ServerException("Error in KeyManagerFactory.init: " + e.getMessage( ) );
		//System.err.println("Error in KeyManagerFactory.init: " + e.getMessage( ) );
		//System.exit(1);
	    }
	    
	    try {
		sslContext.init(kmf.getKeyManagers(), null, null);
	    } catch(KeyManagementException e) {
	        throw new ServerException("Error in SSLContext.init: " + e.getMessage( ) );
		//System.err.println("Error in SSLContext.init: " + e.getMessage( ) );
		//System.exit(1);
	    }

	    ((HttpsServer)server).setHttpsConfigurator(new HttpsConfigurator(sslContext));
	}
    }    
}
