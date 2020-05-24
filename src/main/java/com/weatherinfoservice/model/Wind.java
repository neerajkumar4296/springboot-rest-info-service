package com.weatherinfoservice.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Wind {

	private Double windSpeed;

	private Integer windDegree;

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public Wind() {
		super();
	}

	@JsonProperty("windSpeed")
	public Double getWindSpeed() {
		return windSpeed;
	}

	@JsonProperty("speed")
	public void setWindSpeed(Double windSpeed) {
		this.windSpeed = windSpeed;
	}

	@JsonProperty("windDegree")
	public Integer getWindDegree() {
		return windDegree;
	}

	@JsonProperty("deg")
	public void setWindDegree(Integer windDegree) {
		this.windDegree = windDegree;
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
		return "Wind [speed=" + windSpeed + ", deg=" + windDegree + ", additionalProperties=" + additionalProperties
				+ "]";
	}

}
