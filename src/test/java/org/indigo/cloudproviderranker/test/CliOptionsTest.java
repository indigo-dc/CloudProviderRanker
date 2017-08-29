package org.indigo.cloudproviderranker.test;

import org.junit.Test;
import static org.junit.Assert.*;

import org.indigo.cloudproviderranker.CliOptions;

import com.google.devtools.common.options.OptionsParser;
import com.google.devtools.common.options.OptionsParsingException;

import java.util.Collections;

public class CliOptionsTest {

  @Test
  public void parserWithUnknownOption() {
    OptionsParser parser = OptionsParser.newOptionsParser(CliOptions.class);
    try {
      parser.parse("--unknown", "option");
      fail();
    } catch (OptionsParsingException e) {
      assertEquals("--unknown", e.getInvalidArgument());
      assertEquals("Unrecognized option: --unknown", e.getMessage());
    }
    assertEquals(Collections.<String>emptyList(), parser.getResidue());
  }

  @Test
  public void portOptionWithValue() throws Exception {
    OptionsParser parser = OptionsParser.newOptionsParser(CliOptions.class);
    parser.parse("-p", "1234");

    CliOptions opts = parser.getOptions(CliOptions.class);
    assertNotNull(opts);
    assertEquals(1234, opts.port);
  }

  @Test
  public void portOptionWithDefault() throws Exception {
    OptionsParser parser = OptionsParser.newOptionsParser(CliOptions.class);
    parser.parse();

    CliOptions opts = parser.getOptions(CliOptions.class);
    assertNotNull(opts);
    assertEquals(8080, opts.port);
  }

  @Test
  public void rulesOptionWithValue() throws Exception {
    OptionsParser parser = OptionsParser.newOptionsParser(CliOptions.class);
    parser.parse("-r", "rules.drl");

    CliOptions opts = parser.getOptions(CliOptions.class);
    assertNotNull(opts);
    assertEquals("rules.drl", opts.rulesFile);
  }

  @Test
  public void rulesOptionWithDefault() throws Exception {
    OptionsParser parser = OptionsParser.newOptionsParser(CliOptions.class);
    parser.parse();

    CliOptions opts = parser.getOptions(CliOptions.class);
    assertNotNull(opts);
    assertEquals("", opts.rulesFile);
  }

}
