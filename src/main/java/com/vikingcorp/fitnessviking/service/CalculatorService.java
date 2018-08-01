package com.vikingcorp.fitnessviking.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.vikingcorp.fitnessviking.model.Plates;

@Component
public class CalculatorService {
	
	public double calculateOneRepMax(double weight, double reps) {
		return weight * (1 + (reps / 30));
	}
	
	public Plates calculatePlatesNeeded(double weight) {
		Plates plates = new Plates();
		double weightMinusBar = weight - 45;
		
		if(weightMinusBar >= 0 && weightMinusBar % 5 == 0) {
			int weightRemainingRounded = (int)weightMinusBar;
			
			int numberOf45 = calculateNumberOfPlates(weightRemainingRounded, 45);
			plates.setFortyFive(numberOf45);
			weightRemainingRounded = weightRemainingRounded - (numberOf45 * 45);
			
			int numberOf35 = calculateNumberOfPlates(weightRemainingRounded, 35);
			plates.setThirtyFive(numberOf35);
			weightRemainingRounded = weightRemainingRounded - (numberOf35 * 35);
			
			int numberOf25 = calculateNumberOfPlates(weightRemainingRounded, 25);
			plates.setTwentyFive(numberOf25);
			weightRemainingRounded = weightRemainingRounded - (numberOf25 * 25);
			
			int numberOf10 = calculateNumberOfPlates(weightRemainingRounded, 10);
			plates.setTen(numberOf10);
			weightRemainingRounded = weightRemainingRounded - (numberOf10 * 10);
			
			int numberOf5 = calculateNumberOfPlates(weightRemainingRounded, 5);
			plates.setFive(numberOf5);
			weightRemainingRounded = weightRemainingRounded - (numberOf5 * 5);
			
			int numberOf2Point5 = calculateNumberOfPlates(weightRemainingRounded, 2.5);
			plates.setTwoAndHalf(numberOf2Point5);
			weightRemainingRounded = (int)(weightRemainingRounded - (numberOf2Point5 * 2.5));

		} else {
			throw new InvalidWeightException();
		}
		
		return plates;
	}
	
	private int calculateNumberOfPlates(int remainingWeight, double plateWeight) {
		int numberOfPlate = (int)(remainingWeight / plateWeight);
		if(numberOfPlate % 2 != 0) {
			numberOfPlate = numberOfPlate - 1;
		}
		
		return numberOfPlate;
	}
	
	@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Weight must be 45 lbs or"
			+ " more and divisible by 5") 
	 private class InvalidWeightException extends RuntimeException {
	 }
}
