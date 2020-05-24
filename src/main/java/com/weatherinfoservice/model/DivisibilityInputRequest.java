package com.weatherinfoservice.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

public class DivisibilityInputRequest {
	
	@Digits(message = "firstDivisor must be a positive Integer value of maxm 2 digits",  integer = 2, fraction = 0)
	@Min(value = 1, message = "must be a positive integer" )
	private int firstDivisor;
	
	@Digits(message = "secondDivisor must be a positive Integer value of maxm 2 digits", integer = 2, fraction = 0)
	@Min(value = 1, message = "must be a positive integer" )
	private int secondDivisor;
	
	@Digits(message = "dividend must be a positive Integer value of maxm 4 digits", integer = 4, fraction = 0)
	@Min(value = 1, message = "must be a positive integer" )
	private int dividend;
	
	

	public DivisibilityInputRequest() {
		super();
	}

	public DivisibilityInputRequest( int firstDivisor, int secondDivisor,int dividend) {
		super();
		this.firstDivisor = firstDivisor;
		this.secondDivisor = secondDivisor;
		this.dividend = dividend;
	}

	public int getFirstDivisor() {
		return firstDivisor;
	}

	public void setFirstDivisor(int firstDivisor) {
		this.firstDivisor = firstDivisor;
	}

	public int getSecondDivisor() {
		return secondDivisor;
	}

	public void setSecondDivisor(int secondDivisor) {
		this.secondDivisor = secondDivisor;
	}

	public int getDividend() {
		return dividend;
	}

	public void setDividend(int dividend) {
		this.dividend = dividend;
	}

	@Override
	public String toString() {
		return "DivisibilityInputRequest [firstDivisor=" + firstDivisor + ", secondDivisor=" + secondDivisor
				+ ", dividend=" + dividend + "]";
	}

	
	

}
