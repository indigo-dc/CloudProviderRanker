package org.indigo.cloudproviderruleengine.test;

import org.junit.Test;
import static org.junit.Assert.*;
import com.google.gson.*;
import org.indigo.cloudproviderruleengine.CloudProvider;

// Test build: javac -cp /usr/share/java/junit4-4.12.jar:../target/CloudProviderRanker-0.1.jar:/usr/local/share/java/gson/gson-2.6.2.jar CloudProviderTest.java

public class CloudProviderTest {

  private static final double DELTA = 1e-15;

  @Test
  public void testCloudProvider( ) {
    
    String jsonBlob = "{\"id\":1, \"totalVEPHDISK\":10, \"totalVCPU\":10, \"inUseVEPHDISK\": 1, \"name\":\"TestSite\", \"totalVRAM\":32, \"totalVDISK\":10,\"inUseVCPU\":7, \"inUseVRAM\":8, \"inUseVDISK\":1 }";
    Gson gson = new Gson();
    JsonElement E = gson.fromJson(jsonBlob, JsonElement.class);
    JsonObject obj = E.getAsJsonObject( );
    CloudProvider cp = gson.fromJson(obj, CloudProvider.class);
    System.out.println("rank=" + cp.getTotalRank());
    assertEquals(1, cp.getID());
    assertEquals("TestSite", cp.getName());
    assertEquals(10, cp.getTotalVCPU());
    assertEquals(32, cp.getTotalVRAM());
    assertEquals(10, cp.getTotalVEPHDISK());
    assertEquals(10, cp.getTotalVDISK());
    assertEquals(7, cp.getInUseVCPU());
    assertEquals(8, cp.getInUseVRAM());
    assertEquals(1, cp.getInUseVEPHDISK());
    assertEquals(1, cp.getInUseVDISK());
    assertEquals((double)0.0, (double)cp.getTotalRank(), DELTA);
    cp.setGoodParsed( );
    cp.setErrorMessage( "No error" ) ;
    assertEquals(true, cp.isGoodParsed());
    assertEquals("No error", cp.getParseError());
    cp.setWrongParsed( );
    cp.setErrorMessage( "Test error message" ) ;
    assertEquals(false, cp.isGoodParsed());
    assertEquals("Test error message", cp.getParseError());
    
    cp.addToTotalRank( (float)2.5 );
    assertEquals((float)2.5, cp.getTotalRank( ), DELTA );
    cp.zeroRank( );
    assertEquals((float)0.0, cp.getTotalRank( ), DELTA );
  } 
}
