package com.sample.springbootmultipledatabaseconnectionsh2.service;

import com.sample.springbootmultipledatabaseconnectionsh2.dto.DepartmentDto;

public interface DepartmentService {

	DepartmentDto saveDepartment(DepartmentDto departmentDto);

	DepartmentDto getDepartmentByCode(String departmentCode);

}