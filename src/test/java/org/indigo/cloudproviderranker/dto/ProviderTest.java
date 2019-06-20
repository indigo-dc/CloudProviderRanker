package org.indigo.cloudproviderranker.dto;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.indigo.cloudproviderranker.dto.Provider;
import org.indigo.cloudproviderranker.dto.Service;

public class ProviderTest {

	@Test
	public void test( ) {

		ArrayList<Service> services = new ArrayList<Service>();
	    Service s = new Service();
	    s.setService_id("service_id");
	    services.add(s);
		
		Provider p1 = new Provider();
		p1.setProvider("provider");
	    p1.setServices(services);
	    
		assertTrue( p1.getProvider().compareTo("provider")==0 ); 
		assertTrue( p1.getServices() != null); 
		assertTrue( p1.getServices().get(0).equals(s));
		
		p1.toString();
		
		Provider p2 = new Provider(	"provider",
									services);

		assertTrue( p2.getProvider().compareTo("provider")==0 ); 
		assertTrue( p2.getServices() != null); 
		assertTrue( p2.getServices().get(0).equals(s));
		
	}
}
