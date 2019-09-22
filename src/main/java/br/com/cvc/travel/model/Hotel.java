package br.com.cvc.travel.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode(callSuper = true, of = "id")
public class Hotel extends BaseDTO {

	private static final long serialVersionUID = 420294716133550605L;
	@JsonProperty
	private Long id;
	@JsonProperty
	private String name;
	@JsonProperty
	private Long cityCode;
	@JsonProperty
	private String cityName;
	@JsonProperty
	private List<Room> rooms;

}
