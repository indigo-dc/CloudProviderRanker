package org.indigo.cloudproviderranker.dto;

import org.junit.Test;

import static org.junit.Assert.*;

public class RankResultTest {

  @Test
  public void test() {

    RankResult rr1 = new RankResult();

    rr1.setProvider("provider");
    rr1.setServiceId("serviceId");
    rr1.setSlaWeight(0.5f);
    rr1.setTotalScore(1.0f);
    rr1.setRank(1);
    rr1.setRanked(true);

    assertTrue(rr1.getProvider().equals("provider"));
    assertTrue(rr1.getServiceId().equals("serviceId"));
    assertTrue(rr1.getSlaWeight().equals(0.5f));
    assertTrue(rr1.getTotalScore().equals(1.0f));
    assertTrue(rr1.getRank() == 1);
    assertTrue(rr1.isRanked() == true);

    rr1.toString();

    RankResult rr2 = new RankResult("provider", "serviceId", 0.5f, 1.0f, 1, true);

    assertTrue(rr2.getProvider().equals("provider"));
    assertTrue(rr2.getServiceId().equals("serviceId"));
    assertTrue(rr2.getSlaWeight().equals(0.5f));
    assertTrue(rr2.getTotalScore().equals(1.0f));
    assertTrue(rr2.getRank() == 1);
    assertTrue(rr2.isRanked() == true);        
  }
}
