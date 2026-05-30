package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Department;
import com.example.demo.entities.Designation;
import com.example.demo.entities.Employee;


@Repository("emprepo")
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Optional<Employee> findByEmployeeName(String employeeName);
	
	Optional<Employee> findByEmployeeEmail(String employeeEmail);
	
	@Query("UPDATE Employee e SET e.employeeName=:name,e.employeeEmail=:email, e.employeeContact=:contact,e.department.departmentId=:deptid,e.designation.designationId=:desigId WHERE e.employeeId=:empId")
	@Modifying
	public int updateEmployee(Long empId,String name,String email, Long contact, Long deptid,Long desigId);
	
	List<Employee> findByDepartment(Department department);
	
	List<Employee> findByDesignation(Designation designation);
}
