package com.weatherinfoservice;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.weatherinfoservice.delegate.ApplicationDelegate;
import com.weatherinfoservice.exceptions.BadServiceRequestException;
import com.weatherinfoservice.model.Address;
import com.weatherinfoservice.model.CoronaCasesSummary;
import com.weatherinfoservice.model.CountrySummary;
import com.weatherinfoservice.model.DivisibilityInputRequest;
import com.weatherinfoservice.model.Employee;
import com.weatherinfoservice.model.MathsInputRequest;
import com.weatherinfoservice.services.MathsOperationService;
import com.weatherinfoservice.util.ApplicationUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping(value = "utilities")
@CrossOrigin(origins = {"http://localhost:3000"})
public class ApplicationsUtiltyController {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationsUtiltyController.class);
	protected static final String CORONA_SUMMARY_JSON_VALUE= "application/vnd.CoronaCasesSummary.v1+json";
	protected static final String CORONA_COUNTRY_SUMMARY_JSON_VALUE= "application/vnd.CountrySummary.v1+json";
	
	@Autowired
	MathsOperationService mathsOperationService;
	
	@Autowired
	ApplicationDelegate applicationDelegate;
	
	

	@GetMapping(value = "/test")
	public String testController() {
		logger.info("Tested " + this.getClass().getSimpleName() + " OK!");
		return this.getUserName() + " Tested " + this.getClass().getSimpleName() + " OK!";
	}

	@ApiOperation(value = "Provides locale value of the input attribute in the language code requested")
	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(value = "/locale/value", method = RequestMethod.GET)
	public String getLocalisedContentBasedOnLanguage(
			@RequestParam(name = "attributename") String attributeName,
			@RequestParam(name = "languagecode") Optional<String> languageCode) {
		System.out.println(" attr:: " + attributeName + " lang:: " + languageCode);
		return applicationDelegate.getLocalisedResponse(attributeName, languageCode);
	}

	@ApiResponses(value = { @ApiResponse(code = 400, message = "Please Provide the Required Input"),
	@ApiResponse(code = 501, message = "Operation Type (is) not implemented yet") })
	@ApiOperation(value = "Peform some Maths operation by providng the input")
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping(value = "/perform", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String performOperations(@RequestBody @Valid MathsInputRequest inputRequest, @ApiIgnore Errors errors)
			throws Exception {
		if (errors.hasErrors()) {
			List<String> errorList = errors.getFieldErrors().stream().map(error -> error.getDefaultMessage())
					.collect(Collectors.toList());
			logger.error("validation failed for input request:: " + errorList.toString());
			throw new BadServiceRequestException(errorList.toString());
		}
		logger.info("Input Request body:: " + inputRequest);
		return mathsOperationService.showOperationResult(mathsOperationService.perform(inputRequest),
				inputRequest.getOperationType());

	}

	@ApiResponses(value = { @ApiResponse(code = 400, message = "Please Provide the Required Input") })
	@ApiOperation(value = "Generate OTP of desired length...default length (8)")
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(value = "generate/otp")
	public String getOTP(@RequestParam(value = "otplength", defaultValue = "8", required = false) String lengthOfOTP) {
		logger.info("getOTP called...");
		return "OTP Generated Successfully:: " + ApplicationUtil.generateOTP(Integer.parseInt(lengthOfOTP.trim()));
	}

	@ApiResponses(value = { @ApiResponse(code = 400, message = "invalid input numbers provided") })
	@ApiOperation(value = "Generates numbers divisible by the 2 specified numbers for the dividend provided")
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(value = "print/printnumbersdivisibleby")
	public String getNumbersDivisibleBY(@RequestBody @Valid DivisibilityInputRequest divisibilityInputRequest,
			@ApiIgnore Errors errors) {
		if (errors.hasErrors()) {
			List<String> errorList = errors.getFieldErrors().stream().map(error -> error.getDefaultMessage())
					.collect(Collectors.toList());
			logger.error("validation failed for input request:: " + errorList.toString());
			throw new BadServiceRequestException(errorList.toString());
		}
		logger.info("getNumbersDivisibleBY called...");
		return ApplicationUtil.getNumbersDivisibleBy(divisibilityInputRequest.getFirstDivisor(),
				divisibilityInputRequest.getSecondDivisor(), divisibilityInputRequest.getDividend());
	}
	
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Input File Provide is Blank...Kindly Check") })
	@ApiOperation(value = "Analyzes the content of the text file uploaded using this utility service")
	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(value = "file/analyzefile", method = RequestMethod.POST)
	public String readTheNumberOfLinesFromAFile(@RequestParam(required = true, value = "file") MultipartFile inputFile) throws IOException {
		logger.info("Input file name:: " + inputFile.getOriginalFilename());
		logger.info("File Content Type:: " +inputFile.getContentType());
	     return applicationDelegate.analyzeContentInTheFile(inputFile);

	}
	
	@ApiResponses(value = { @ApiResponse(code = 400, message = "number exceeds the supported range") })
	@ApiOperation(value = "Converts an Integer value to its equivalent Roman Numeral")
	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(value = "toRomanNumeral/{number}", method = RequestMethod.GET)
	public String integerToRomanLiteral(@PathVariable(name = "number") Integer inputNumber ) {
		logger.info("integerToRomanLiteral method called in " +this.getClass().getSimpleName()+ " for input number:: " +inputNumber);
	    return ApplicationUtil.intToRoman(inputNumber);
	}
	
	@ApiOperation(value = "Corona Virus cases summary for the whole world/ global stats along with country name provided")
	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(value = "coronaCases/summary", method = RequestMethod.GET, produces=CORONA_SUMMARY_JSON_VALUE)
	public CoronaCasesSummary coronaVirusSummary(@RequestParam Optional<String> countryName) {
		logger.info("coronaVirusSummary method called in " +this.getClass().getSimpleName());
	    return applicationDelegate.getCoronaInfo(countryName);
	}
	
	@ApiOperation(value = "Corona Virus cases summary for the Country Name Provided")
	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(value = "coronaCases/summary/{countryName}", method = RequestMethod.GET, produces=CORONA_COUNTRY_SUMMARY_JSON_VALUE)
	public CountrySummary coronaVirusSummaryForCountry(@PathVariable String countryName) {
		logger.info("coronaVirusSummary method called in " +this.getClass().getSimpleName());
	    return applicationDelegate.getCoronaInfoForCountry(countryName);
	}
	
	@ApiOperation(value = "Corona Virus cases summary sorted by the total confirmed cases")
	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(value = "coronaCases/summary/sorted", method = RequestMethod.GET, produces=CORONA_COUNTRY_SUMMARY_JSON_VALUE)
	public List<CountrySummary> summarySortedByTotalConfirmedCases() {
		logger.info("summarySortedByTotalConfirmedCases method called in " +this.getClass().getSimpleName());
	    return applicationDelegate.getCoronaInfoByCount();
	}
	
	@ApiOperation(value = "Corona Virus total newly confirmed cases")
	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(value = "coronaCases/newlyConfirmed", method = RequestMethod.GET)
	public int totalNewlyConfirmedCases() {
		logger.info("totalNewlyConfirmedCases method called in " +this.getClass().getSimpleName());
	    return applicationDelegate.getTotalNewConfirmedCases();
	}
	
	@GetMapping(value = "employees/save")
	public void saveEmployeeDetails()  {
		//DbUtil dbUtil= new DbUtil();
		applicationDelegate.saveEmployees();
	}
	
	@GetMapping(value = "employees/findAll")
	public List<Employee> allEmployeeDetails()  {
		//DbUtil dbUtil= new DbUtil();
	    return applicationDelegate.findAllEmployees();
	}
	
	@GetMapping(value = "employees/withHomeAddress")
	public List<Employee> employeesHavingOfficeAddresss()  {
		//DbUtil dbUtil= new DbUtil();
	    return applicationDelegate.employeesWithOfficeAddress();
	}
	
	@GetMapping(value = "employees/allAddresses")
	public List<Address> allEmployeesAddress()  {
		//DbUtil dbUtil= new DbUtil();
	return applicationDelegate.employeeAddresses();
	}
	
	@GetMapping(value = "employees/employee/{employeeId}/addresses")
	public List<Address> employeeAddresses(@PathVariable Long employeeId)  {
	return applicationDelegate.employeeAddressesById(employeeId);
	}
	
	@GetMapping(value = "employees/employee/{employeeId}")
	public Employee employeeDetails(@PathVariable Long employeeId)  {
	return applicationDelegate.employeeDetailsById(employeeId);
	}

	private String getUserName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}

}
