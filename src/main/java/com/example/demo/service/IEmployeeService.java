package com.example.demo.service;

import java.util.List;

import com.example.demo.entities.Employee;

public interface IEmployeeService {

	public void createEmployee(Employee employee);

	public Employee getEmployeeById(Long id);

	public List<Employee> getAllEmployees();

	public void updateEmployee(Employee employee);
	
	public List<Employee> getAllEmployeesByDepartment(Long deptId);
	
	public List<Employee> getAllEmployeesByDesignation(Long desigId);
}
