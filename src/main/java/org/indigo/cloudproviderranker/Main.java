package org.indigo.cloudproviderranker;

import com.google.devtools.common.options.OptionsParser;

import java.io.IOException;

import java.util.Collections;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public final class Main {

  private Main() {
  }

  /**
   * Doc TODO.
   */
  public static void main(final String[] argv) throws IOException {
    OptionsParser parser = OptionsParser.newOptionsParser(CliOptions.class);
    parser.parseAndExitUponError(argv);
    CliOptions opts = parser.getOptions(CliOptions.class);

    /* get residual parameters */
    String[] args = parser.getResidue().toArray(new String[0]);

    int tcpport = opts.port;
    boolean usessl = false;
    String keystorepath = null;
    String password = null;

    if (args.length < 2) {
      System.err.println("Must specify the SLA priority file as first argument"
                         + " and monitoring normalization file as second argument!");

      printUsage(parser);
      System.exit(1);
    }

    SlaNormalizations.priority_file = args[0];
    PaaSMetricNormalization.normalization_file = args[1];

    Sla.rules_file = opts.rulesFile;
    Sla.loadRules();

    if (!opts.keystorePath.isEmpty()) {
      if (opts.password.isEmpty()) {
        System.err.println("If a keystore path is specified then"
                           + " a password must also be specified!");

        printUsage(parser);
        System.exit(1);
      }

      usessl = true;
      keystorepath = opts.keystorePath;
      password = opts.password;
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
    re.addHandlerToContext("/get-sla-parameters",  new GetSlaParamHandler());
    re.addHandlerToContext("/custom-sla-parameters",  new CustomSlaParamHandler());

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

  private static void printUsage(OptionsParser parser) {
    System.out.println("Usage: java -jar <jar-file> [OPTIONS]"
                       + " <sla priority file> <monitoring normalization file>");
    System.out.println(parser.describeOptions(Collections.<String,
                                              String>emptyMap(),
                                              OptionsParser.HelpVerbosity.LONG));
  }
}
