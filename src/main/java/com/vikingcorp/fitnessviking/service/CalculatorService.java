package com.vikingcorp.fitnessviking.service;

import org.springframework.stereotype.Component;

import com.vikingcorp.fitnessviking.model.Plates;

@Component
public class CalculatorService {
	
	public double calculateOneRepMax(int weight, double reps) {
		return weight * (1 + (reps / 30));
	}
	
	public Plates calculatePlatesNeeded(int weight) {
		Plates plates = new Plates();
		int weightMinusBar = weight - 45;
		
		if(weightMinusBar >= 0 && weightMinusBar % 5 == 0) {
			int numberOf45 = calculateNumberOfPlates(weightMinusBar, 45);
			plates.setFortyFive(numberOf45);
			weightMinusBar = weightMinusBar - (numberOf45 * 45);
			
			int numberOf35 = calculateNumberOfPlates(weightMinusBar, 35);
			plates.setThirtyFive(numberOf35);
			weightMinusBar = weightMinusBar - (numberOf35 * 35);
			
			int numberOf25 = calculateNumberOfPlates(weightMinusBar, 25);
			plates.setTwentyFive(numberOf25);
			weightMinusBar = weightMinusBar - (numberOf25 * 25);
			
			int numberOf10 = calculateNumberOfPlates(weightMinusBar, 10);
			plates.setTen(numberOf10);
			weightMinusBar = weightMinusBar - (numberOf10 * 10);
			
			int numberOf5 = calculateNumberOfPlates(weightMinusBar, 5);
			plates.setFive(numberOf5);
			weightMinusBar = weightMinusBar - (numberOf5 * 5);
			
			int numberOf2Point5 = calculateNumberOfPlates(weightMinusBar, 2.5);
			plates.setTwoAndHalf(numberOf2Point5);
			weightMinusBar = (int)(weightMinusBar - (numberOf2Point5 * 2.5));

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
}
