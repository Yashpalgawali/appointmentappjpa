package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Designation;

@Repository("designationrepo")
public interface DesignationRepository extends JpaRepository<Designation, Long> {

	Optional<Designation> findByDesignationName(String designationName);

	@Modifying
	@Query("Update Designation c SET c.designationName=:name where c.designationId=:id")
	public int updateDesignation(Long id, String name);
}
