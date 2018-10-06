package com.vikingcorp.fitnessviking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

	@GetMapping(value="onerepmax",
			produces = "application/json")
	public ResponseEntity<Double> getOneRepMax(@RequestParam("weight") int weight, 
			@RequestParam("reps") int reps) {
		double oneRepMax = calculatorService.calculateOneRepMax(weight, reps);
		
		return new ResponseEntity<>(oneRepMax, HttpStatus.OK);
	}

	@GetMapping(value = "plates",
			produces = "application/json")
	public ResponseEntity<Plates> getPlatesForWeight(@RequestParam("weight") int weight) {
		Plates plates = calculatorService.calculatePlatesNeeded(weight);
		
		return new ResponseEntity<>(plates, HttpStatus.OK);
	}

}
