package com.weatherinfoservice.services;



import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import javax.mail.MessagingException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.weatherinfoservice.model.WeatherReport;
import com.weatherinfoservice.repositories.EmailServiceRepository;
import com.weatherinfoservice.repositories.RestServiceRepository;
import com.weatherinfoservice.test.data.MockTestData;
import com.weatherinfoservice.util.WeatherInfoUtil;

@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {
	
    @InjectMocks
	private  WeatherService weatherService;
	
	@Mock
	private RestServiceRepository openWeatherRestRepository;
	
	@Mock
	private EmailServiceRepository emailServiceRepository;
	

	private static final int RESPONSE_CODE_OK=200;
	
	private static WeatherReport weatherReport;
	
	//private static String weatherReportAsFormmattedString;
	
	
	private static final String LOCATION="Kolkata";
	private static final String COUNTRY_CODE="IN";
	private static final String SERVICEAPI_END_POINT_URI="http://api.openweathermap.org/data/2.5/weather?q=Kolkata&units=metric&APPID=64125ebb02bb7fd24ad526fe90e8827c";
	
	@BeforeEach
	void init(){
		ReflectionTestUtils.setField(weatherService, "hometown", LOCATION);
	}
	
	@BeforeAll
	   static void initializeData() throws JsonMappingException, JsonProcessingException {
		   weatherReport=MockTestData.getMockWeatherReport();
		   //weatherReportAsFormmattedString=MockTestData.getMockWeatherDataAsFormattedString();
	   }
	
	@Test
	@DisplayName("test for getting the weather service enpoint uri")
	public void test_getWeatherServicApiEndPoint() {
		when(this.openWeatherRestRepository.getWeatherServicApiUrl(LOCATION)).thenReturn(SERVICEAPI_END_POINT_URI);
		
		String apiEndPoint= weatherService.getWeatherServicApiEndPoint(LOCATION);
		assertThat(apiEndPoint, anything());
		assertThat(apiEndPoint, containsString("data"));
		//assertThat(apiEndPoint, is(not(apiEndPoint)));
		//assertThat(list, contains(first, second));
		assertThat(apiEndPoint, is(SERVICEAPI_END_POINT_URI));
	}
	
	@Test
	@DisplayName("test for getting city weather report by calling by API call")
	public void test_getCityWeatherReport() throws MessagingException {
		
		given(this.openWeatherRestRepository.getWeatherReport(LOCATION)).willReturn(weatherReport);
		
		WeatherReport weatherReportData= weatherService.getCityWeatherReport(LOCATION);
		
		assertThat(weatherReportData.getSystemDetails().getCountry(), is(COUNTRY_CODE));
		assertThat(weatherReportData.getCityName(), is(LOCATION));
		assertThat(weatherReportData.getResponseCode(), is(RESPONSE_CODE_OK));
	}
	
	@Test
	@DisplayName("test for getting city weather report as Concised formatted String by making API call")
	public void test_getCityWeatherReportFormattedString(){
		when(this.openWeatherRestRepository.getWeatherReport(LOCATION)).thenReturn(weatherReport);
		
		WeatherInfoUtil weatherInfoUtil= new WeatherInfoUtil();
		String weatherReportString= weatherService.getWeatherReportAsFormattedString(LOCATION);
		assertAll(
				()-> assertThat(weatherReportString, notNullValue()),
				()-> assertThat(weatherReportString, anything()),
				()-> assertThat(weatherReportString, containsString("temperature")),
				()-> assertThat(weatherReportString, containsString("visibility")),
				()-> assertEquals(weatherReportString, weatherInfoUtil.textResponseFormatter(weatherReport))
				);
		
		
	}

}
