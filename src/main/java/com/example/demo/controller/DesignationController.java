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
import com.example.demo.entities.Designation;
import com.example.demo.service.IDesignationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("designation")
public class DesignationController {

	private final IDesignationService desigserv;

	@PostMapping("/")
	public ResponseEntity<ResponseDto> createDesignation(@Valid @RequestBody Designation designation) {
		desigserv.createDesignation(designation);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(
				designation.getDesignationName() + " is created successfully", HttpStatus.CREATED.toString()));
	}

	@PutMapping("/")
	public ResponseEntity<ResponseDto> updateDesignation(@Valid @RequestBody Designation designation) {
		desigserv.updateDesignation(designation);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(
				designation.getDesignationName() + " is updated successfully", HttpStatus.CREATED.toString()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Designation> getDesignationById(@PathVariable Long id) {
		Designation designation = desigserv.getDesignationById(id);
		return ResponseEntity.status(HttpStatus.OK).body(designation);
	}

	@GetMapping("/")
	public ResponseEntity<List<Designation>> getAllDesignation() {
		List<Designation> designationList = desigserv.getAllDesignations();
		return ResponseEntity.status(HttpStatus.OK).body(designationList);
	}
}
