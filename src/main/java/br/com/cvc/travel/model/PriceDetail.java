package br.com.cvc.travel.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PriceDetail extends BaseDTO {

	private static final long serialVersionUID = 6085684435977520241L;

	@JsonProperty
	private BigDecimal adult;
	@JsonProperty
	private BigDecimal child;
}
