package org.indigo.cloudproviderranker.test;

import org.junit.Test;
import static org.junit.Assert.*;
import org.indigo.cloudproviderranker.RankHandler;
import org.indigo.cloudproviderranker.ParseResult;

public class ParseResultTest {
  @Test
  public void test( ) {
    ParseResult p = new ParseResult("message", 100);
    assertTrue(p.getMessage().compareTo("message")==0);
    assertTrue(p.getHTTPCode()==100);
  }
}
