package com.weatherinfoservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.weatherinfoservice.delegate.ApplicationDelegate;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "countryInfo")
@Api(value = "country-info-controller", description = "Rest Controller for Providing Country Information for a Given Country")
public class CountryInfoController {

	private static final Logger logger = LoggerFactory.getLogger(CountryInfoController.class);
	
	@Autowired
	ApplicationDelegate applicationDelegate;

	@ApiOperation(value = "Test the Weather Controller")
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String testController() {
		logger.info("Tested " +this.getClass().getSimpleName()+ " OK!");
		return this.getUserName()+" Tested " +this.getClass().getSimpleName()+ " OK!";
	}

	@ApiResponses(value = {@ApiResponse(code = 400, message = "Invalid Country Name:: Please Make Sure You Spell the Country Name Correctly"),
	@ApiResponse(code = 200, message = "Successfully fetched weather report") })
	@ApiOperation(value = "Country information for a given Country:: all information")
	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(value = "/{countryName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String countryInfo(@PathVariable String countryName) throws Exception {
		logger.info("getWeatherReport called");
		return applicationDelegate.getCountryInfo(countryName);
	}
		
	private String getUserName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	  }
	
	

}
