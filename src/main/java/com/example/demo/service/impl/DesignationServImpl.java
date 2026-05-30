package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entities.Company;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.service.ICompanyService;

import lombok.RequiredArgsConstructor;

@Service("compserv")
@RequiredArgsConstructor
public class DesignationServImpl implements ICompanyService {

	private final CompanyRepository comprepo;

	@Override
	public void createCompany(Company company) {
		comprepo.save(company);

	}

	@Override
	public Company getCompanyById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Company> getAllCompanies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateCompany(Company company) {
		// TODO Auto-generated method stub

	}

}
