package com.weatherinfoservice.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.weatherinfoservice.ApplicationsUtiltyController;
import com.weatherinfoservice.db.repositories.EmployeeRepository;
import com.weatherinfoservice.exceptions.BadServiceRequestException;
import com.weatherinfoservice.model.Address;
import com.weatherinfoservice.model.AddressType;
import com.weatherinfoservice.model.Employee;
import com.weatherinfoservice.model.Qualification;

@Repository
public class DbUtil {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationsUtiltyController.class);
	
	@Autowired
	EmployeeRepository empRepository;
	
		public void saveEmployeeDetails() {
		
			/*
			 * Address addresstemp= new Address("B155/303", "Sukhobrishti, Shapoorji",
			 * "Kolkata", "West Bengal", "India", 700135, AddressType.TEMPORARY); Address
			 * addressperm= new Address("141", "Station Road,", "Khagaria", "Bihar",
			 * "India", 851204, AddressType.HOME); List<Address> addressesNeeraj = new
			 * ArrayList<>(); addressesNeeraj.add(addresstemp);
			 * addressesNeeraj.add(addressperm);
			 * 
			 * Address addresstempO= new Address("B155/303", "Sukhobrishti, Shapoorji",
			 * "Kolkata", "West Bengal", "India", 700135, AddressType.TEMPORARY); Address
			 * addressPerm= new Address("58", "Thana Chowk", "Samastipur", "Bihar", "India",
			 * 848114, AddressType.HOME); Address addressOffice= new Address("ODC4D3",
			 * "Gitanjali Park", "Newtown", "West Bengal", "India", 700156,
			 * AddressType.OFFICE); List<Address> addressesOther= new ArrayList<>();
			 * addressesOther.add(addresstempO); addressesOther.add(addressOffice);
			 * addressesOther.add(addressPerm); Employee empNeeraj= new Employee(986845L,
			 * "Neeraj", "Kumar", LocalDate.of(1995, 12, 31), 50000L,
			 * Qualification.GRADUATE, addressesNeeraj);
			 * 
			 * Employee empOther= new Employee(1073214L, "Aditya", "Gupta",
			 * LocalDate.of(1992, 03, 24), 60000L, Qualification.GRADUATE, addressesOther);
			 */
		
			logger.error("dummy save:: sample data is already in DB...please check code for new insertion");

//				SessionFactory sessionFactory= new Configuration().configure().buildSessionFactory();
//				Session session = sessionFactory.openSession();
//				session.beginTransaction();
//				session.save(empNeeraj);
//				session.getTransaction().commit();
//			    session.close();
		
				//empRepository.saveAll(Arrays.asList(empNeeraj, empOther));
				//empRepository.save(empOther);
	}
		
		public List<Employee> fetchAllEmployees(){
			return empRepository.findAll();
		}
		
		public List<Employee> fetchEmployeeWithOfficeAddress(){
			List<Employee> employees= empRepository.findAll();
			// gives the list of employee matching the condition provided       
			return employees.stream().filter(emp->emp.getAddresses().stream().anyMatch(address->AddressType.OFFICE.equals(address.getAddressType())))
					.collect(Collectors.toList());
		}
		
		public List<Address> fetchAllAddresses(){
			List<Employee> employees= empRepository.findAll();
			// gives the list of Addresses       
			return employees.stream().map(Employee::getAddresses)
					.flatMap(Collection::stream)
					.collect(Collectors.toList());
					
		}
		
		public List<Address> fetchEmployeeAddresses(Long employeeId ){
			return empRepository.findById(employeeId)
					.orElseThrow(()-> new BadServiceRequestException("No Employee Found with the Provided employee Id....hence no Address"))
					.getAddresses();   
			 
					
		}
		
		public Employee fetchEmployeewithId(Long employeeId ){
			return empRepository.findById(employeeId)
					.orElseThrow(()-> new BadServiceRequestException("No Employee Found with the Provided employee Id"));
		}

}
