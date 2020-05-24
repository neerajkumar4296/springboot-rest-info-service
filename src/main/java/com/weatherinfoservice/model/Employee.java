package com.weatherinfoservice.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "EMPLOYEES")
public class Employee {

	@Id
	@Column(name = "EMPLOYEE_ID")
	private Long employeeId;
	
	@Column(name = "FIRSTNAME", length = 25, nullable = false)
	private String firstName;
	
	@Column(name = "LASTNAME", length = 25)
	private String lastName;
	
	@Column(name = "DOB", nullable = false)
	//@Temporal(TemporalType.DATE)-- not usefull when using with java 8 time api
	@DateTimeFormat(iso = ISO.DATE, pattern = "^(1[0-2]|0[1-9])/(3[01]"+ "|[12][0-9]|0[1-9])/[0-9]{4}$")
	private LocalDate dob;
	
	@Column(name = "MAXM_QUALIFICATION", nullable = false)
	@Enumerated(EnumType.STRING)
	private Qualification maxEdQualification;

	
	@Column(name = "SALARY", nullable = false)
	private Long salary;
	
	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
	@JoinColumn(name = "EMPLOYEE_ID")
	private List<Address> addresses = new ArrayList<>();

	public Employee() {
		super();
	}

	public Employee(Long employeeId, String firstName, String lastName, LocalDate dob, Long salary,
			Qualification edQualification, List<Address> address) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.salary = salary;
		this.maxEdQualification = edQualification;
		this.addresses = address;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public Long getSalary() {
		return salary;
	}

	public void setSalary(Long salary) {
		this.salary = salary;
	}

	public Qualification getEdQualification() {
		return maxEdQualification;
	}

	public void setEdQualification(Qualification edQualification) {
		this.maxEdQualification = edQualification;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> address) {
		this.addresses = address;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName + ", dob="
				+ dob + ", maxEdQualification=" + maxEdQualification + ", salary=" + salary + ", address=" + addresses
				+ "]";
	}

	
}
