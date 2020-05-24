package com.weatherinfoservice.model;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeatherReport {

	private Coordinates coordinates;

	private List<Weather> weather;

	//private String weatherBase;

	private WeatherInfo weatherInfo;

	private String visibility;

	private Wind wind;

	//private Clouds clouds;

	private long reportDate;

	private SystemDetails systemDetails;

	private Integer timezone;

	private Integer cityId;

	private String cityName;

	private Integer responseCode;
	

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public WeatherReport() {
		super();
	}

	@JsonProperty("coordinates")
	public Coordinates getCoordinates() {
		return coordinates;
	}

	@JsonProperty("coord")
	public void setCoordinates(Coordinates coord) {
		this.coordinates = coord;
	}

	@JsonProperty("weather")
	public List<Weather> getWeather() {
		return weather;
	}

	@JsonProperty("weather")
	public void setWeather(List<Weather> weather) {
		this.weather = weather;
	}

//	@JsonProperty("weatherBase")
//	public String getWeatherBase() {
//		return weatherBase;
//	}
//
//	@JsonProperty("base")
//	public void setWeatherBase(String base) {
//		this.weatherBase = base;
//	}

	@JsonProperty("weatherInfo")
	public WeatherInfo getWeatherInfo() {
		return weatherInfo;
	}

	@JsonProperty("main")
	public void setWeatherInfo(WeatherInfo main) {
		this.weatherInfo = main;
	}

	@JsonProperty("visibility")
	public String getVisibility() {
		return visibility;
	}

	@JsonProperty("visibility")
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	
	@JsonProperty("wind")
	public Wind getWind() {
		return wind;
	}

	@JsonProperty("wind")
	public void setWind(Wind wind) {
		this.wind = wind;
	}

	/*
	 * @JsonProperty("clouds") public Clouds getClouds() { return clouds; }
	 * 
	 * @JsonProperty("clouds") public void setClouds(Clouds clouds) { this.clouds =
	 * clouds; }
	 */

	@JsonProperty("reportDate")
	public LocalDateTime getReportDate() {
		return LocalDateTime.ofEpochSecond(reportDate, 0, ZoneOffset.ofHoursMinutes(5, 30));
	}

	@JsonProperty("dt")
	public void setReportDate(long dt) {
		this.reportDate = dt;
	}

	@JsonProperty("systemDetails")
	public SystemDetails getSystemDetails() {
		return systemDetails;
	}

	@JsonProperty("sys")
	public void setSystemDetails(SystemDetails sys) {
		this.systemDetails = sys;
	}

	@JsonProperty("timezone")
	public Integer getTimezone() {
		return timezone;
	}

	@JsonProperty("timezone")
	public void setTimezone(Integer timezone) {
		this.timezone = timezone;
	}

	@JsonProperty("cityId")
	public Integer getCityId() {
		return cityId;
	}

	@JsonProperty("id")
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	@JsonProperty("cityName")
	public String getCityName() {
		return cityName;
	}

	@JsonProperty("name")
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@JsonProperty("responseCode")
	public Integer getResponseCode() {
		return responseCode;
	}

	@JsonProperty("cod")
	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}

	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}

	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}

	@Override
	public String toString() {
		return "WeatherReport [coordinates=" + coordinates + ", weather=" + weather
				+ ", weatherInfo=" + weatherInfo + ", visibility=" + visibility + ", wind=" + wind
				+ ", reportDate=" + reportDate + ", systemDetails=" + systemDetails + ", timezone=" + timezone
				+ ", cityId=" + cityId + ", cityName=" + cityName + ", responseCode=" + responseCode
				+ ", additionalProperties=" + additionalProperties + "]";
	}

	

}
