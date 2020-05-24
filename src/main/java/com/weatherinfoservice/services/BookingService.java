package com.weatherinfoservice.services;

import java.time.LocalDateTime;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.weatherinfoservice.db.repositories.BookingRepository;
import com.weatherinfoservice.exceptions.BadServiceRequestException;
import com.weatherinfoservice.exceptions.MyAppException;
import com.weatherinfoservice.model.Booking;
import com.weatherinfoservice.model.Passenger;

@Service
public class BookingService {

	private static final Logger logger = LoggerFactory.getLogger(BookingService.class);
	private static final String SAVING_BOOKING_ERROR = "Some Error occured in Saving the Booking...Please check the input Request";
	private static final String BOOKING_SUCCESS_MESSAGE = "Booking Successful....Please keep the Booking Id for future reference";

//	@Autowired
//	ServicesDao servicedao;
	
	@Autowired
	BookingRepository bookingRepository;

	public Booking generateAndSaveBooking(Passenger passenger) throws Exception {
		Booking booking = new Booking();
		String bookingNumber = generateBookingNumber(passenger);
		try {
			if (!StringUtils.isEmpty(bookingNumber)) {
				booking.setBookingId(bookingNumber);
				booking.setBookingTime(LocalDateTime.now());
				booking.setMessage(BOOKING_SUCCESS_MESSAGE);
				booking.setPassenger(passenger);
				bookingRepository.save(booking);
				logger.info("Booking Saved Successfully for Passenger:: " +passenger);
			}
		} catch (Exception e) {
			throw new MyAppException(SAVING_BOOKING_ERROR);
		}
		return booking;

	}

	private String generateBookingNumber(Passenger passenger) throws Exception {

		logger.info("Generating booking number for Passenger:: " + passenger);
		StringBuilder bookingNumberBuilder = new StringBuilder();
		bookingNumberBuilder.append(passenger.getFirstName().substring(0, 2).toUpperCase())
				.append(passenger.getMobileNo().substring(0, 2))
				.append(passenger.getSource().substring(0, 2).toUpperCase()).append(passenger.getNoOfTicket())
				.append(String.valueOf(new Random().nextInt(10)))
				.append(passenger.getDestination().substring(0, 2).toUpperCase());
		logger.info("Booking number generated :: " + bookingNumberBuilder.toString());
		return bookingNumberBuilder.toString();
	}
	
	public Booking retrieveBooking(String bookingId) {
		return bookingRepository.findById(bookingId).orElseThrow(()-> new BadServiceRequestException("Invalid Booking ID Provided!"));
	}
	
	public Booking retrieveLostBooking(String mobileNumber, String firstName) {
	String passengerFirstName= firstName.substring(0, 1).toUpperCase()+ firstName.substring(1);
		return bookingRepository.findBookingByMobileAndPassengerName(mobileNumber, passengerFirstName)
				                .orElseThrow(()-> new BadServiceRequestException("No Booking Found Matching the Provided details"));
	}

}
