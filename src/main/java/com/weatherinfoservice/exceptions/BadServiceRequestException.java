package com.weatherinfoservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,code =HttpStatus.BAD_REQUEST)
public class BadServiceRequestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BadServiceRequestException() {
		super();
	}

	public BadServiceRequestException(String message) {
		super(message);
	}
}
