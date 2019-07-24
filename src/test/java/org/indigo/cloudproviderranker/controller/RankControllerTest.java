package org.indigo.cloudproviderranker.controller;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import org.indigo.cloudproviderranker.Application;
import org.indigo.cloudproviderranker.utils.TestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class RankControllerTest {

	private static final String CPRIO_BASE_DIR = "./src/test/resources/RankControllerIO/";
	protected MockMvc mvc;
	@Autowired
	WebApplicationContext webApplicationContext;

	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testRank() throws Exception {

		String json = null;
		try {
			json = TestUtil.getFileContentAsString(CPRIO_BASE_DIR+"cpr-request.json");
		} catch (IOException e) {
			fail("Error reading request template");
		}

		String uri = "/rank";

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(json)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();

		String result = null;
		try {
			result = TestUtil.getFileContentAsString(CPRIO_BASE_DIR+"cpr-response.json");
		} catch (IOException e) {
			fail("Error reading response template");
		}        

		assertEquals(content, result);        

	}

}
