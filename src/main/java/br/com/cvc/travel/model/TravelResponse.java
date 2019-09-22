package br.com.cvc.travel.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class TravelResponse {

	private List<CalculationOfHotelPrice> hotelsWithPricesCalculated; 
}
