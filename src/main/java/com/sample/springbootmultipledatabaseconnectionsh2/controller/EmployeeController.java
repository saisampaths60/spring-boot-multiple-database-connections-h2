package com.sample.springbootmultipledatabaseconnectionsh2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.springbootmultipledatabaseconnectionsh2.dto.APIResponseDTO;
import com.sample.springbootmultipledatabaseconnectionsh2.dto.EmployeeDto;
import com.sample.springbootmultipledatabaseconnectionsh2.service.EmployeeService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {

	private EmployeeService employeeService;

	@PostMapping
	public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto) {
		EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto);
		return new ResponseEntity<EmployeeDto>(savedEmployee, HttpStatus.CREATED);
	}

	@GetMapping("{id}")
	public ResponseEntity<APIResponseDTO> getEmployee(@PathVariable("id") Long employeeId) {
		APIResponseDTO apiResponseDTO = employeeService.getEmployeeById(employeeId);
		return new ResponseEntity<APIResponseDTO>(apiResponseDTO, HttpStatus.OK);
	}

}