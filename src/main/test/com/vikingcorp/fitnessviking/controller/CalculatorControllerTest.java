package com.vikingcorp.fitnessviking.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vikingcorp.fitnessviking.model.OneRepMaxRequest;
import com.vikingcorp.fitnessviking.service.CalculatorService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class CalculatorControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CalculatorService calculatorService;
	
	@Test
	public void expectedOneRepMaxIsreturnedForGivenRequest() throws Exception {
		double weight = 225;
		int reps = 10;
		double expectedOneRepMax = 300;
		
		when(calculatorService.calculateOneRepMax(weight, reps)).thenReturn(expectedOneRepMax);
		
		OneRepMaxRequest oneRepMaxRequest = new OneRepMaxRequest();
		oneRepMaxRequest.setWeight(weight);
		oneRepMaxRequest.setReps(reps);
		
		String requestJson = new ObjectMapper().writeValueAsString(oneRepMaxRequest);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/calculator/onerepmax")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestJson);
		
		MvcResult result = mockMvc.perform(requestBuilder)
		.andExpect(status().isOk())
		.andReturn();
		
		assertThat(result.getResponse().getContentAsString(), is("300.0"));
	}

}
