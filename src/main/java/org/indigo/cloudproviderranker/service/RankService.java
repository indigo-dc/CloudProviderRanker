package org.indigo.cloudproviderranker.service;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.indigo.cloudproviderranker.config.properties.RuleEngineProperties;
import org.indigo.cloudproviderranker.dto.RankResult;
import org.indigo.cloudproviderranker.dto.RankedService;
import org.indigo.cloudproviderranker.utils.ServiceComparator;
import org.indigo.cloudproviderranker.utils.Utils;
import org.indigo.cloudproviderranker.utils.exceptions.RulesBuildException;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.Message;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;


@Service
public class RankService {

  private static final Logger logger = LoggerFactory.getLogger(RankService.class);

  private static KieContainer kcontainer;
  private List<String> drls;

  /** RankedService constructor.
   * 
   * @param ruleEngineProperties properties containing the Rule Engine conf.
   */
  @Autowired
  public RankService(RuleEngineProperties ruleEngineProperties) {

    logger.debug("Importing Drools Rules");

    this.drls = ruleEngineProperties.getRules();

    KieServices ks = KieServices.Factory.get();
    KieFileSystem kfs = ks.newKieFileSystem();
    //KieResources kres = ks.getResources();

    //drls.forEach(drl -> kfs.write("src/main/resources/" + drl.hashCode() + ".drl", 
    //             kres.newFileSystemResource(drl)));
    drls.forEach(drl -> {
      try {
        if (drl.startsWith("classpath:")) {
          ClassPathResource cpr = new ClassPathResource(drl.substring("classpath:".length()));
          kfs.write("src/main/resources/" + drl.hashCode() + ".drl", 
                     new String(FileCopyUtils.copyToByteArray(cpr.getInputStream())));
        } else {
          kfs.write("src/main/resources/" + drl.hashCode() + ".drl",
                     new String(Files.readAllBytes(ResourceUtils.getFile(drl).toPath())));
        }
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    });

    KieBuilder kb = ks.newKieBuilder(kfs).buildAll();

    // Check the builder for errors
    if (kb.getResults().hasMessages(Message.Level.ERROR)) {
      throw new RulesBuildException(kb.getResults());
    }

    logger.info("Drools Rules successfully imported");

    KieRepository kr = ks.getRepository();
    kcontainer = ks.newKieContainer(kr.getDefaultReleaseId());

  }

  /** run method.
   * 
   * @param services hashmap of services that will be ranked
   * @return
   */
  public List<RankResult> run(HashMap<String, RankedService> services) {

    KieSession ks = kcontainer.newKieSession();
    ks.setGlobal("logger", logger);

    for (RankedService service : services.values()) {
      ks.insert(service);
    }

    ks.fireAllRules();
    ks.dispose();

    int numServicesNotRanked = 0;

    for (RankedService service : services.values()) {

      try {

        Float slaScore    = service.getSlaScore()     == null ? 0 : service.getSlaScore();
        Float metricScore = service.getMetricsScore() == null ? 0 : service.getMetricsScore();

        Float totalScore = slaScore + metricScore;

        RankedService parentService = services.get(service.getServiceParentId());

        if (parentService != null) {
          Float parentSlaScore = 
              parentService.getSlaScore() == null ? 0 : parentService.getSlaScore();
          Float parentMetricsScore = 
              parentService.getMetricsScore() == null ? 0 : parentService.getMetricsScore();
          totalScore += (parentSlaScore + parentMetricsScore);
        }

        service.setTotalScore(totalScore);
        // Set ranked = false if the total score is 0 or the SLA is not provided
        service.setRanked(totalScore != 0 && service.getSlaScore() != null);

      } catch (Exception e) {
        logger.warn("Error computing score for service " + service.getServiceId(), e);
        service.setRanked(false);
      }

      if (!service.isRanked()) {
        numServicesNotRanked++;
      }

    }

    List<RankedService> results = new ArrayList<RankedService>(services.values());

    /** Sort the list of services according to the SLA weight (preference); 
     *  if the weight is equal, then consider the score computed above 
     */
    Collections.sort(results, new ServiceComparator());

    List<RankResult> ranked = new ArrayList<RankResult>();

    // Associate the rank to each element in the list and create results
    int n = results.size();
    int rank = (n - numServicesNotRanked);

    for (int i = 0; i < n; i++) {
      RankedService s = results.get(i);
      if (s.isRanked()) {
        s.setRank(rank);
        rank--;
      } else {
        s.setRank(-1);
      }
      ranked.add(new RankResult(s.getProvider(), s.getServiceId(), 
                                s.getSlaWeight(), s.getTotalScore(), s.getRank(), s.isRanked()));
    }

    logger.debug("Ranking results: " + Utils.toJsonString(results));

    return ranked;

  }
}
