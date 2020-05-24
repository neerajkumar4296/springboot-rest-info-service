package com.weatherinfoservice.exceptions;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.EXPECTATION_FAILED)
public class MyAppException extends DataAccessException {

	
	private static final long serialVersionUID = 1L;
	private static String detailMessage;

	public MyAppException() {
		super(detailMessage);
		
	}

	public MyAppException(String message, Throwable cause) {
		super(message, cause);
	}

	public MyAppException(String message) {
		super(message);
	}


	
	

}
