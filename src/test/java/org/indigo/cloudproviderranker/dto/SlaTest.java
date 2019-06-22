package org.indigo.cloudproviderranker.dto;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class SlaTest {

    @Test
    public void test() {

        Service service = new Service();
        service.setService_id("service_id");
        List<Service> services = new ArrayList<Service>();
        services.add(service);

        Sla s1 = new Sla();
        s1.setCustomer("customer");
        s1.setProvider("provider");
        s1.setStart_date("start_date");
        s1.setEnd_date("end_date");
        s1.setServices(services);
        s1.setId("sla_id");

        assertTrue(s1.getCustomer().compareTo("customer") == 0);
        assertTrue(s1.getProvider().compareTo("provider") == 0);
        assertTrue(s1.getStart_date().compareTo("start_date") == 0);
        assertTrue(s1.getEnd_date().compareTo("end_date") == 0);
        assertTrue(s1.getServices() != null);
        assertTrue(s1.getServices().get(0).equals(service));
        assertTrue(s1.getId().compareTo("sla_id") == 0);

        s1.toString();

        Sla s2 = new Sla("customer", "provider", "start_date", "end_date", services, "sla_id");

        assertTrue(s2.getCustomer().compareTo("customer") == 0);
        assertTrue(s2.getProvider().compareTo("provider") == 0);
        assertTrue(s2.getStart_date().compareTo("start_date") == 0);
        assertTrue(s2.getEnd_date().compareTo("end_date") == 0);
        assertTrue(s2.getServices() != null);
        assertTrue(s2.getServices().get(0).equals(service));
        assertTrue(s2.getId().compareTo("sla_id") == 0);

    }
}
