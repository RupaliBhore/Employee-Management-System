package com.springboot.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.backend.entity.Employee;
import com.springboot.backend.exception.ResourceNotFoundException;
import com.springboot.backend.repository.EmployeeRepository;

//@RestController se https requst handle kar sakate ho
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository  employeeRepository;
	
	
	//get all empoyee
	  @GetMapping
	    public List<Employee> getAllEmployees(){
	        return employeeRepository.findAll();
	    }
	  
	  
	// build create employee REST API
	  //@RequestBody this anootaion convert JSON Into java object
	    @PostMapping
	    public Employee createEmployee(@RequestBody Employee employee) {
	        return employeeRepository.save(employee); //save method take entity object
	    }
	    
	    
	    // build get employee by id REST API
	    @GetMapping("{id}")
	    public ResponseEntity<Employee> getEmployeeById(@PathVariable  long id)
	    {
	        Employee employee = employeeRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" + id));
	        return ResponseEntity.ok(employee);
	    }
	    
	    
	    // build update employee REST API
	    @PutMapping("{id}")                                                                 //type of employee object
	    public ResponseEntity<Employee> updateEmployee(@PathVariable long id,@RequestBody Employee employeeDetails) {
	        Employee updateEmployee = employeeRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));

	        updateEmployee.setFirstName(employeeDetails.getFirstName());
	        updateEmployee.setLastName(employeeDetails.getLastName());
	        updateEmployee.setEmailId(employeeDetails.getEmailId());

	        employeeRepository.save(updateEmployee);

	        return ResponseEntity.ok(updateEmployee);
	    }
	    
	    
	    
	    // build delete employee REST API
	    @DeleteMapping("{id}")
	    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long id){

	        Employee employee = employeeRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));

	        employeeRepository.delete(employee);

	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	    }

}
