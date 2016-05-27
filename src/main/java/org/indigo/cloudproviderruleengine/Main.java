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

	if(args.length==0) {
	  System.err.println("Must specify at least the SLA priority file as first argument");
	  System.exit(1);
	}
	
//	String sla_priority_file = args[0];

	SlaNormalizations.priority_file = args[0];

	if(args.length>1)
	    TCPPORT = Integer.parseInt( args[1] );
	if(args.length>=3 && args.length <4) {
	    System.err.println("If a keystore path is specified then must specify also a password. STOP!");
	    System.exit(1);
	}
	if(args.length == 4) {
	    usessl = true;
	    keystorepath = args[2];
	    password = args[3];
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
