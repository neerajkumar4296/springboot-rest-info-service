package com.weatherinfoservice.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonView;
import com.weatherinfoservice.jsonviews.JsonResponseView;

@Entity
public class Booking implements  Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "bookingid", length = 25, nullable = false, unique = true, updatable = false)
	@JsonView(JsonResponseView.Public.class)
	private String bookingId;
	
	@Column(name = "bookingtime", nullable = false, unique = true, updatable = false)
	@JsonView(JsonResponseView.Public.class)
	private LocalDateTime bookingTime;
	
	@Transient
	@JsonView(JsonResponseView.Public.class)
	private String message;
	
	@Embedded
	@JsonView(JsonResponseView.Private.class)
	private Passenger passenger;
	
	
	
	public Booking() {
		super();
	}
	
	
	public String getBookingId() {
		return bookingId;
	}
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	
	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public LocalDateTime getBookingTime() {
		return bookingTime;
	}
	public void setBookingTime(LocalDateTime localDateTime) {
		this.bookingTime = localDateTime;
	}
	
	
	public Passenger getPassenger() {
		return passenger;
	}
	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}
	
	
	
}
