package br.com.cvc.travel.service;

import java.util.List;

import br.com.cvc.travel.model.Hotel;
import br.com.cvc.travel.model.TravelRequestCity;
import br.com.cvc.travel.model.TravelRequestHotel;

public interface HotelService {
	
	public List<Hotel> getHotelsSelection(TravelRequestCity request);
	
	public List<Hotel> getHotelsSelection(TravelRequestHotel request);
		
}
