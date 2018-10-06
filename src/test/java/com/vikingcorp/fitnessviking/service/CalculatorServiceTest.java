package com.vikingcorp.fitnessviking.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Before;
import org.junit.Test;

import com.vikingcorp.fitnessviking.model.Plates;

public class CalculatorServiceTest {
	
	private CalculatorService calculatorService;

	@Before
	public void setup() {
		calculatorService = new CalculatorService();
	}
	
	@Test
	public void weight225AndReps10ReturnOrmOf300() {
		double expectedResult = 300.0;
		double actualResult = calculatorService.calculateOneRepMax(225, 10);
		
		assertThat(actualResult, is(expectedResult));
	}
	
	@Test
	public void weight135WillReturnTwoFortyFivePoundPlates() {
		int expectedresult = 2;
		
		Plates plates = calculatorService.calculatePlatesNeeded(135);
		int actualResult = plates.getFortyFive();
		
		assertThat(expectedresult, is(actualResult));
	}
	
	@Test
	public void weight115WillReturnTwoThirtyFivePoundPlates() {
		int expectedresult = 2;
		
		Plates plates = calculatorService.calculatePlatesNeeded(115);
		int actualResult = plates.getThirtyFive();
		
		assertThat(expectedresult, is(actualResult));
	}
	
	@Test
	public void weight95WillReturnTwoTwentyFivePoundPlates() {
		int expectedresult = 2;
		
		Plates plates = calculatorService.calculatePlatesNeeded(95);
		int actualResult = plates.getTwentyFive();
		
		assertThat(expectedresult, is(actualResult));
	}
	
	@Test
	public void weight65WillReturnTwoTenPoundPlates() {
		int expectedresult = 2;
		
		Plates plates = calculatorService.calculatePlatesNeeded(65);
		int actualResult = plates.getTen();
		
		assertThat(expectedresult, is(actualResult));
	}
	
	@Test
	public void weight55WillReturnTwoFivePoundPlates() {
		int expectedresult = 2;
		
		Plates plates = calculatorService.calculatePlatesNeeded(55);
		int actualResult = plates.getFive();
		
		assertThat(expectedresult, is(actualResult));
	}
	
	@Test
	public void weight50WillReturnTwoTwoAndHalfPoundPlates() {
		int expectedresult = 2;
		
		Plates plates = calculatorService.calculatePlatesNeeded(50);
		int actualResult = plates.getTwoAndHalf();
		
		assertThat(expectedresult, is(actualResult));
	}

}
