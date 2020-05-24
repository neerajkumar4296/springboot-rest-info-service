package com.weatherinfoservice.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

public class MathsInputRequest {

	
	@Digits(message = "firstNumber must could a double value of upto 3 decimal places", fraction = 3, integer = 4)
	@Min(value = 1, message = "must be a positive integer" )
	private double firstNumber;
	
	@Digits(message = "secondNumber could be a double value of upto 3 decimal places", fraction = 3, integer = 4)
	@Min(value = 1, message = "must be a positive integer" )
	private double secondNumber;

	
	private OperationType operationType;
	
	
	
	public MathsInputRequest() {
		super();
	}


	public MathsInputRequest( double firstNumber,
			 double secondNumber,
			 OperationType operationType) {
		super();
		this.firstNumber = firstNumber;
		this.secondNumber = secondNumber;
		this.operationType = operationType;
	}
	
	
	public double getFirstNumber() {
		return firstNumber;
	}
	public void setFirstNumber(double firstNumber) {
		this.firstNumber = firstNumber;
	}
	public double getSecondNumber() {
		return secondNumber;
	}
	public void setSecondNumber(double secondNumber) {
		this.secondNumber = secondNumber;
	}
	public OperationType getOperationType() {
		return operationType;
	}
	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}

	
	
	
	

	
	
	
}
