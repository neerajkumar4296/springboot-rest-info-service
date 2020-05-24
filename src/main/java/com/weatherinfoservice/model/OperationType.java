package com.weatherinfoservice.model;

import com.weatherinfoservice.exceptions.BadServiceRequestException;

public enum OperationType {

	SUM("SUM"), 
	DIVIDE("DIVIDE"),
	MULTIPLY("MULTIPLY"),
	LCM("LCM"),
	HCF("HCF");
	
	
	private final String operation_code;

	private OperationType(String operation_code) {
		this.operation_code = operation_code;
	}

	public String getOperation_code() {
		return operation_code;
	}
	
	

	public static OperationType fromText(String text){
        for(OperationType r : OperationType.values()){
            if(r.getOperation_code().equals(text)){
                return r;
            }
        }
        throw new BadServiceRequestException("unacceptable operation value");
    }
}
