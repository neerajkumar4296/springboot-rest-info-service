package com.weatherinfoservice.repositories;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.weatherinfoservice.model.WeatherReport;
import com.weatherinfoservice.services.EmailService;

@Repository
public class EmailServiceRepository {
	
	private static final Logger logger = LoggerFactory.getLogger(EmailServiceRepository.class);

	
	@Value("${hometown.emailaddresses}")
	private String emailAddresses;
	
	@Autowired
	EmailService emailService;
	
	public void sendEmailRequest(WeatherReport weatherReport) {
		List<String> emailAddressesMasked=Arrays.asList(emailAddresses.split(",")).stream().
		map(emailAddress-> emailAddress.replaceAll("(?<=.{4}).(?=[^@]*?@)", "*")).collect(Collectors.toList());
		emailService.sendEmail(emailAddresses, weatherReport);
		logger.info("email report sent successfully to recipients:: " +emailAddressesMasked);
	}

}
