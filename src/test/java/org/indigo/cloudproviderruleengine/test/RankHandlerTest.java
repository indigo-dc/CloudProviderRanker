package org.indigo.cloudproviderruleengine.test;

import org.junit.Test;
import static org.junit.Assert.*;
import org.indigo.cloudproviderruleengine.RankHandler;

public class RankHandlerTest {
  @Test
  public void testRankHandlerTest( ) {
    RankHandler r = new RankHandler();
    if(r!=null)
      assertNotEquals(null,r);
  }
}
