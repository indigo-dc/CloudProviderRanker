package org.indigo.cloudproviderranker.dto;

import java.util.HashMap;
import java.util.Map;

import org.indigo.cloudproviderranker.dto.Target;
import org.junit.Test;
import static org.junit.Assert.*;
//
public class TargetTest {

	@Test
	public void test( ) {

		Target t = new Target( );
		t.setUnit("u");
		t.setType("t");
		Map<String, Float> restrictions = new HashMap<String,Float>();
		restrictions.put("r1", 1.0f);
		t.setRestrictions(restrictions);

		assertTrue( t.getType().compareTo("t")==0 ); 
		assertTrue( t.getUnit().compareTo("u")==0 );
		assertTrue( t.getRestrictions().containsKey("r1")); 
		assertTrue( t.getRestrictions().containsValue(1.0f)); 
		assertTrue( t.getRestrictions().get("r1").equals(1.0f));

		t.toString();

		Target t2 = new Target("t", "u", restrictions);

		assertTrue( t2.getType().compareTo("t")==0 ); 
		assertTrue( t2.getUnit().compareTo("u")==0 );
		assertTrue( t2.getRestrictions().containsKey("r1")); 
		assertTrue( t2.getRestrictions().containsValue(1.0f)); 
		assertTrue( t2.getRestrictions().get("r1").equals(1.0f));

		t2.toString();

	}

}
