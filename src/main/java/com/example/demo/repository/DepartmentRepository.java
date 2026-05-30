package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Company;
import com.example.demo.entities.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

	Optional<Department> findByDepartmentName(String departmentName);
	
	List<Department> findByCompany(Company company);
	
	@Query("UPDATE Department d SET d.departmentName=:dept_name,d.company.companyId=:comp_id WHERE d.departmentId=:dept_id")
	@Modifying	
	public int updateDepartment(Long dept_id, String dept_name, Long comp_id);
}
