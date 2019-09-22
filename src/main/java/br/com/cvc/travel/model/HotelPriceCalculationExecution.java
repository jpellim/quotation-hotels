package br.com.cvc.travel.model;

import java.util.concurrent.CompletableFuture;


public class HotelPriceCalculationExecution {

	private CompletableFuture<CalculationOfHotelPrice> futureCalculoHotelResponse;
	private Exception exception;

	public HotelPriceCalculationExecution(CompletableFuture<CalculationOfHotelPrice> futureCalculoHotelResponse) {
		super();
		this.futureCalculoHotelResponse = futureCalculoHotelResponse;
	}

	public CompletableFuture<CalculationOfHotelPrice> getFutureCalculoHotelResponse() {
		return futureCalculoHotelResponse;
	}

	public void setFutureCalculoHotelResponse(CompletableFuture<CalculationOfHotelPrice> futureCalculoHotelResponse) {
		this.futureCalculoHotelResponse = futureCalculoHotelResponse;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}
	
	
 
}
