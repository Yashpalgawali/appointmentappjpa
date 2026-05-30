package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.DepartmentDto;
import com.example.demo.entities.Department;

public interface IDepartmentService {
	
	public void createDepartment(Department department);
	
	public DepartmentDto getDepartmentById(Long dept_id);
	
	public void updateDepartment(Department department);
	
	public List<DepartmentDto> getAllDepartments();
	
	public List<DepartmentDto> getAllDepartmentsByCompanyId(Long compId);
}
