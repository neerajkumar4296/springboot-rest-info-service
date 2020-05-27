package com.weatherinfoservice.util;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.weatherinfoservice.model.WeatherReport;

public class WeatherInfoUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(WeatherInfoUtil.class);
	private static final String NO_VISIBiLITY_DATA_MESSAGE = "visibility data not available for this location";
	
	public String textResponseFormatter( WeatherReport weatherReport) {
		logger.info("textResponseFormatter called..:: formatting response");
		return "Weather report for " + weatherReport.getCityName() + "(" + weatherReport.getSystemDetails().getCountry()+ ")" + " generated on:: " + weatherReport.getReportDate() 
				+ "\ncity name:: "+ weatherReport.getCityName() + ", "
				+ "coordinates:: " + " longitude:: "+ weatherReport.getCoordinates().getLongitude() + " latitude:: "+ weatherReport.getCoordinates().getLatitude() 
				+ "\nweather type:: "+ weatherReport.getWeather().stream().map(weather -> weather.getWeatherDesc()).findFirst().get()
				+ "\ntemperature:: " + weatherReport.getWeatherInfo().getTemperature() + "\u2103"
				+ "\nmin_temperature:: "+ weatherReport.getWeatherInfo().getMinTemperature() + "\u2103" 
				+ "\nmax_temperature:: "+ weatherReport.getWeatherInfo().getMaxTemperature() + "\u2103" 
				+ "\nactual_feel:: "+ weatherReport.getWeatherInfo().getActualFeel() + "\u2103" 
				+ "\nvisibility:: "+getVisibilityFromReport(weatherReport.getVisibility())   
				+ "\nwind speed:: " + weatherReport.getWind().getWindSpeed()+ " km/h" 
				+ "\nsunrise time:: " + weatherReport.getSystemDetails().getSunriseTime() 
				+ "\nsunset Time:: "+ weatherReport.getSystemDetails().getSunsetTime();
	}
	
	public static String getVisibilityFromReport(String visibility) {
		return Optional.ofNullable(visibility)
				.map(visibilityData-> visibilityData.trim()+ " m")
				.orElse(NO_VISIBiLITY_DATA_MESSAGE);
	}

}
