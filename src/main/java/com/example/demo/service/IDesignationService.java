package com.example.demo.service;

import java.util.List;

import com.example.demo.entities.Designation;

public interface IDesignationService {

	public void createDesignation(Designation designation);

	public Designation getDesignationById(Long id);

	public List<Designation> getAllDesignations();

	public void updateDesignation(Designation designation);

}
