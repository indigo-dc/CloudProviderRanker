package org.indigo.cloudproviderranker;

import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import java.util.logging.Level;
import java.io.IOException;

public final class Main {

  private Main() {
  }

  public static void main(final String[] args) throws IOException {
    int tcpport = 8080;
    boolean usessl = false;
    String keystorepath = null;
    String password = null;

    if (args.length < 2) {
      System.err.println("Must specify at least the SLA priority file as "
                         + "first argument and monitoring normalization file as second argument");
      System.exit(1);
    }

    SlaNormalizations.priority_file = args[0];
    PaaSMetricNormalization.normalization_file = args[1];

    if (args.length > 2) {
      tcpport = Integer.parseInt(args[2]);
    }
    if (args.length >= 4 && args.length < 5) {
      System.err.println("If a keystore path is specified"
                         + " then must specify also a password. STOP!");
      System.exit(1);
    }
    if (args.length   ==   5) {
      usessl = true;
      keystorepath = args[3];
      password = args[4];
    }

    RestEngine re = new RestEngine();
    try {
      re.initHttpServer(usessl,  tcpport,  keystorepath,  password);
    } catch (ServerException se) {
      System.err.println(se.getMessage());
      System.exit(1);
    }
    re.addHandlerToContext("/rank",  new RankHandler());
    re.addHandlerToContext("/custom-paas-parameters",  new CustomPaaSParamHandler());
    re.addHandlerToContext("/get-paas-parameters",  new GetPaaSParamHandler());
    re.addHandlerToContext("/get-sla-parameters",  new GetSLAParamHandler());
    re.addHandlerToContext("/custom-sla-parameters",  new CustomSLAParamHandler());

    re.startServer();

    Logger rootLogger = Logger.getLogger("");
    FileHandler logHandler = new FileHandler("/var/log/CloudProviderRanker.log",
                                             10 * 1024 * 1024,
                                             10,
                                             false);

    logHandler.setFormatter(new SimpleFormatter());
    logHandler.setLevel(Level.FINEST);
    rootLogger.addHandler(logHandler);
    String plairOrSsl = usessl ? "SSL" : "plain";
    rootLogger.log(Level.INFO,  "Cloud Provider Ranker is listening on TCP port "
		   + tcpport
		   + " with "
		   + plairOrSsl
		   + " HTTP protocol \n");

  }
}
