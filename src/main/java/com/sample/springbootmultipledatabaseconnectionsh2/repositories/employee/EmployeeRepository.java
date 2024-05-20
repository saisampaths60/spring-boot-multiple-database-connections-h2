package com.sample.springbootmultipledatabaseconnectionsh2.repositories.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.springbootmultipledatabaseconnectionsh2.entities.employee.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Employee findByEmail(String email);

}