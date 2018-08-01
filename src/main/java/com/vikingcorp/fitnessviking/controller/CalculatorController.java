package com.vikingcorp.fitnessviking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vikingcorp.fitnessviking.model.OneRepMaxRequest;
import com.vikingcorp.fitnessviking.model.Plates;
import com.vikingcorp.fitnessviking.service.CalculatorService;

@RestController
@RequestMapping("calculator")
public class CalculatorController {

	private final CalculatorService calculatorService;

	@Autowired
	public CalculatorController(CalculatorService calculatorService) {
		this.calculatorService = calculatorService;
	}

	@RequestMapping(value = "onerepmax", 
			method = RequestMethod.POST,
			consumes = "application/json")
	public ResponseEntity<Double> getOneRepMax(@RequestBody OneRepMaxRequest request) {
		double oneRepMax = calculatorService.calculateOneRepMax(request.getWeight(), request.getReps());
		
		return new ResponseEntity<>(oneRepMax, HttpStatus.OK);
	}

	@RequestMapping(value = "plates/{weight}", 
			method = RequestMethod.GET)
	public ResponseEntity<Plates> getPlatesForWeight(@PathVariable("weight") double weight) {
		Plates plates = calculatorService.calculatePlatesNeeded(weight);
		
		return new ResponseEntity<>(plates, HttpStatus.OK);
	}

}
