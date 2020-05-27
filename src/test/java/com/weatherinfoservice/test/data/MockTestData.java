package com.weatherinfoservice.test.data;

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
	
	public static final String countryInfoData=
			"name India\r\n" + 
			"topLevelDomain\r\n" + 
			".in\r\n" + 
			"alpha2Code IN\r\n" + 
			"alpha3Code IND\r\n" + 
			"callingCodes\r\n" + 
			"91\r\n" + 
			"capital New Delhi\r\n" + 
			"altSpellings\r\n" + 
			"IN\r\n" + 
			"Bhārat\r\n" + 
			"Republic of India\r\n" + 
			"Bharat Ganrajya\r\n" + 
			"region Asia\r\n" + 
			"subregion Southern Asia\r\n" + 
			"population 1295210000\r\n" + 
			"latlng\r\n" + 
			"20\r\n" + 
			"77\r\n" + 
			"demonym Indian\r\n" + 
			"area 3287590\r\n" + 
			"gini 33.4\r\n" + 
			"timezones\r\n" + 
			"UTC+05:30\r\n" + 
			"borders\r\n" + 
			"AFG\r\n" + 
			"BGD\r\n" + 
			"BTN\r\n" + 
			"MMR\r\n" + 
			"CHN\r\n" + 
			"NPL\r\n" + 
			"PAK\r\n" + 
			"LKA\r\n" + 
			"nativeName भारत\r\n" + 
			"numericCode 356\r\n" + 
			"currencies\r\n" + 
			"code INR\r\n" + 
			"name Indian rupee\r\n" + 
			"symbol ₹\r\n" + 
			"languages\r\n" + 
			"iso639_1 hi\r\n" + 
			"iso639_2 hin\r\n" + 
			"name Hindi\r\n" + 
			"nativeName हिन्दी\r\n" + 
			"iso639_1 en\r\n" + 
			"iso639_2 eng\r\n" + 
			"name English\r\n" + 
			"nativeName English\r\n" + 
			"translations\r\n" + 
			"de Indien\r\n" + 
			"es India\r\n" + 
			"fr Inde\r\n" + 
			"ja インド\r\n" + 
			"it India\r\n" + 
			"br Índia\r\n" + 
			"pt Índia\r\n" + 
			"nl India\r\n" + 
			"hr Indija\r\n" + 
			"fa هند\r\n" + 
			"flag https://restcountries.eu/data/ind.svg\r\n" + 
			"regionalBlocs\r\n" + 
			"acronym SAARC\r\n" + 
			"name South Asian Association for Regional Cooperation\r\n" + 
			"otherAcronyms\r\n" + 
			"otherNames\r\n" + 
			"cioc IND\r\n" + 
			"";
	
	
	public static WeatherReport getMockWeatherReport() throws JsonMappingException, JsonProcessingException {
		ObjectMapper jsonMapper= new ObjectMapper();
		return jsonMapper.readValue(weatherReportJsonAsString, WeatherReport.class);
	}
	
	public static String getMockWeatherDataAsFormattedString() {
		return weatherReportsFormattedString;
	}

	public static String getCountryInfoMockData() {
		return countryInfoData;
	}

}
