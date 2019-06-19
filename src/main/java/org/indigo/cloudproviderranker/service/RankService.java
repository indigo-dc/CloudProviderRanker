package org.indigo.cloudproviderranker.service;

import org.indigo.cloudproviderranker.config.properties.RuleEngineProperties;
import org.indigo.cloudproviderranker.dto.RankedService;
import org.indigo.cloudproviderranker.utils.ServiceComparator;
import org.indigo.cloudproviderranker.utils.exceptions.RulesBuildException;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.Message;
import org.kie.api.io.KieResources;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


@Service
public class RankService {
	
	private static final Logger logger = LoggerFactory.getLogger(RankService.class);
	
	private static KieContainer kcontainer;
	private List<String> drls;
	
	@Autowired
	public RankService(RuleEngineProperties ruleEngineProperties) {
		
		logger.debug("Importing Drools Rules");

		this.drls = ruleEngineProperties.getRules();
		
	    KieServices ks = KieServices.Factory.get();
	    KieFileSystem kfs = ks.newKieFileSystem();
	    KieResources kres = ks.getResources();
	    
	    drls.forEach(drl -> kfs.write("src/main/resources/" + drl.hashCode() + ".drl", kres.newFileSystemResource(drl)));
	    
	    KieBuilder kb = ks.newKieBuilder(kfs).buildAll();
	    
	      // Check the builder for errors
        if (kb.getResults().hasMessages(Message.Level.ERROR)) {
          throw new RulesBuildException(kb.getResults());
        }
        
        logger.info("Drools Rules successfully imported");
  
        KieRepository kr = ks.getRepository();
        kcontainer = ks.newKieContainer(kr.getDefaultReleaseId());
	    
	}
	
	public List<RankedService> run(HashMap<String, RankedService> services) {
		
		
	    KieSession ks = kcontainer.newKieSession();
	    ks.setGlobal("logger", logger);
	    	    
	    for (RankedService service : services.values() )
	    	ks.insert(service);
	    
	    ks.fireAllRules();
	    ks.dispose();
	    
	    int numServicesNotRanked = 0;
	    
	    for (RankedService service : services.values()) {
	    	
	    	try {
	    	
		    	Float slaScore    = service.getSlaScore()     == null ? 0 : service.getSlaScore();
		    	Float metricScore = service.getMetricsScore() == null ? 0 : service.getMetricsScore();
		    	
		    	Float totalScore = slaScore + metricScore;
		    	
		    	RankedService parentService = services.get(service.getServiceParentId() );
		    	
		    	if( parentService != null) {
		    		totalScore += ( parentService.getSlaScore() + parentService.getMetricsScore());
		    	}
		    	
		    	service.setTotalScore(totalScore);
		    	service.setRanked(totalScore != 0);
		    	
	    	} catch (Exception e) {
	    		logger.warn("Error computing score for service " + service.getServiceId(), e);
	    		service.setRanked(false);
	    	}
	    	
	    	if (!service.isRanked())
	    		numServicesNotRanked++;
	    	
	    }
	    
	    List<RankedService> results = new ArrayList<RankedService>(services.values());
	    
	    /** Sort the list of services according to the SLA weight (preference); 
	     *  if the weight is equal, then consider the score computed above 
	     */
	    Collections.sort(results, new ServiceComparator());
	    
	    // Associate the rank to each element in the list
	    int n = results.size();
	    int rank=(n-numServicesNotRanked);
	    
	    for (int i = 0; i < n; i++) {
	    	if(results.get(i).isRanked()) {
	    		results.get(i).setRank(rank);
	    	    rank--;
	    	}
	    	else results.get(i).setRank(-1);
	    }
	    
	    logger.debug("Ranking results: " + results);
		
		return results;
		
	}
}
