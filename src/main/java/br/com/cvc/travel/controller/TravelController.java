package br.com.cvc.travel.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cvc.travel.model.TravelRequest;
import br.com.cvc.travel.model.TravelRequestCity;
import br.com.cvc.travel.model.TravelRequestHotel;
import br.com.cvc.travel.model.TravelResponse;
import br.com.cvc.travel.service.TravelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Permite realizar operações sobre informações de Hotéis.")
@RestController
@RequestMapping("/travel")
public class TravelController {

	@Autowired
	private TravelService travelService;

    @ApiOperation("Operação que permite calcular o preço total para cada quarto de cada hotel de um código de cidade.")
	@GetMapping("/hotels/city/{cityCode}")
	TravelResponse hotelsQuotation(@PathVariable(name = "cityCode", required = true) final Long cityCode,
			@RequestParam(name = "checkin", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate checkin,
			@RequestParam(name = "checkout", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate checkout,
			@RequestParam(name = "adultsQuantity", required = true) final Integer adultsQuantity,
			@RequestParam(name = "childremQuantity", required = true) final Integer childremQuantity) {

		return travelService.quotateHotelsByCity(montarRequestCity(cityCode, checkin, checkout, adultsQuantity, childremQuantity));

	}
    
    @ApiOperation("Operação que permite calcular o preço total para cada quarto de um hotel específico.")
	@GetMapping("/hotels/{hotelCode}")
	TravelResponse hotelQuotation(@PathVariable(name = "hotelCode", required = true) final Long hotelCode,
			@RequestParam(name = "checkin", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate checkin,
			@RequestParam(name = "checkout", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate checkout,
			@RequestParam(name = "adultsQuantity", required = true) final Integer adultsQuantity,
			@RequestParam(name = "childremQuantity", required = true) final Integer childremQuantity) {

		return travelService.quotateHotelByHotel(montarRequestHotel(hotelCode, checkin, checkout, adultsQuantity, childremQuantity));

	}
    
    private TravelRequestHotel montarRequestHotel(Long hotelCode, LocalDate checkin, LocalDate checkout, Integer adultsQuantity,
			Integer childremQuantity) {
    	return TravelRequestHotel.of(hotelCode, montarRequest(checkin, checkout, adultsQuantity, childremQuantity));
    }

    private TravelRequestCity montarRequestCity(Long cityCode, LocalDate checkin, LocalDate checkout, Integer adultsQuantity,
			Integer childremQuantity) {
    	return TravelRequestCity.of(cityCode, montarRequest(checkin, checkout, adultsQuantity, childremQuantity));
    }
    
	private TravelRequest montarRequest(LocalDate checkin, LocalDate checkout, Integer adultsQuantity,
			Integer childremQuantity) {
		return TravelRequest.of(checkin, checkout, adultsQuantity, childremQuantity);
	}

}
