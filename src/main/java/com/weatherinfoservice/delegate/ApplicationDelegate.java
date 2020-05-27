package com.weatherinfoservice.delegate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.mail.MessagingException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.weatherinfoservice.exceptions.BadServiceRequestException;
import com.weatherinfoservice.model.Address;
import com.weatherinfoservice.model.CoronaCasesSummary;
import com.weatherinfoservice.model.CountrySummary;
import com.weatherinfoservice.model.Employee;
import com.weatherinfoservice.model.GlobalSummary;
import com.weatherinfoservice.model.WeatherReport;
import com.weatherinfoservice.services.CoronaSummaryService;
import com.weatherinfoservice.services.CountryInfoService;
import com.weatherinfoservice.services.EmployeeService;
import com.weatherinfoservice.services.WeatherService;

@Component
public class ApplicationDelegate {

	@Value(value = "${services.localeService.basicUri}")
	private String localeServiceBasicUri;

	@Autowired
	@Qualifier("loadBalancedRestTemplate")
	private RestTemplate restServiceTemplate;
	
	@Autowired
	EmployeeService employeeService;

	@Autowired
	private CountryInfoService countryInfoService;
	
	@Autowired
	private CoronaSummaryService coronaSummaryService;

	@Autowired
	WeatherService weatherService;

	public String getLocalisedResponse(String attributeName, Optional<String> languageCode) {

		String result = null;
		Map<String, String> pathParams = new HashMap<>();
		pathParams.put("attributename", attributeName);
		// pathParams.put("languagecode", languageCode.get());
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(localeServiceBasicUri);

		uriBuilder = languageCode.isPresent() ? uriBuilder.queryParam("languageCode", languageCode.get())
				: uriBuilder.queryParam("languageCode", StringUtils.EMPTY);

		URI uri = uriBuilder.buildAndExpand(pathParams).toUri();
		System.out.println("calling util service uri:: " + uri.toString());
		result = restServiceTemplate.getForObject(uri, String.class);
		System.out.println("result returned:: " + result);
		return result;

	}

	public String getCountryInfo(String countryName) {

		return countryInfoService.getCounrtyInfoFromService(countryName);

	}

	public CoronaCasesSummary getCoronaInfo(Optional<String> countryName) {
		if(countryName.isPresent()) {
			return coronaSummaryService.getCoronaCasesSummaryForCountryWithGlobe(countryName.get());
		}
		return coronaSummaryService.getCoronaCasesSummaryFromService();
	}
	
	public CountrySummary getCoronaInfoForCountry(String countryName) {

		return coronaSummaryService.getCoronaCasesSummaryForCountry(countryName);
	}

	public String analyzeContentInTheFile(MultipartFile inputFile) throws IOException {
		if (inputFile.isEmpty()) {
			throw new BadServiceRequestException("Input Text File Provided is Blank...Kindly Check");
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(inputFile.getInputStream()));
		// Optional<String> largestLine= reader.lines().sorted((str1, str2)->
		// str1.length()-str2.length()).findFirst();
		// List<String> filteredLines= reader.lines().filter( str1->
		// str1.startsWith("select")).collect(Collectors.toList());

		return reader.lines().max(Comparator.comparingInt(String::length)).get();

	}

	public WeatherReport getCityWeatherInfo(String location) throws MessagingException {
		return weatherService.getCityWeatherReport(location);
	}

	public String getCityWeatherInfoConcised(String location) {
		return weatherService.getWeatherReportAsFormattedString(location);
	}

	public String getServicApiEndPoint(String location) {
		return weatherService.getWeatherServicApiEndPoint(location);
	}
	
	public void saveEmployees() {
		employeeService.saveEmployeeDetails();
	}
	
	public List<Employee> findAllEmployees() {
		return employeeService.fetchAllEmployees();
	}
	
	public Employee employeeDetailsById(Long employeeId ) {
		return employeeService.fetchEmployeewithId(employeeId);
	}
	
	public List<Address> employeeAddresses() {
		return employeeService.fetchAllAddresses();
	}
	
	public List<Address> employeeAddressesById(Long employeeId) {
		return employeeService.fetchEmployeeAddresses(employeeId);
	}
	
	public List<Employee> employeesWithOfficeAddress() {
		return employeeService.fetchEmployeesWithOfficeAddress();
	}

}
