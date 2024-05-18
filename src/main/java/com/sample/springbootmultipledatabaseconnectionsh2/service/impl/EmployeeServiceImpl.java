package com.sample.springbootmultipledatabaseconnectionsh2.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sample.springbootmultipledatabaseconnectionsh2.dto.APIResponseDTO;
import com.sample.springbootmultipledatabaseconnectionsh2.dto.DepartmentDto;
import com.sample.springbootmultipledatabaseconnectionsh2.dto.EmployeeDto;
import com.sample.springbootmultipledatabaseconnectionsh2.entity.Department;
import com.sample.springbootmultipledatabaseconnectionsh2.entity.Employee;
import com.sample.springbootmultipledatabaseconnectionsh2.repository.DepartmentRepository;
import com.sample.springbootmultipledatabaseconnectionsh2.repository.EmployeeRepository;
import com.sample.springbootmultipledatabaseconnectionsh2.service.EmployeeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	private DepartmentRepository departmentRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Override
	public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
		Employee employee = new Employee(employeeDto.getId(), employeeDto.getFirstName(), employeeDto.getLastName(),
				employeeDto.getEmail(), employeeDto.getDepartmentCode());
		Employee savedEmployee = employeeRepository.save(employee);
		EmployeeDto savedEmployeeDto = new EmployeeDto(savedEmployee.getId(), savedEmployee.getFirstName(),
				savedEmployee.getLastName(), savedEmployee.getEmail(), savedEmployee.getDepartmentCode());
		return savedEmployeeDto;
	}

	@Override
	public APIResponseDTO getEmployeeById(Long employeeId) {
		LOGGER.info("inside getEmployeeById method");
		Employee employee = employeeRepository.findById(employeeId).get();

		Department department = departmentRepository.findByDepartmentCode(employee.getDepartmentCode());

		DepartmentDto departmentDto = new DepartmentDto(department.getId(), department.getDepartmentName(),
				department.getDepartmentDescription(), department.getDepartmentCode());

		EmployeeDto employeeDto = new EmployeeDto(employee.getId(), employee.getFirstName(), employee.getLastName(),
				employee.getEmail(), employee.getDepartmentCode());
		APIResponseDTO apiResponseDTO = new APIResponseDTO();
		apiResponseDTO.setDepartmentDto(departmentDto);
		apiResponseDTO.setEmployeeDto(employeeDto);
		return apiResponseDTO;
	}

}