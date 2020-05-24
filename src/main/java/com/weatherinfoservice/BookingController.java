package com.weatherinfoservice;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.weatherinfoservice.exceptions.BadServiceRequestException;
import com.weatherinfoservice.jsonviews.JsonResponseView;
import com.weatherinfoservice.model.Booking;
import com.weatherinfoservice.model.Passenger;
import com.weatherinfoservice.services.BookingService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping(value = "booking")
public class BookingController {
	
	private static final Logger logger = LoggerFactory.getLogger(BookingController.class);
	private static final String BOOKING_JSON_VALUE= "application/vnd.Booking.v1+json";

	
	@Autowired
	BookingService bookingService;
	
	@GetMapping(value = "/test")
	public String testController() {
		logger.info("Tested " +this.getClass().getSimpleName()+ " OK!\"");
		return this.getUserName()+" Tested " +this.getClass().getSimpleName()+ " OK!";
	}
	
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Please Provide the Required Input"), @ApiResponse(code = 201, message = "Successfully Created")  })
	@ApiOperation(value = "Mock Ticket booking service based on the Passenger details Provided")
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping(value = "/bookticket", produces = BOOKING_JSON_VALUE)
	@JsonView(JsonResponseView.Public.class)
	public Booking bookingRequest(@RequestBody @Valid Passenger passenger, @ApiIgnore Errors errors) throws Exception {
		
		if(errors.hasErrors()) {
			List<String> errorList= errors.getFieldErrors().stream().map(error-> error.getDefaultMessage()).collect(Collectors.toList());
			logger.error("validation failed for input request:: " +errorList.toString());
			throw new BadServiceRequestException(errorList.toString());
		}
		return bookingService.generateAndSaveBooking(passenger);
		
	}
	
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid Booking ID Provided!")})
	@ApiOperation(value = "Retrieve the booking by providing the the booking id")
	@GetMapping(value = "retrieve/{bookingId}", produces = BOOKING_JSON_VALUE)
	@JsonView(JsonResponseView.Private.class)
	public Booking retrieveBookingDetails(@PathVariable String bookingId) {
		return bookingService.retrieveBooking(bookingId);
	}
	
	@ApiResponses(value = { @ApiResponse(code = 400, message = "No Booking Found Matching the Provided details")})
	@ApiOperation(value = "Retrieve the Lost Booking details by providing the Mobile Number and Passenger First Name")
	@GetMapping(value = "retrieveLostBookingDetails/{mobileNumber}/{passengerFirstName}", produces = BOOKING_JSON_VALUE)
	public Booking retrieveLostBookingDetails(@PathVariable String mobileNumber, @PathVariable String passengerFirstName) {
		return bookingService.retrieveLostBooking(mobileNumber, passengerFirstName);
	}
	
	private String getUserName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	  }

}
