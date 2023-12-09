package com.springboot.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.backend.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	
	//ALL CRUD DATABASE METHOD

}
