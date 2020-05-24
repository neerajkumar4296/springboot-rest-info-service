package com.weatherinfoservice.services;

import java.util.Arrays;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weatherinfoservice.exceptions.BadServiceRequestException;
import com.weatherinfoservice.model.CoronaCasesSummary;
import com.weatherinfoservice.model.CountrySummary;
import com.weatherinfoservice.repositories.RestServiceRepository;

@Service
public class CoronaSummaryService {
	
	
	@Autowired
	RestServiceRepository countryInfoRestRepository;
	
	public CoronaCasesSummary getCoronaCasesSummaryFromService() {
		return countryInfoRestRepository.getCoronaCasesSummary();
	}
	
	
	
	public CountrySummary getCoronaCasesSummaryForCountry(String countryName) {
		return 
				getCoronaCasesSummaryFromService().getCountrySummaries()
				.stream()
				.filter(countrySummary-> countrySummary.getCountryName().equalsIgnoreCase(countryName))
				.findFirst()
				.orElseThrow(()-> new BadServiceRequestException("invalid Country name was provided"));
	}

	public CoronaCasesSummary getCoronaCasesSummaryForCountryWithGlobe(String countryName) {
		CoronaCasesSummary coronaCasesSummaryResponse= new CoronaCasesSummary();
		CoronaCasesSummary coronaCasesSummary= getCoronaCasesSummaryFromService();
		
		coronaCasesSummaryResponse.setGlobalSummary(coronaCasesSummary.getGlobalSummary());
		coronaCasesSummaryResponse.setCountrySummaries(
				Arrays.asList(
						coronaCasesSummary.getCountrySummaries()
						.stream()
						.filter(countrySummary-> countrySummary.getCountryName().equalsIgnoreCase(countryName))
						.findFirst()
						.orElseThrow(()-> new BadServiceRequestException("invalid Country name was provided"))));
		
		coronaCasesSummaryResponse.setLocalDateTime(coronaCasesSummary.getLocalDateTime());
		
		return coronaCasesSummaryResponse;
	}

}
