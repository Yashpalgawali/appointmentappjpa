package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.Company;
import com.example.demo.exceptions.GlobalException;
import com.example.demo.exceptions.ResourceAlreadyExistsException;
import com.example.demo.exceptions.ResourceNotExistsException;
import com.example.demo.exceptions.ResourceNotModifiedException;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.service.ICompanyService;

import lombok.RequiredArgsConstructor;

@Service("compserv")
@RequiredArgsConstructor
public class CompanyServImpl implements ICompanyService {

	private final CompanyRepository comprepo;

	@Override
	public void createCompany(Company company) {

		Optional<Company> byCompanyName = comprepo.findByCompanyName(company.getCompanyName());
		if (byCompanyName.isPresent()) {
			throw new ResourceAlreadyExistsException("Company " + company.getCompanyName() + " is already exists");
		}

		Company savedCompany = comprepo.save(company);
		if (savedCompany == null) {
			throw new GlobalException("Company " + company.getCompanyName() + " is not saved");
		}
	}

	@Override
	public Company getCompanyById(Long id) {

		return comprepo.findById(id)
				.orElseThrow(() -> new ResourceNotExistsException("Company is found for given ID " + id));
	}

	@Override
	public List<Company> getAllCompanies() {
		var compList = comprepo.findAll();
		if (compList.size() > 0)
			return compList;
		throw new ResourceNotExistsException("No Companies found");
	}

	@Override
	@Transactional
	public void updateCompany(Company company) {
		comprepo.findById(company.getCompanyId())
				.orElseThrow(() -> new ResourceNotExistsException("No Company found " + company.getCompanyName()));

		int result = comprepo.updateCompany(company.getCompanyId(), company.getCompanyName());
		if (result < 0) {
			throw new ResourceNotModifiedException("Company " + company.getCompanyName() + " is not updated");
		}

	}

}
