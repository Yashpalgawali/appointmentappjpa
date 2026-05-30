package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.Designation;
import com.example.demo.exceptions.GlobalException;
import com.example.demo.exceptions.ResourceAlreadyExistsException;
import com.example.demo.exceptions.ResourceNotExistsException;
import com.example.demo.exceptions.ResourceNotModifiedException;
import com.example.demo.repository.DesignationRepository;
import com.example.demo.service.IDesignationService;

import lombok.RequiredArgsConstructor;

@Service("desigserv")
@RequiredArgsConstructor
public class DesignationServImpl implements IDesignationService {

	private final DesignationRepository desigrepo;

	@Override
	public void createDesignation(Designation designation) {

		String trimmedDesigName = designation.getDesignationName().trim();
		 
		Optional<Designation> foundDesignation =	desigrepo.findByDesignationName(trimmedDesigName);
		if(foundDesignation.isPresent()) {
			throw new ResourceAlreadyExistsException("Designation "+trimmedDesigName+" already exists");
		}

		designation.setDesignationName(trimmedDesigName);
		Designation savedDesig = desigrepo.save(designation);
		if (savedDesig == null) {
			throw new GlobalException("Designation " + trimmedDesigName + " is not saved");
		}
	}

	@Override
	public Designation getDesignationById(Long id) {
		// TODO Auto-generated method stub
		return desigrepo.findById(id)
				.orElseThrow(() -> new ResourceNotExistsException("No Designation found for given ID " + id));
	}

	@Override
	public List<Designation> getAllDesignations() {

		var desigList = desigrepo.findAll();
		if (desigList.size() > 0)
			return desigList;
		throw new ResourceNotExistsException("No Designation(s) found");
	}

	@Override
	@Transactional
	public void updateDesignation(Designation designation) {

		this.getDesignationById(designation.getDesignationId());
		
		String trimmedDesigName = designation.getDesignationName().trim();
		 
		int result = desigrepo.updateDesignation(designation.getDesignationId(), trimmedDesigName);
		if (result < 0) {
			throw new ResourceNotModifiedException(
					"Designation " + trimmedDesigName + " is not updated");
		}

	}

}
