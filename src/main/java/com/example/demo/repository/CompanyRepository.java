package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Company;

@Repository("companyrepo")
public interface CompanyRepository extends JpaRepository<Company, Long> {

	Optional<Company> findByCompanyName(String companyName);

	@Modifying
	@Query("Update Company c SET c.companyName=:name where c.companyId=:id")
	public int updateCompany(Long id, String name);
}
