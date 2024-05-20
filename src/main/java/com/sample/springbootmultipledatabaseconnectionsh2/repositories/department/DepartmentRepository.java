package com.sample.springbootmultipledatabaseconnectionsh2.repositories.department;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.springbootmultipledatabaseconnectionsh2.entities.department.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

	Department findByDepartmentCode(String departmentCode);

}