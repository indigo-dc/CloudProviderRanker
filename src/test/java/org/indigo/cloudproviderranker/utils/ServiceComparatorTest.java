package org.indigo.cloudproviderranker.utils;

import static org.junit.Assert.assertTrue;

import org.indigo.cloudproviderranker.dto.RankedService;
import org.indigo.cloudproviderranker.utils.ServiceComparator;

import org.junit.Test;


public class ServiceComparatorTest {

  @Test
  public void test() {

    RankedService rs1 = new RankedService();
    rs1.setMetricsScore(1.0f);
    rs1.setSlaScore(1.0f);
    rs1.setSlaWeight(50f);
    rs1.setTotalScore(2.0f);

    RankedService rs2 = new RankedService();
    rs2.setMetricsScore(2.0f);
    rs2.setSlaScore(2.0f);
    rs2.setSlaWeight(50f);
    rs2.setTotalScore(4.0f);

    RankedService rs3 = new RankedService();
    rs3.setMetricsScore(1.0f);
    rs3.setSlaScore(1.0f);
    rs3.setSlaWeight(50f);
    rs3.setTotalScore(2.0f);

    RankedService rs4 = new RankedService();
    rs4.setMetricsScore(0.5f);
    rs4.setSlaScore(0.5f);
    rs4.setSlaWeight(100f);
    rs4.setTotalScore(1.0f);

    ServiceComparator sc = new ServiceComparator();

    assertTrue(sc.compare(rs1, rs2) == -1);
    assertTrue(sc.compare(rs2, rs1) == 1);
    assertTrue(sc.compare(rs1, rs3) == 0);
    assertTrue(sc.compare(rs1, rs4) == -1);
    assertTrue(sc.compare(rs4, rs1) == 1);

  }
}
