package com.weatherinfoservice.db.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.weatherinfoservice.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, String> {
	
	@Query(value="SELECT * FROM BOOKING WHERE MOBILENO=?1 and FIRSTNAME=?2", nativeQuery = true)
	public Optional<Booking> findBookingByMobileAndPassengerName(String mobileNo, String passengerFirstName);

}
