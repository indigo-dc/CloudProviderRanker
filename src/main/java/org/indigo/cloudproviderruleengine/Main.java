 package org.indigo.cloudproviderruleengine;
 
 import java.util.Map;
 
 public class Main {

    public static final void main(String[] args) {
    	
	Map<String, String> env = System.getenv();
	String home = env.get("HOME");

	int TCPPORT = 8080;
	boolean usessl = false;
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
	
	RESTEngine re = new RESTEngine();
	try {
	  re.initHttpServer( usessl, TCPPORT, keystorepath, password );
	} catch(ServerException se) {
	  System.err.println(se.getMessage( ) );
	  System.exit(1);
	}
	re.addHandlerToContext("/version", new VersionHandler());
	re.addHandlerToContext("/rank", new RankHandler());
	re.addHandlerToContext("/checkprovider", new CheckProviderHandler());
	re.startServer( );
	System.out.println("HTTP" + (usessl ? "S" : "") + " Server is listening on port "+TCPPORT+"\n");
    }
}
