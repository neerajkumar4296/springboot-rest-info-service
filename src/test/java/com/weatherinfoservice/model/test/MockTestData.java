package com.weatherinfoservice.model.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weatherinfoservice.model.WeatherReport;

public class MockTestData {
	
	private static final String weatherReportJsonAsString=			
			"{\"coord\":{\"lon\":88.37,\"lat\":22.57},"
			+ "\"weather\":[{\"id\":721,\"main\":\"Haze\",\"description\":\"haze\",\"icon\":\"50d\"}],"
			+ "\"main\":{\"temp\":33,\"feels_like\":35.98,\"temp_min\":33,\"temp_max\":33,\"pressure\":1003,\"humidity\":79},\"visibility\":3600,"
			+ "\"wind\":{\"speed\":8.7,\"deg\":180},\"dt\":1590398180,"
			+ "\"sys\":{\"type\":1,\"id\":9114,\"country\":\"IN\",\"sunrise\":1590362581,\"sunset\":1590410655},"
			+ "\"timezone\":19800,\"id\":1275004,\"name\":\"Kolkata\",\"cod\":200}";
	private static final String weatherReportsFormattedString= "Weather report for Kolkata(IN) generated on:: 2020-04-02T23:40:22\r\n" + 
			"city name:: London, coordinates::  longitude:: -0.13 latitude:: 51.51\r\n" + 
			"weather type:: few clouds\r\n" + 
			"temperature:: 11℃\r\n" + 
			"min_temperature:: 10℃\r\n" + 
			"max_temperature:: 13℃\r\n" + 
			"actual_feel:: 6.11℃\r\n" + 
			"visibility:: 10000 m\r\n" + 
			"wind speed:: 5.7 km/h\r\n" + 
			"sunrise time:: 2020-04-02T11:02:37\r\n" + 
			"sunset Time:: 2020-04-03T00:05";
	
	public static WeatherReport getMockWeatherReport() throws JsonMappingException, JsonProcessingException {
		ObjectMapper jsonMapper= new ObjectMapper();
		return jsonMapper.readValue(weatherReportJsonAsString, WeatherReport.class);
	}
	
	public static String getMockWeatherDataAsFormattedString() {
		return weatherReportsFormattedString;
	}

}
