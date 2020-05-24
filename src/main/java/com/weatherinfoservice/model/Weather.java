package com.weatherinfoservice.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Weather {

	
	private Integer weatherId;

	private String weatherType;

	private String weatherDesc;

	
	private String weatherIcon;

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public Weather() {
		super();
	}
	@JsonProperty("weatherId")
	public Integer getWeatherId() {
		return weatherId;
	}
	@JsonProperty("id")
	public void setWeatherId(Integer id) {
		this.weatherId = id;
	}
    @JsonProperty("weatherType")
	public String getWeatherType() {
		return weatherType;
	}
    @JsonProperty("main")
	public void setWeatherType(String weatherType) {
		this.weatherType = weatherType;
	}
	@JsonProperty("weatherDesc")
	public String getWeatherDesc() {
		return weatherDesc;
	}
	@JsonProperty("description")
	public void setWeatherDesc(String weatherDesc) {
		this.weatherDesc = weatherDesc;
	}
	@JsonProperty("weatherIcon")
	public String getWeatherIcon() {
		return weatherIcon;
	}
	@JsonProperty("icon")
	public void setWeatherIcon(String icon) {
		this.weatherIcon = icon;
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
		return "Weather [id=" + weatherId + ", main=" + weatherType + ", description=" + weatherDesc + ", icon=" + weatherIcon
				+ ", additionalProperties=" + additionalProperties + "]";
	}

    

}
