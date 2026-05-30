package com.example.demo.mapper;

import com.example.demo.dto.DepartmentDto;
import com.example.demo.entities.Department;

public class DepartmentMapper {

	public static DepartmentDto mapToDepartmentDto(Department department, DepartmentDto deptDto) {

		deptDto.setDepartmentId(department.getDepartmentId());
		deptDto.setDepartmentName(department.getDepartmentName());
		deptDto.setCompany(department.getCompany());

		return deptDto;
	}

	public static Department mapToDepartment(DepartmentDto deptDto, Department dept) {

		dept.setDepartmentId(deptDto.getDepartmentId());
		dept.setDepartmentName(deptDto.getDepartmentName());
		dept.setCompany(deptDto.getCompany());

		return dept;
	}
}
