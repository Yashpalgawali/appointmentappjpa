package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDto;
import com.example.demo.entities.Employee;
import com.example.demo.service.IEmployeeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("employee")
public class EmployeeController {

	private final IEmployeeService empserv;

	@PostMapping("/")
	public ResponseEntity<ResponseDto> createEmployee(@Valid @RequestBody Employee employee) {
		empserv.createEmployee(employee);
		return ResponseEntity.status(HttpStatus.CREATED).body(
				new ResponseDto(employee.getEmployeeName() + " is created successfully", HttpStatus.CREATED.toString()));
	}

	@PutMapping("/")
	public ResponseEntity<ResponseDto> updateEmployee(@Valid @RequestBody Employee employee) {
		empserv.updateEmployee(employee);
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseDto(employee.getEmployeeName() + " is updated successfully", HttpStatus.OK.toString()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee = empserv.getEmployeeById(id);
		return ResponseEntity.status(HttpStatus.OK).body(employee);
	}

	@GetMapping("/")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employeeList = empserv.getAllEmployees();
		return ResponseEntity.status(HttpStatus.OK).body(employeeList);
	}
	
	@GetMapping("/department/{deptId}")
	public ResponseEntity<List<Employee>> getAllEmployeesByDepartment(@PathVariable Long deptId) {
		List<Employee> employeeList = empserv.getAllEmployeesByDepartment(deptId);
		return ResponseEntity.status(HttpStatus.OK).body(employeeList);
	}
	
	@GetMapping("/designation/{desigId}")
	public ResponseEntity<List<Employee>> getAllEmployeesByDesignation(@PathVariable Long desigId) {
		List<Employee> employeeList = empserv.getAllEmployeesByDepartment(desigId);
		return ResponseEntity.status(HttpStatus.OK).body(employeeList);
	}
}
