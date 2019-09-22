package br.com.cvc.travel.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.cvc.travel.model.CalculationOfHotelPrice;
import br.com.cvc.travel.model.Hotel;
import br.com.cvc.travel.model.HotelPriceCalculationExecution;
import br.com.cvc.travel.model.HotelPriceCalculationParam;
import br.com.cvc.travel.model.Room;
import br.com.cvc.travel.model.TravelCalculationExecution;
import br.com.cvc.travel.service.HotelPriceCalculationService;

@Service
public class HotelPriceCalculationServiceImpl implements HotelPriceCalculationService {
	
	public static final BigDecimal COMMISSION_VALUE = new BigDecimal(0.7);
	
	@Override
	public TravelCalculationExecution calculateHotelPrice(HotelPriceCalculationParam param) {
		
	 	List<HotelPriceCalculationExecution> asyncTasks = new ArrayList<>();
		for(Hotel hotel : param.getHotels()) {
			CompletableFuture<CalculationOfHotelPrice> supplyAsync = CompletableFuture.supplyAsync(() -> calculatePriceHotel(param, hotel));
			HotelPriceCalculationExecution hotelPriceCalcExecution = new HotelPriceCalculationExecution(supplyAsync);
			asyncTasks.add(hotelPriceCalcExecution);
		}
	 
		List<HotelPriceCalculationExecution> calculationsExceptions = new ArrayList<>();
		List<CalculationOfHotelPrice> successfulCalculations = asyncTasks.stream().map(cf -> {
		try {
			return Optional.of(cf.getFutureCalculoHotelResponse().get());
		} catch (Exception e) {
			cf.setException(e);
			calculationsExceptions.add(cf);
			return Optional.ofNullable((CalculationOfHotelPrice)null);
		}
		}).filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());

		TravelCalculationExecution travelCalculationExecution = new TravelCalculationExecution(successfulCalculations, calculationsExceptions);
		return travelCalculationExecution;
  
	}

	private CalculationOfHotelPrice calculatePriceHotel(HotelPriceCalculationParam param, Hotel hotel) {
		 
		final CalculationOfHotelPrice calculationOfHotelPrice = new CalculationOfHotelPrice();	
		calculationOfHotelPrice.setHotel(hotel);
		 
	    final Period intervalPeriod = Period.between(param.getRequest().getCheckin(), param.getRequest().getCheckout());
	    final int diffDays = intervalPeriod.getDays();
	  
	 	for (final Room room : hotel.getRooms()) {	
	 		BigDecimal totalPrice = BigDecimal.ZERO;		
	 		BigDecimal adultsValue = room.getPrice().getAdult().multiply(BigDecimal.valueOf(param.getRequest().getAdultsQuantity()).multiply(new BigDecimal(diffDays)));	 		
	 		BigDecimal childremValue = room.getPrice().getChild().multiply(BigDecimal.valueOf(param.getRequest().getChildremQuantity()).multiply(new BigDecimal(diffDays)));	 		
	 		BigDecimal adultsCommission = room.getPrice().getAdult().divide(COMMISSION_VALUE, RoundingMode.HALF_EVEN);	 		
	 		BigDecimal childremCommission = room.getPrice().getChild().divide(COMMISSION_VALUE, RoundingMode.HALF_EVEN);
	 		
	 		totalPrice = totalPrice.add(adultsValue).add(childremValue).add(adultsCommission).add(childremCommission);
	 		
	 		room.setTotalPrice(totalPrice);	 		
		}

		return calculationOfHotelPrice;
	}
	
}
