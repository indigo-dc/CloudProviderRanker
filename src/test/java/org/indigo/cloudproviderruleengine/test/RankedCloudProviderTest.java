package org.indigo.cloudproviderruleengine;

import org.junit.Test;
import static org.junit.Assert.*;
//import com.google.gson.*;

// Test build: javac -cp /usr/share/java/junit4-4.12.jar:../target/CloudProviderRanker-0.1.jar:/usr/local/share/java/gson/gson-2.6.2.jar CloudProviderTest.java

public class RankedCloudProviderTest {
  
  private static final double DELTA = 1e-15;
  
  @Test
  public void testRankedCloudProvider( ) {
    RankedCloudProvider rcp = new RankedCloudProvider( 1, "TestRanked", (float)2.5, true, "No error");
    assertEquals((float)2.5, rcp.getRank( ), DELTA );
    assertEquals("TestRanked", rcp.getName( ) );
    assertEquals(true, rcp.isRanked( ) );
    assertEquals("No error", rcp.getRankError( ) );
    assertEquals(1, rcp.getID() );
  }
}
