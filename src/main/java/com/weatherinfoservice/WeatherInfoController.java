package com.weatherinfoservice;

import java.util.Optional;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.weatherinfoservice.delegate.ApplicationDelegate;
import com.weatherinfoservice.model.WeatherReport;
import com.weatherinfoservice.services.WeatherService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "weather")
@Api(value = "weather-info-controller", description = "Rest Controller for Providing Weather related Information for a City/Place")
public class WeatherInfoController {

	private static final Logger logger = LoggerFactory.getLogger(WeatherInfoController.class);
	protected static final String WEATHER_REPORT_JSON_VALUE= "application/vnd.WeatherReport.v1+json";
	private static final String WEATHER_REPORT_XML_VALUE= "application/vnd.WeatherReport.v1+xml";

	@Autowired
	private ApplicationDelegate applicationDelegate;
	
	

	@ApiOperation(value = "Test the Weather Controller")
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String testController() {
		logger.info("Tested " +this.getClass().getSimpleName()+ " OK!");
		return this.getUserName()+" Tested " +this.getClass().getSimpleName()+ " OK!";
	}

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Weather Service uri successfully returned") })
	@ApiOperation(value = "Weather service provider API url...default location (Kolkata)")
	@RequestMapping(value = "/weatherserviceurl", method = RequestMethod.GET)
	public String weatherServiceurl(@RequestParam(defaultValue = "kolkata") Optional<String> location) {
		logger.info("getWeatherServiceurl called");
		return applicationDelegate.getServicApiEndPoint(location.get());
	}

	@ApiResponses(value = {@ApiResponse(code = 400, message = "Invalid City Name:: Please Make Sure You Spell the City Name Correctly"),
	@ApiResponse(code = 200, message = "Successfully fetched weather report") })
	@ApiOperation(value = "Weather information report for a given City:: all information")
	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(value = "/cityweatherreport/{location}", method = RequestMethod.GET, produces = WEATHER_REPORT_JSON_VALUE)
	public WeatherReport cityWeatherReport(@PathVariable String location) throws Exception {
		logger.info("getWeatherReport called");
		return applicationDelegate.getCityWeatherInfo(location);
	}

	@ApiResponses(value = {@ApiResponse(code = 400, message = "Invalid City Name:: Please Make Sure You Spell the City Name Correctly"),
	@ApiResponse(code = 200, message = "Successfully fetched weather report") })
	@ApiOperation(value = "Weather information report for a given City as formatted string (concised information)")
	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(value = "/cityweatherreport/{location}/concised", method = RequestMethod.GET )
	public String cityWeatherReportString(@PathVariable String location) {
		logger.info("getCityWeatherReportString in " + this.getClass().getSimpleName() + " called for location:: "
				+ location);
		return applicationDelegate.getCityWeatherInfoConcised(location);
	}
	
	@Scheduled(cron ="${weather-report-schedule.cronexpression}")
	public void cityWeatherReportToEmail() throws MessagingException {
		logger.info("cityWeatherReportToEmail in " + this.getClass().getSimpleName());
		applicationDelegate.getCityWeatherInfo("Khagaria");
	}
	
	
	private String getUserName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	  }
	
	

}
