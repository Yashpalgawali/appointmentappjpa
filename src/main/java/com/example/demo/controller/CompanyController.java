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
import com.example.demo.entities.Company;
import com.example.demo.service.ICompanyService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("company")
public class CompanyController {

	private final ICompanyService compserv;

	@PostMapping("/")
	public ResponseEntity<ResponseDto> createCompany(@Valid @RequestBody Company company) {
		compserv.createCompany(company);
		return ResponseEntity.status(HttpStatus.CREATED).body(
				new ResponseDto(company.getCompanyName() + " is created successfully", HttpStatus.CREATED.toString()));
	}

	@PutMapping("/")
	public ResponseEntity<ResponseDto> updateCompany(@Valid @RequestBody Company company) {
		compserv.updateCompany(company);
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseDto(company.getCompanyName() + " is updated successfully", HttpStatus.OK.toString()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
		Company company = compserv.getCompanyById(id);
		return ResponseEntity.status(HttpStatus.OK).body(company);
	}

	@GetMapping("/")
	public ResponseEntity<List<Company>> getAllCompanies() {
		List<Company> companyList = compserv.getAllCompanies();
		return ResponseEntity.status(HttpStatus.OK).body(companyList);
	}
}
