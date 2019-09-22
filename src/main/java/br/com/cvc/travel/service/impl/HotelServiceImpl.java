package br.com.cvc.travel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.cvc.travel.model.Hotel;
import br.com.cvc.travel.model.TravelRequestCity;
import br.com.cvc.travel.model.TravelRequestHotel;
import br.com.cvc.travel.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${consultaHoteisPorCidadeRest.url}")
	private String restUrlCity;

	@Value("${consultaDetalheHotelPorCodigoRest.url}")
	private String restUrlHotel;

	
	@Override
	public List<Hotel> getHotelsSelection(TravelRequestCity request) {

		String restUrlWithCityCode = restUrlCity + request.getCityCode();

		return getHotels(restUrlWithCityCode);
		
	}
 
	@Override
	public List<Hotel> getHotelsSelection(TravelRequestHotel request) {
		
		String restUrlWithHotelCode = restUrlHotel + request.getHotelCode();

		return getHotels(restUrlWithHotelCode);
	}
	
	
	private List<Hotel> getHotels(String restUrlWithCityCode) {

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Hotel> requestEntity = new HttpEntity<>(null, headers);

		ParameterizedTypeReference<List<Hotel>> typeRef = new ParameterizedTypeReference<List<Hotel>>() {
		};
		ResponseEntity<List<Hotel>> responseEntity = restTemplate.exchange(restUrlWithCityCode, HttpMethod.GET,
				requestEntity, typeRef);
		List<Hotel> myModelClasses = responseEntity.getBody();

		return myModelClasses;

	}


}
