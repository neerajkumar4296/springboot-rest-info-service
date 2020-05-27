package com.weatherinfoservice;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.weatherinfoservice.delegate.ApplicationDelegate;
import com.weatherinfoservice.model.WeatherReport;
import com.weatherinfoservice.test.data.MockTestData;


@WebMvcTest(value = WeatherInfoController.class)
@WithMockUser(username = "appadmin", password = "appadminpass", roles = {"USER", "ADMIN"})
public class WeatherInfoControllerTest {
	
	   @Autowired
	   private MockMvc mockMvc;
	   
  
	   @MockBean
	   private ApplicationDelegate applicationDelegate;
	   
	   private static WeatherReport weatherReport;
	   
	   private static final String LOCATION= "Kolkata";
	   
	   private static String weatherReportAsFormmattedString;
	   
	   @BeforeAll
	   static void initializeData() throws JsonMappingException, JsonProcessingException {
		   weatherReport=MockTestData.getMockWeatherReport();
		   weatherReportAsFormmattedString=MockTestData.getMockWeatherDataAsFormattedString();
	   }
	   
	   @Test
	   @DisplayName("Test for WeatherInfoController")
	   public void testTestController() throws Exception
	   {   
		   this.mockMvc.perform(get("/weather/test"))
				   .andExpect(status().isOk());
	   }
	   
	   @Test
	   @DisplayName("Test for CityWeatherReport call(Json Response)")
	   //@Disabled
	   public void testCityWeatherReport() throws Exception
	   {
		   given(this.applicationDelegate.getCityWeatherInfo(LOCATION)).willReturn(weatherReport);
		   
		            this.mockMvc.perform(get("/weather/cityweatherreport/{location}", LOCATION)
				   .accept(WeatherInfoController.WEATHER_REPORT_JSON_VALUE))
		           .andExpect(status().isOk())
		           .andExpect(jsonPath("$.cityName", is(LOCATION)))
		           .andExpect(jsonPath("$.coordinates").isNotEmpty());
	   }
	   
	   @Test
	   @DisplayName("Test for CityWeatherReport call(Formatted String Response)")
	   public void testCityWeatherReportString() throws Exception
	   {
		   when(this.applicationDelegate.getCityWeatherInfoConcised(LOCATION)).thenReturn(weatherReportAsFormmattedString);
		   
		   
		            this.mockMvc.perform(get("/weather/cityweatherreport/{location}/concised", LOCATION))
		           .andExpect(MockMvcResultMatchers.status().isOk())
		           .andExpect(content().string(containsString(LOCATION)));
	   }
	   
	   

}
