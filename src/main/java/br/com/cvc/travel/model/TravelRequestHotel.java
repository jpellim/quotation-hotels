package br.com.cvc.travel.model;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class TravelRequestHotel {
 
    @NotNull
	private Long hotelCode;
	
	private TravelRequest travelRequest;
}
