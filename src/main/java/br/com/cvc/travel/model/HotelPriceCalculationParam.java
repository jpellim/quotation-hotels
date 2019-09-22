package br.com.cvc.travel.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class HotelPriceCalculationParam {

	private TravelRequest request;
	private List<Hotel> hotels;
}
