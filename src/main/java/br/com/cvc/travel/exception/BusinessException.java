package br.com.cvc.travel.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper=true)
public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 1L;
 
    public BusinessException(String message) {
        super(message);
       
    }
}