package com.weatherinfoservice.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weatherinfoservice.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
