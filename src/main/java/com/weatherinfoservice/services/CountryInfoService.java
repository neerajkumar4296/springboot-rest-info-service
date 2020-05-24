package com.weatherinfoservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weatherinfoservice.repositories.RestServiceRepository;

@Service
public class CountryInfoService {
	
	@Autowired
	RestServiceRepository countryInfoRestRepository;
	
	
	public String getCounrtyInfoFromService(String countryName) {
		return countryInfoRestRepository.getCountryInfo(countryName);
	}
	
	
}
