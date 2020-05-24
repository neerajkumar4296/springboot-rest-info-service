package com.weatherinfoservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ADDRESSES")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer addressId;
	
	@Column(name = "HOUSE_NO", nullable = false)
	private String houseno;
	
	@Column(name = "ROAD", nullable = false)
	private String road;
	
	@Column(name = "CITY", nullable = false)
	private String city;
	
	@Column(name = "STATE", nullable = false)
	private String state;
	
	@Column(name = "COUNTRY", nullable = false)
	private String country;
	
	@Column(name = "ZIP_CODE", nullable = false)
	private Integer zip;
	
	@Column(name = "ADDRESS_TYPE", nullable = false)
	@Enumerated(EnumType.STRING)
	private AddressType addressType;

	public Address() {
		super();
	}

	public Address(String houseno, String road, String city, String state, String country,
			Integer zip, AddressType addressType) {
		this.houseno = houseno;
		this.road = road;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zip = zip;
		this.addressType = addressType;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public String getHouseno() {
		return houseno;
	}

	public void setHouseno(String houseno) {
		this.houseno = houseno;
	}

	public String getRoad() {
		return road;
	}

	public void setRoad(String road) {
		this.road = road;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getZip() {
		return zip;
	}

	public void setZip(Integer zip) {
		this.zip = zip;
	}

	public AddressType getAddressType() {
		return addressType;
	}

	public void setAddressType(AddressType addressType) {
		this.addressType = addressType;
	}

	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", houseno=" + houseno + ", road=" + road + ", city=" + city
				+ ", state=" + state + ", country=" + country + ", zip=" + zip + ", addressType=" + addressType + "]";
	}

	

}
