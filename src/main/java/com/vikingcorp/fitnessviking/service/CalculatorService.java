package com.vikingcorp.fitnessviking.service;

import org.springframework.stereotype.Component;

@Component
public class CalculatorService {
	
	public double calculateOneRepMax(double weight, double reps) {
		return weight * (1 + (reps / 30));
	}
}
