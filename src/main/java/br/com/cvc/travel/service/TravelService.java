package br.com.cvc.travel.service;

import br.com.cvc.travel.model.TravelRequestCity;
import br.com.cvc.travel.model.TravelRequestHotel;
import br.com.cvc.travel.model.TravelResponse;

public interface TravelService {
 
	TravelResponse quotateHotelsByCity(TravelRequestCity request);
	
	TravelResponse quotateHotelByHotel(TravelRequestHotel request);
}
