package org.indigo.cloudproviderranker;

import com.google.devtools.common.options.Option;
import com.google.devtools.common.options.OptionsBase;

/**
 * Command-line options definition for CPR.
 */
public final class CliOptions extends OptionsBase {

  @Option(
      name = "help",
      abbrev = 'h',
      help = "Prints usage info.",
      defaultValue = "true"
  ) public boolean help;

  @Option(
      name = "port",
      abbrev = 'p',
      help = "The server port.",
      category = "startup",
      defaultValue = "8080"
  ) public int port;

  @Option(
      name = "rules-file",
      abbrev = 'r',
      help = "Rules file.",
      category = "startup",
      defaultValue = ""
  ) public String rulesFile;

  @Option(
      name = "keystore-path",
      abbrev = 'k',
      help = "Keystore path.",
      category = "startup",
      defaultValue = ""
  ) public String keystorePath;

  @Option(
      name = "password",
      abbrev = 'P',
      help = "Keystore password.",
      category = "startup",
      defaultValue = ""
  ) public String password;
}
