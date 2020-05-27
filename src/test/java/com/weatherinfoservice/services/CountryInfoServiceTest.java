package com.weatherinfoservice.services;


import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.weatherinfoservice.model.OperationType;
import com.weatherinfoservice.repositories.RestServiceRepository;
import com.weatherinfoservice.test.data.MockTestData;

@ExtendWith(MockitoExtension.class)
class CountryInfoServiceTest {

	@InjectMocks
	CountryInfoService countryInfoService;
	
	@Mock
	RestServiceRepository countryInfoRestRepository;
	
	private static String countryInfoData;
	
	@BeforeAll
	static void initializeData() {
		countryInfoData=MockTestData.getCountryInfoMockData();
	}
	
	@ParameterizedTest
	@MethodSource("testCounrtyInfoFromServiceScenarioArguments")
	@DisplayName("test for getting the country info from service API call")
	void test_getCounrtyInfoFromService(String countryName) {
		when(this.countryInfoRestRepository.getCountryInfo(countryName)).thenReturn(countryInfoData);
		
		String countryInfodataString =countryInfoService.getCounrtyInfoFromService(countryName);
		assertThat(countryInfodataString, notNullValue());
		assertThat(countryInfodataString, anything());
		assertThat(countryInfodataString, containsString(".svg"));
		assertThat(countryInfodataString, containsString("timezones"));
		
	}
	
	 private static Stream<String> testCounrtyInfoFromServiceScenarioArguments(){ 
		 return Stream.of("India", "Pakistan", "China", "Panama");
			  }

}
