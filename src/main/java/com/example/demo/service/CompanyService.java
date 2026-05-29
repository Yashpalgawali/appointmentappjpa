package com.example.demo.service;

import java.util.List;

import com.example.demo.entities.Company;

public interface CompanyService {

	public void createCompany(Company company);

	public Company getCompanyById(Long id);

	public List<Company> getAllCompanies();

	public void updateCompany(Company company);

}
