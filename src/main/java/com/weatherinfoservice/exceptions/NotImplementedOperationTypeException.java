package com.weatherinfoservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_IMPLEMENTED,code =HttpStatus.NOT_IMPLEMENTED)
public class NotImplementedOperationTypeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotImplementedOperationTypeException() {
		super();
	}

	public NotImplementedOperationTypeException(String message) {
		super(message);
	}
	
	
	
	

}
