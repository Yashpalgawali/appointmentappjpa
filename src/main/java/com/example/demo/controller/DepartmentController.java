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

import com.example.demo.dto.DepartmentDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.entities.Department;
import com.example.demo.service.IDepartmentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("department")
public class DepartmentController {

	private final IDepartmentService deptserv;

	@PostMapping("/")
	public ResponseEntity<ResponseDto> createDepartment(@Valid @RequestBody Department department) {
		deptserv.createDepartment(department);
		return ResponseEntity.status(HttpStatus.CREATED).body(
				new ResponseDto(department.getDepartmentName() + " is created successfully", HttpStatus.CREATED.toString()));
	}

	@PutMapping("/")
	public ResponseEntity<ResponseDto> updateDepartment(@Valid @RequestBody Department department) {
		deptserv.updateDepartment(department);
		return ResponseEntity.status(HttpStatus.CREATED).body(
				new ResponseDto(department.getDepartmentName() + " is updated successfully", HttpStatus.CREATED.toString()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable Long id) {
		DepartmentDto department = deptserv.getDepartmentById(id);
		return ResponseEntity.status(HttpStatus.OK).body(department);
	}

	@GetMapping("/")
	public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
		List<DepartmentDto> departmentList = deptserv.getAllDepartments();
		return ResponseEntity.status(HttpStatus.OK).body(departmentList);
	}
}
