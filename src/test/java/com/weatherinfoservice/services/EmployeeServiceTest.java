package com.weatherinfoservice.services;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

	@Test()
	@Disabled
	void test() {
		fail(()-> "Failed at:: "+LocalDateTime.now().toString());
		
	}

}
