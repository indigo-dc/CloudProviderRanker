package org.indigo.cloudproviderranker.dto;

import org.indigo.cloudproviderranker.utils.Utils;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RankedServiceTest {

	@Test
	public void test( ) {
		
		List<Target> targets = new ArrayList<Target>();
		Target t = new Target( );
		t.setUnit("u");
		targets.add(t);
		
		List<Metric> metrics = new ArrayList<Metric>();
		Metric m = new Metric();		
		m.setMetricName("metric_name");
		m.setMetricValue(0.5f);		
		metrics.add(m);
		
		RankedService rs1 = new RankedService();
		rs1.setProvider("provider");
		rs1.setServiceId("serviceId");
		rs1.setServiceParentId("serviceParentId");
		rs1.setServiceType("serviceType");
		rs1.setTargets(targets);
		rs1.setMetrics(Utils.metricsToMap(metrics));
	    rs1.setMetricsScore(1.0f);
	    rs1.setSlaScore(2.0f);
	    rs1.setSlaWeight(50f);
	    rs1.setTotalScore(3.0f);
	    rs1.setRank(10);
	    rs1.setRanked(true);
		
        assertTrue(rs1.getProvider().compareTo("provider")==0 ); 
		assertTrue( rs1.getServiceId().compareTo("serviceId")==0 );
		assertTrue( rs1.getServiceParentId().compareTo("serviceParentId")==0 );
		assertTrue( rs1.getServiceType().compareTo("serviceType")==0 );
		assertTrue( rs1.getTargets() != null); 
		assertTrue( rs1.getTargets().get(0).equals(t));		
		assertTrue( rs1.getMetrics()!= null); 
		assertTrue( rs1.getMetrics().containsKey("metric_name")); 
		assertTrue( rs1.getMetrics().containsValue(0.5f)); 
		assertTrue( rs1.getMetrics().get("metric_name").equals(0.5f));
		assertTrue( rs1.getMetricsScore().equals(1.0f));
		assertTrue( rs1.getSlaScore().equals(2.0f));
		assertTrue( rs1.getSlaWeight().equals(50f));
		assertTrue( rs1.getTotalScore().equals(3f));
		assertTrue( rs1.getRank() == 10);
		assertTrue( rs1.isRanked() == true);
		
		rs1.toString();
		
		Map<String, Float> metricsmap = new HashMap<String, Float>();
		metricsmap.put("metric_name", 0.5f);
		
		RankedService rs2 = new RankedService(	"provider",
												"serviceId",
												"serviceParentId",
												"serviceType",
												targets,
												metricsmap,
												1.0f,
												2.0f,
												50f,
												3.0f,
												10,
												true);
						
		assertTrue( rs2.getProvider().compareTo("provider")==0 ); 
		assertTrue( rs2.getServiceId().compareTo("serviceId")==0 );
		assertTrue( rs2.getServiceParentId().compareTo("serviceParentId")==0 );
		assertTrue( rs2.getServiceType().compareTo("serviceType")==0 );
		assertTrue( rs2.getTargets() != null); 
		assertTrue( rs2.getTargets().get(0).equals(t));		
		assertTrue( rs2.getMetrics()!= null); 
		assertTrue( rs2.getMetrics().containsKey("metric_name")); 
		assertTrue( rs2.getMetrics().containsValue(0.5f)); 
		assertTrue( rs2.getMetrics().get("metric_name").equals(0.5f));
		assertTrue( rs2.getMetricsScore().equals(1.0f));
		assertTrue( rs2.getSlaScore().equals(2.0f));
		assertTrue( rs2.getSlaWeight().equals(50f));
		assertTrue( rs2.getTotalScore().equals(3f));
		assertTrue( rs2.getRank() == 10);
		assertTrue( rs2.isRanked() == true);
		
		rs1.computeTotalScore(rs2);
		assertTrue( rs1.getTotalScore().equals(6f));
		
		RankedService rs3 = RankedService.builder().provider("provider")
							.serviceId("serviceId")
							.serviceType("serviceType")
							.serviceParentId("serviceParentId")
							.targets(targets)
							.metricsScore(1.0f)
							.metrics(Utils.metricsToMap(metrics))
							.slaScore(2.0f)
							.slaWeight(50f)
						    .totalScore(3.0f)
						    .rank(10)
						    .ranked(true).build();

		assertTrue( rs3.getProvider().compareTo("provider")==0 ); 
		assertTrue( rs3.getServiceId().compareTo("serviceId")==0 );
		assertTrue( rs3.getServiceParentId().compareTo("serviceParentId")==0 );
		assertTrue( rs3.getServiceType().compareTo("serviceType")==0 );
		assertTrue( rs3.getTargets() != null); 
		assertTrue( rs3.getTargets().get(0).equals(t));		
		assertTrue( rs3.getMetrics()!= null); 
		assertTrue( rs3.getMetrics().containsKey("metric_name")); 
		assertTrue( rs3.getMetrics().containsValue(0.5f)); 
		assertTrue( rs3.getMetrics().get("metric_name").equals(0.5f));
		assertTrue( rs3.getMetricsScore().equals(1.0f));
		assertTrue( rs3.getSlaScore().equals(2.0f));
		assertTrue( rs3.getSlaWeight().equals(50f));
		assertTrue( rs3.getTotalScore().equals(3f));
		assertTrue( rs3.getRank() == 10);
		assertTrue( rs3.isRanked() == true);
		
	}
}
