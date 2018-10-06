package com.vikingcorp.fitnessviking.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vikingcorp.fitnessviking.model.Plates;
import com.vikingcorp.fitnessviking.service.CalculatorService;

@RunWith(SpringRunner.class)
@WebMvcTest(value=CalculatorController.class, secure = false)
public class CalculatorControllerTest {
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CalculatorService calculatorService;
	
	@Test
	public void calculatorControllerReturnsOneRepMax() throws Exception {
		double expectedResult = 300.0;
		
		when(calculatorService.calculateOneRepMax(225, 10)).thenReturn(expectedResult);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/calculator/onerepmax")
				.param("weight", "225")
				.param("reps", "10");
		
		MvcResult actualResult = mockMvc.perform(requestBuilder).andReturn();
		
		assertThat(actualResult.getResponse().getContentAsString(), 
				is(String.valueOf(expectedResult)));
	}
	
	@Test
	public void calculatorControllerReturnsPlateAmounts() throws Exception {
		Plates expectedResult = new Plates();
		expectedResult.setFortyFive(4);
		
		when(calculatorService.calculatePlatesNeeded(225)).thenReturn(expectedResult);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/calculator/plates")
				.param("weight", "225");
		
		String resultJson = mockMvc.perform(requestBuilder).andReturn().getResponse().getContentAsString();
		
		Plates actualResult = objectMapper.readValue(resultJson, Plates.class);
				
		assertThat(actualResult, 
				samePropertyValuesAs(expectedResult));
	}
	
}
