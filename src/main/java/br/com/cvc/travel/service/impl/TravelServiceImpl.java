package br.com.cvc.travel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cvc.travel.exception.BusinessException;
import br.com.cvc.travel.model.CalculationOfHotelPrice;
import br.com.cvc.travel.model.Hotel;
import br.com.cvc.travel.model.HotelPriceCalculationExecution;
import br.com.cvc.travel.model.HotelPriceCalculationParam;
import br.com.cvc.travel.model.TravelCalculationExecution;
import br.com.cvc.travel.model.TravelRequest;
import br.com.cvc.travel.model.TravelRequestCity;
import br.com.cvc.travel.model.TravelRequestHotel;
import br.com.cvc.travel.model.TravelResponse;
import br.com.cvc.travel.service.HotelPriceCalculationService;
import br.com.cvc.travel.service.HotelService;
import br.com.cvc.travel.service.TravelService;

@Service
public class TravelServiceImpl implements TravelService {

	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private HotelPriceCalculationService hotelPriceCalculationService;
	
	@Override
	public TravelResponse quotateHotelsByCity(TravelRequestCity request) {
	
		List<Hotel> hotelsSelected = hotelService.getHotelsSelection(request);
	 
		return getQuotationForHotelsSelected(request.getTravelRequest(), hotelsSelected);
		
	}


	private TravelResponse getQuotationForHotelsSelected(final TravelRequest request, final List<Hotel> hotelsSelected) {

		TravelCalculationExecution execution =  hotelPriceCalculationService.calculateHotelPrice(HotelPriceCalculationParam.of(request, hotelsSelected));
		
		List<CalculationOfHotelPrice> hotelsWithPriceCalculated = execution.getCalculationsPerformed();
		
		System.out.println(hotelsWithPriceCalculated);
		
		verifyErrors(execution.getCalculationsException());
	 
		return TravelResponse.of(hotelsWithPriceCalculated);
	}


	@Override
	public TravelResponse quotateHotelByHotel(TravelRequestHotel request) {
		
		List<Hotel> hotelsSelected = hotelService.getHotelsSelection(request);
		 
		return getQuotationForHotelsSelected(request.getTravelRequest(), hotelsSelected);
	}

	
	private void verifyErrors(List<HotelPriceCalculationExecution> calculationsException) {
		 
		if (!calculationsException.isEmpty()) {
			String errors = calculationsException.stream()
						.map(ex -> ex.getException().getMessage())
						.toString();
			throw new BusinessException(errors);
		}
	}
 
}
