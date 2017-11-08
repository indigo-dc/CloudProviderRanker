package org.indigo.cloudproviderranker;

// doc: https://goo.gl/AJ792o
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsServer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.concurrent.Executors;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;

/**
 * Doc TODO.
 */
public class RestEngine {
  /**
   * Doc TODO.
   */
  private HttpServer server = null;

  /**
   * Doc TODO.
   */
  public final void startServer() {
    server.setExecutor(Executors.newCachedThreadPool());
    server.start();
  }

  /**
   * Doc TODO.
   */
  public final void addHandlerToContext(final String contextPath,  final HttpHandler handler) {
    server.createContext(contextPath,  handler);
  }

  /**
   * Doc TODO.
   */
  public final void initHttpServer(final boolean usessl,
                                   final int tcpport,
                                   final String keystorepath,
                                   final String password) throws ServerException {
    if (!usessl) {
      try {
        server = HttpServer.create(new InetSocketAddress(tcpport),  0);
      } catch (IOException e) {
        throw new ServerException("Error in HttpServer.create: " + e.getMessage(), e);
      }

    } else {

      // Use: keytool -genkey -alias webservice -keystore server_keystore.ks
      // to create the keystore.

      try {
        server = HttpsServer.create(new InetSocketAddress(tcpport),  0);
      } catch (IOException e) {
        throw new ServerException("Error in HttpServer.create: " + e.getMessage(), e);
      }
      SSLContext sslContext = null;
      try {
        sslContext = SSLContext.getInstance("TLS");
      } catch (NoSuchAlgorithmException e) {
        throw new ServerException("Error in SSLContext.getInstance(\"TLS\"): "
                                  + e.getMessage(), e);
      }

      char[] keystorePassword = password.toCharArray();
      KeyStore ks = null;
      try {
        ks = KeyStore.getInstance("JKS");
      } catch (KeyStoreException e) {
        throw new ServerException("Error in KeyStore.getInstance(\"JKS\"): "
                                  + e.getMessage(), e);
      }
      try {
        ks.load(new FileInputStream(keystorepath),  keystorePassword);
      } catch (FileNotFoundException e) {
        throw new ServerException("Error in KeyStore.load: " + e.getMessage(), e);
      } catch (IOException e) {
        throw new ServerException("Error in KeyStore.load: " + e.getMessage(), e);
      } catch (NoSuchAlgorithmException e) {
        throw new ServerException("Error in KeyStore.load: " + e.getMessage(), e);
      } catch (CertificateException e) {
        throw new ServerException("Error in KeyStore.load: " + e.getMessage(), e);
      }

      KeyManagerFactory kmf = null;
      try {
        kmf = KeyManagerFactory.getInstance("SunX509");
      } catch (NoSuchAlgorithmException e) {
        throw new ServerException("Error in KeyManagerFactory.getInstance(\"SunX509\"): "
                                  + e.getMessage(), e);
      }
      try {
        kmf.init(ks,  keystorePassword);
      } catch (KeyStoreException e) {
        throw new ServerException("Error in KeyManagerFactory.init: " + e.getMessage(), e);
      } catch (NoSuchAlgorithmException e) {
        throw new ServerException("Error in KeyManagerFactory.init: " + e.getMessage(), e);
      } catch (UnrecoverableKeyException e) {
        throw new ServerException("Error in KeyManagerFactory.init: " + e.getMessage(), e);
      }

      try {
        sslContext.init(kmf.getKeyManagers(),  null,  null);
      } catch (KeyManagementException e) {
        throw new ServerException("Error in SSLContext.init: " + e.getMessage(), e);
      }
      HttpsConfigurator httpsConfigurator = new HttpsConfigurator(sslContext);
      ((HttpsServer) server).setHttpsConfigurator(httpsConfigurator);
    }
  }
}
