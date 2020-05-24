package com.weatherinfoservice.model;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SystemDetails {

	private Integer systemType;

	private Integer systemId;

	private String country;

	private long sunriseTime;

	private long sunsetTime;

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public SystemDetails() {
		super();
	}

	@JsonProperty("systemType")
	public Integer getSystemType() {
		return systemType;
	}

	@JsonProperty("type")
	public void setSystemType(Integer type) {
		this.systemType = type;
	}

	@JsonProperty("systemId")
	public Integer getSystemId() {
		return systemId;
	}

	@JsonProperty("id")
	public void setSystemId(Integer id) {
		this.systemId = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@JsonProperty("sunriseTime")
	public LocalDateTime getSunriseTime() {
		return LocalDateTime.ofEpochSecond(sunriseTime, 0, ZoneOffset.ofHoursMinutes(5, 30));
	}

	@JsonProperty("sunrise")
	public void setSunriseTime(long sunriseTime) {
		this.sunriseTime = sunriseTime;
	}

	@JsonProperty("sunsetTime")
	public LocalDateTime getSunsetTime() {
		return LocalDateTime.ofEpochSecond(sunsetTime, 0, ZoneOffset.ofHoursMinutes(5, 30));
	}

	@JsonProperty("sunset")
	public void setSunsetTime(long sunsetTime) {
		this.sunsetTime = sunsetTime;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}

	@Override
	public String toString() {
		return "SystemDetails [type=" + systemType + ", id=" + systemId + ", country=" + country + ", sunriseTime="
				+ sunriseTime + ", sunsetTime=" + sunsetTime + ", additionalProperties=" + additionalProperties + "]";
	}

}
