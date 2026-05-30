package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.DepartmentDto;
import com.example.demo.entities.Department;
import com.example.demo.entities.Designation;
import com.example.demo.entities.Employee;
import com.example.demo.exceptions.GlobalException;
import com.example.demo.exceptions.ResourceAlreadyExistsException;
import com.example.demo.exceptions.ResourceNotExistsException;
import com.example.demo.exceptions.ResourceNotModifiedException;
import com.example.demo.mapper.DepartmentMapper;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.IDepartmentService;
import com.example.demo.service.IDesignationService;
import com.example.demo.service.IEmployeeService;

import lombok.RequiredArgsConstructor;

@Service("empserv")
@RequiredArgsConstructor
public class EmployeeServImpl implements IEmployeeService {

	private final EmployeeRepository emprepo;
	
	private final IDesignationService desigserv;
	
	private final IDepartmentService deptserv;
	
	@Override
	public void createEmployee(Employee employee) {
		
		Optional<Employee> byName = emprepo.findByEmployeeName(employee.getEmployeeName());
		
		Optional<Employee> byEmail = emprepo.findByEmployeeEmail(employee.getEmployeeEmail());
		
		if( byName.isPresent()) {
			throw new ResourceAlreadyExistsException("Employee already exists with the name "+employee.getEmployeeName());
		}
		
		if(byEmail.isPresent()) {
			throw new ResourceAlreadyExistsException("Employee already exists with the Email : "+employee.getEmployeeEmail());
		}
		
		Employee savedEmp = emprepo.save(employee);
		if(savedEmp==null) {
			throw new GlobalException("Employee "+employee.getEmployeeName()+" is not created");
		}

	}

	@Override
	public Employee getEmployeeById(Long id) {
		// TODO Auto-generated method stub
		return emprepo.findById(id).orElseThrow(()-> new ResourceNotExistsException("Employee is not present with the given ID "+id));
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> empList = emprepo.findAll();
		if(empList.size() > 0) {
			return empList;
		}
		throw new ResourceNotExistsException("No Employee(s) present");
	}

	@Override
	@Transactional
	public void updateEmployee(Employee employee) {
		
		int res = emprepo.updateEmployee(employee.getEmployeeId(), employee.getEmployeeName(), employee.getEmployeeEmail(), employee.getEmployeeContact(), employee.getDepartment().getDepartmentId(),employee.getDesignation().getDesignationId());

		if(res < 0 ) {
			throw new ResourceNotModifiedException("Employee "+employee.getEmployeeName()+" is not updated");
		}
	}

	@Override
	public List<Employee> getAllEmployeesByDepartment(Long deptId) {
		
		DepartmentDto deptDto = deptserv.getDepartmentById(deptId);
		Department dept = DepartmentMapper.mapToDepartment(deptDto, new Department());
		var empList = emprepo.findByDepartment(dept);
		if(empList.size() > 0)
			return empList;
		throw new ResourceNotExistsException("No Employees found under Department"+dept.getDepartmentName());
	}

	@Override
	public List<Employee> getAllEmployeesByDesignation(Long desigId) {
		Designation desig = desigserv.getDesignationById(desigId);
		var empList = emprepo.findByDesignation(desig);
		if(empList.size() > 0)
			return empList;
		throw new ResourceNotExistsException("No Employees found with Designation "+desig.getDesignationName());
	}

}
