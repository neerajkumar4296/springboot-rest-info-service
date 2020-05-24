package com.weatherinfoservice.repositories;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.weatherinfoservice.model.CoronaCasesSummary;
import com.weatherinfoservice.model.WeatherReport;

@Repository
public class RestServiceRepository {
	
	private static final Logger logger = LoggerFactory.getLogger(RestServiceRepository.class);

		
	@Value("${services.openweather.basicuri: }")
	private String weatherServiceBasicUri;

	@Value("${services.openweather.responseunitmetric}")
	private String responseUnit;

	@Value("${services.openweather.responsemodehtml}")
	private String responseMode;

	@Value("${services.openweather.apiparam}")
	private String apiParam;
	
	@Value("${services.restcountries.basicuri}")
	private String countryServiceBasicUri;
	
	@Value("${services.coronaSummaryService.basicuri}")
	private String coronaSummaryService;
	
	@Autowired
	@Qualifier("simpleRestTemplate")
	RestTemplate restTemplate;
	
	
	

	
	public String getWeatherServicApiUrl(String location) {
		StringBuilder apiUrlBuilder = new StringBuilder();
		logger.info("getWeatherServicApiUrl called for location:: " + location);
		return apiUrlBuilder.append(weatherServiceBasicUri).append(location).append(responseUnit).append(apiParam)
				.toString();

	}
	
	public WeatherReport getWeatherReport(String location) {
		logger.info("making api request to service endpoint url for weather report::");
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
		ResponseEntity<WeatherReport> responseEntity = restTemplate.exchange(getWeatherServicApiUrl(location),
				HttpMethod.GET, httpEntity, WeatherReport.class);
		return responseEntity.getBody();
	}
	
	public String getCountryInfo(String countryName) {
		logger.info("making api request to service endpoint url for country info report::");
		String countryInfo=restTemplate.getForObject(countryServiceBasicUri, String.class, countryName);
		logger.info("country info fetched successfully:: " +countryInfo);
		return countryInfo;
	}
	
	public CoronaCasesSummary getCoronaCasesSummary() {
		logger.info("making api request to service endpoint url for corona summary report::");
		//String coronaCasesSummary=restTemplate.getForObject(coronaSummaryService, String.class);
		 CoronaCasesSummary coronaCasesSummary= restTemplate.getForObject(coronaSummaryService, CoronaCasesSummary.class);
		logger.info("corona summary fetched successfully:: " +coronaCasesSummary);
		return coronaCasesSummary;
	}
	
	

}
