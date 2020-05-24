package com.weatherinfoservice.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeatherInfo {

	private Integer temperature;

	private Double actualFeel;

	private Integer minTemperature;

	private Integer maxTemperature;

	private Integer pressure;

	private Integer humidity;

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public WeatherInfo() {
		super();
	}

	@JsonProperty("temperature")
	public Integer getTemperature() {
		return temperature;
	}

	@JsonProperty("temp")
	public void setTemperature(Integer temperature) {
		this.temperature = temperature;
	}

	@JsonProperty("actualFeel")
	public Double getActualFeel() {
		return actualFeel;
	}

	@JsonProperty("feels_like")
	public void setActualFeel(Double actualFeel) {
		this.actualFeel = actualFeel;
	}

	@JsonProperty("minTemperature")
	public Integer getMinTemperature() {
		return minTemperature;
	}

	@JsonProperty("temp_min")
	public void setMinTemperature(Integer minTemperature) {
		this.minTemperature = minTemperature;
	}

	@JsonProperty("maxTemperature")
	public Integer getMaxTemperature() {
		return maxTemperature;
	}

	@JsonProperty("temp_max")
	public void setMaxTemperature(Integer maxTemperature) {
		this.maxTemperature = maxTemperature;
	}

	@JsonProperty("pressure")
	public Integer getPressure() {
		return pressure;
	}

	@JsonProperty("pressure")
	public void setPressure(Integer pressure) {
		this.pressure = pressure;
	}

	@JsonProperty("humidity")
	public Integer getHumidity() {
		return humidity;
	}

	@JsonProperty("humidity")
	public void setHumidity(Integer humidity) {
		this.humidity = humidity;
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
		return "WeatherInfo [temperature=" + temperature + ", actualFeel=" + actualFeel + ", minTemperature="
				+ minTemperature + ", maxTemperature=" + maxTemperature + ", pressure=" + pressure + ", humidity="
				+ humidity + ", additionalProperties=" + additionalProperties + "]";
	}

}
