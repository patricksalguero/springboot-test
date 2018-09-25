package com.psalguero.test.testcontroller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
public class HellowResourceTest {

	private MockMvc mockMvc;
	private JSONObject hellowObject;
	
	@InjectMocks
	private HellowResource hellowResource;

	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.hellowResource).build();
		this.hellowObject = new JSONObject();
		this.hellowObject.put("title", "nombre");
		this.hellowObject.put("value", "Patrick Salguero");
	}

	@Test
	public void testHelloString() throws Exception {
		this.mockMvc.perform(get("/hello")).andExpect(status().isOk()).andExpect(content().string("Patrick Salguero"));
	}

	@Test
	public void testHelloJson() throws Exception {
		this.mockMvc.perform(get("/hello/json").accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.title", Matchers.is("nombre")))
				.andExpect(jsonPath("$.value", Matchers.is("Patrick Salguero")))
				.andExpect(jsonPath("$.*", Matchers.hasSize(2)))
				.andExpect(content().json(this.hellowObject.toString()));
	}
}
