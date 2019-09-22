package br.com.cvc.travel.service;

import br.com.cvc.travel.model.HotelPriceCalculationParam;
import br.com.cvc.travel.model.TravelCalculationExecution;

public interface HotelPriceCalculationService {
	
	TravelCalculationExecution calculateHotelPrice(HotelPriceCalculationParam param);
}
