package com.weatherinfoservice.model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonPropertyOrder({ "longitude", "latitude" })
public class Coordinates {

	
	private Double longitude;

	
	private Double latitude;

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public Coordinates() {
		super();
	}

	@JsonProperty("longitude")
	public Double getLongitude() {
		return longitude;
	}
    @JsonProperty("lon")
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
    @JsonProperty("latitude")
	public Double getLatitude() {
		return latitude;
	}
	@JsonProperty("lat")
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public String toString() {
		return "Coordinates [longitude=" + longitude + ", latitude=" + latitude + ", additionalProperties="
				+ additionalProperties + "]";
	}

}
