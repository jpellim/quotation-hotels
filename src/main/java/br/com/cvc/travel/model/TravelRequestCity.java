package br.com.cvc.travel.model;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class TravelRequestCity {

    @NotNull
	private Long cityCode;
	
	private TravelRequest travelRequest;
}
