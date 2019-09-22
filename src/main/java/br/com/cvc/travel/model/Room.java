package br.com.cvc.travel.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode(callSuper = true, of = "roomID")
public class Room extends BaseDTO {

	private static final long serialVersionUID = -3216924519976919270L;

	@JsonProperty
	private Integer roomID;
	@JsonProperty
	private String categoryName;
	@JsonProperty
	private BigDecimal totalPrice;
	@JsonProperty
	private PriceDetail price;

}
