package org.indigo.cloudproviderranker.utils;

import java.util.Comparator;

import org.indigo.cloudproviderranker.dto.RankedService;

public class ServiceComparator implements Comparator<RankedService> {

    @Override
    public int compare(RankedService s1, RankedService s2) {

        if (s1 == null && s2 == null)
            return 0;
        if (s1 != null && s2 == null)
            return 1;
        if (s1 == null && s2 != null)
            return -1;
        
        Float slaWeight1 = (s1.getSlaWeight() == null ? 0 : s1.getSlaWeight());                
        Float slaWeight2 = (s2.getSlaWeight() == null ? 0 : s2.getSlaWeight());
        
        if (slaWeight1 > slaWeight2)
            return 1;
        else if (slaWeight1 < slaWeight2)
            return -1;
        else // if no preferences is set on the SLAs...
        {
            Float score1 = s1.getTotalScore();
            Float score2 = s2.getTotalScore();

            if (score1 > score2)
                return 1;
            else if (score1 < score2)
                return -1;
            else
                return 0;
        }
    }
}
