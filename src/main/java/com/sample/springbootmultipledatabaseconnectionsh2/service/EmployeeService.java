package com.sample.springbootmultipledatabaseconnectionsh2.service;

import com.sample.springbootmultipledatabaseconnectionsh2.dto.APIResponseDTO;
import com.sample.springbootmultipledatabaseconnectionsh2.dto.EmployeeDto;

public interface EmployeeService {

	EmployeeDto saveEmployee(EmployeeDto employeeDto);

	APIResponseDTO getEmployeeById(Long employeeId);

}