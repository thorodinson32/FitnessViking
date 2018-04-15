package com.vikingcorp.fitnessviking.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vikingcorp.fitnessviking.service.CalculatorService;

@RestController
public class CalculatorController {
	
	private CalculatorService calculatorService;
	
	@Autowired
	public CalculatorController(CalculatorService calculatorService) {
		this.calculatorService = calculatorService;
	}
	
	@RequestMapping(value = "/onerepmax/{weight}/{reps}", 
					method = RequestMethod.GET)
	public ResponseEntity<Double> getOneRepMax(@PathVariable("weight")double weight, 
			@PathVariable("reps")double reps) {
		double oneRepMax = calculatorService.calculateOneRepMax(weight, reps);
		return new ResponseEntity<>(oneRepMax, HttpStatus.OK);
	}

}
