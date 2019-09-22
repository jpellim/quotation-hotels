package br.com.cvc.travel.model;

import java.util.List;

public class TravelCalculationExecution {
 
	private List<CalculationOfHotelPrice> calculationsPerformed;
	private List<HotelPriceCalculationExecution> calculationsException;
	  
	public TravelCalculationExecution(List<CalculationOfHotelPrice> calculationsPerformed,
			List<HotelPriceCalculationExecution> calculationsException) {
		super();
		this.calculationsPerformed = calculationsPerformed;
		this.calculationsException = calculationsException;
	}
	
	public List<CalculationOfHotelPrice> getCalculationsPerformed() {
		return calculationsPerformed;
	}
	public void setCalculationsPerformed(List<CalculationOfHotelPrice> calculationsPerformed) {
		this.calculationsPerformed = calculationsPerformed;
	}
	public List<HotelPriceCalculationExecution> getCalculationsException() {
		return calculationsException;
	}
	public void setCalculationsException(List<HotelPriceCalculationExecution> calculationsException) {
		this.calculationsException = calculationsException;
	}
 
}
