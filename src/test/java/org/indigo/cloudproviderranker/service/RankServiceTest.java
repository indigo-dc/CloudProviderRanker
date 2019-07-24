package org.indigo.cloudproviderranker.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.indigo.cloudproviderranker.Application;
import org.indigo.cloudproviderranker.dto.RankResult;
import org.indigo.cloudproviderranker.dto.RankedService;
import org.indigo.cloudproviderranker.utils.TestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class RankServiceTest {

	private static final String RS_BASE_DIR = "./src/test/resources/RankServiceIO/";

	@Autowired
	private RankService service;

	@Test
	public void test() {

		String json = null;
		try {
			json = TestUtil.getFileContentAsString(RS_BASE_DIR+"rs-input.json");
		} catch (IOException e) {
			fail("Error reading input template");
		}        

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);        
		TypeFactory typeFactory = mapper.getTypeFactory();
		MapType mapType = typeFactory.constructMapType(HashMap.class, String.class, RankedService.class);        
		HashMap<String, RankedService> services = null;
		try {
			services = mapper.readValue(json, mapType);
		} catch (IOException e) {
			fail("Error creating input HashMap");
		}        

		List<RankResult> result = service.run(services);      

		//mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		String serialized = null;
		try {
			serialized = mapper.writeValueAsString(result);
		} catch (JsonProcessingException e) {
			fail("Error serializing result");
		}   

		try {
			json = TestUtil.getFileContentAsString(RS_BASE_DIR+"rs-output.json");
		} catch (IOException e) {
			fail("Error reading output template");
		}        

		assertEquals(json, serialized);    

	}
}
