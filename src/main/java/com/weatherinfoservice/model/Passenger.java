package com.weatherinfoservice.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.convert.JodaTimeConverters.DateToLocalDateConverter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonView;
import com.weatherinfoservice.jsonviews.JsonResponseView;

@Embeddable
public class Passenger implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "firstname", nullable = false, length = 25)
	@JsonView(JsonResponseView.Public.class)
	@NotBlank(message = "first name can't be blank")
	@Size(min = 3, max = 25, message = "First name size should be between 3 and 25")
	private String firstName;
	
	@Column(name = "lastname", length = 25)
	@JsonView(JsonResponseView.Public.class)
	private String lastName;
	
	@Column(name = "source", updatable = false)
	@JsonView(JsonResponseView.Public.class)
	@NotBlank(message = "source can't be blank")
	@Size(min = 3, max = 15, message = "source size should be between 3 and 15")
	private String source;
	
	@Column(name = "destination", updatable = false)
	@JsonView(JsonResponseView.Public.class)
	@NotBlank(message = "destination can't be blank")
	@Size(min = 3, max = 15, message = "destination size should be between 3 and 15")
	private String destination;
	
	@Column(name = "mobileno", length =10, updatable = false)
	@JsonView(JsonResponseView.Private.class)
    @Pattern(regexp = "[789]{1}\\d{9}",message = "mobile number must be 0f 10 digits starting with 7 or 8 or 9")
	private String mobileNo;
	
	@Column(name = "dateofjourney", nullable = false, updatable = false)
	@JsonView(JsonResponseView.Private.class)
	@DateTimeFormat(iso = ISO.DATE, pattern = "^(1[0-2]|0[1-9])/(3[01]"+ "|[12][0-9]|0[1-9])/[0-9]{4}$")
	private LocalDate dateOfJourney;
	
	@Column(name = "noofticket", length = 1, updatable = false)
	@JsonView(JsonResponseView.Private.class)
	@DecimalMax(value = "5",message = "noOfTicket can't be more than 5")
	@DecimalMin(value = "1", message = "noOfTicket can't be less than 1")
	private int noOfTicket;

	public Passenger() {
		super();
	}

	public Passenger(String firstName, String lastName, String source, String destination, String mobileNo,
			int noOfTicket) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.source = source;
		this.destination = destination;
		this.mobileNo = mobileNo;
		this.noOfTicket = noOfTicket;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Optional<String> getLastName() {
		return Optional.ofNullable(lastName);
	}

	public void setLastName(String lastName) {
		this.lastName = Optional.ofNullable(lastName).get();
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getMobileNo() {
		return mobileNo;
	}
	
	public LocalDate getDateOfJourney() {
		return dateOfJourney;
	}

	public void setDateOfJourney(LocalDate dateOfJourney) {
		this.dateOfJourney = dateOfJourney;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public int getNoOfTicket() {
		return noOfTicket;
	}

	public void setNoOfTicket(int noOfTicket) {
		this.noOfTicket = noOfTicket;
	}

	@Override
	public String toString() {
		return "Passenger [firstName=" + firstName + ", lastName=" + lastName + ", source=" + source + ", destination="
				+ destination + ", mobileNo=" + mobileNo + ", noOfTicket=" + noOfTicket + "]";
	}

}
