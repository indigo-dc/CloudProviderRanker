package org.indigo.cloudproviderranker.dto;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class RankRequestTest {

    @Test
    public void test() {

        List<Preference> preferences = new ArrayList<Preference>();
        Preference pr = new Preference();
        pr.setService_type("service_type");
        preferences.add(pr);

        List<Sla> sla = new ArrayList<Sla>();
        Sla s = new Sla();
        s.setCustomer("customer");
        sla.add(s);

        List<Provider> monitoring = new ArrayList<Provider>();
        Provider p = new Provider();
        p.setProvider("provider");
        monitoring.add(p);

        RankRequest rr1 = new RankRequest();
        rr1.setPreferences(preferences);
        rr1.setSla(sla);
        rr1.setMonitoring(monitoring);

        assertTrue(rr1.getPreferences() != null);
        assertTrue(rr1.getPreferences().get(0).equals(pr));
        assertTrue(rr1.getSla() != null);
        assertTrue(rr1.getSla().get(0).equals(s));
        assertTrue(rr1.getMonitoring() != null);
        assertTrue(rr1.getMonitoring().get(0).equals(p));

        rr1.toString();

        RankRequest rr2 = new RankRequest(preferences, sla, monitoring);

        assertTrue(rr2.getPreferences() != null);
        assertTrue(rr2.getPreferences().get(0).equals(pr));
        assertTrue(rr2.getSla() != null);
        assertTrue(rr2.getSla().get(0).equals(s));
        assertTrue(rr2.getMonitoring() != null);
        assertTrue(rr2.getMonitoring().get(0).equals(p));

    }

}
