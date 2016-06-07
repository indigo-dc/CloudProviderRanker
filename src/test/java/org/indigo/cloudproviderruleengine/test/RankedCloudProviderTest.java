package org.indigo.cloudproviderruleengine;

import org.junit.Test;
import static org.junit.Assert.*;

public class RankedCloudProviderTest {
  
  private static final double DELTA = 1e-15;
  
  @Test
  public void test( ) {
    RankedCloudProvider rcp = new RankedCloudProvider( "TestRanked", 2.5f, true, "No error");
    if(rcp!=null) {
      assertEquals(2.5f, rcp.getRank( ), DELTA );
      assertEquals("TestRanked", rcp.getName( ) );
      assertEquals(true, rcp.isRanked( ) );
      assertEquals("No error", rcp.getRankError( ) );
      rcp.addToRank( 10.0f);
      assertEquals(12.5f, rcp.getRank( ), DELTA );
    }
  }
}
