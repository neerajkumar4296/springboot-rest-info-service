package com.weatherinfoservice.services;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
	
	public List<CountrySummary> coronaCasesByCount(){
		List<CountrySummary> countrySummaries= getCoronaCasesSummaryFromService().getCountrySummaries();
		
		return countrySummaries.parallelStream()
		.sorted(Comparator.comparingInt(CountrySummary::getTotalConfirmed).reversed())
		.collect(Collectors.toList());
	}
	
	public int totalNewlyConfirmed(){
		List<CountrySummary> countrySummaries= getCoronaCasesSummaryFromService().getCountrySummaries();
		
		return 
		      countrySummaries.parallelStream()
		                      .mapToInt(CountrySummary::getNewConfirmed)
		                      .sum();
	}

}
