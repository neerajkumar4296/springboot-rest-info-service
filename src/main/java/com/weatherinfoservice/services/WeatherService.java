package com.weatherinfoservice.services;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.weatherinfoservice.model.WeatherReport;
import com.weatherinfoservice.repositories.EmailServiceRepository;
import com.weatherinfoservice.repositories.RestServiceRepository;
import com.weatherinfoservice.util.WeatherInfoUtil;

@Service
public class WeatherService {

	private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);
	
	@Value("${hometown.location}")
	private String hometown;

	@Autowired
	EmailServiceRepository emailServiceRepository;

	@Autowired
	RestServiceRepository openWeatherRestRepository;

	public String getWeatherServicApiEndPoint(String location) {
		return openWeatherRestRepository.getWeatherServicApiUrl(location);
	}

	public WeatherReport getCityWeatherReport(String location) throws MessagingException {
		WeatherReport weatherReport = openWeatherRestRepository.getWeatherReport(location);
		logger.error("weather report fetched successfully:: " + weatherReport);
		if (hometown.equalsIgnoreCase(location)) {
			logger.info("hometown location....initiating email request");
			emailServiceRepository.sendEmailRequest(weatherReport);
			logger.info("weather report sent successfully");
			return weatherReport;
		}
		return weatherReport;
	}

	public String getWeatherReportAsFormattedString(String location) {
		WeatherInfoUtil weatherInfoUtil= new WeatherInfoUtil();
		logger.error("getWeatherReportAsFormattedString called for location:: " + location);
		WeatherReport weatherReport = openWeatherRestRepository.getWeatherReport(location);
		String formattedResponseString=weatherInfoUtil.textResponseFormatter(weatherReport);
		logger.error("returned formattedResponseString:: " +formattedResponseString);
		return formattedResponseString;

	}

}
