package com.example.demo.service.impl;

import java.util.List;
import java.util.function.Consumer;

import org.springframework.stereotype.Service;

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

		Consumer<? super Designation> action = d -> new ResourceAlreadyExistsException(
				"Designation " + trimmedDesigName + " is already present");
		desigrepo.findByDesignationName(trimmedDesigName).ifPresent(action);

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
				.orElseThrow(() -> new ResourceNotExistsException("No Designation foudn for given ID " + id));
	}

	@Override
	public List<Designation> getAllDesignations() {

		var desigList = desigrepo.findAll();
		if (desigList.size() > 0)
			return desigList;
		throw new ResourceNotExistsException("No Designation(s) found");
	}

	@Override
	public void updateDesignation(Designation designation) {

		this.getDesignationById(designation.getDesignationId());

		int result = desigrepo.updateDesignation(designation.getDesignationId(), designation.getDesignationName());
		if (result < 0) {
			throw new ResourceNotModifiedException(
					"Designation " + designation.getDesignationName() + " is not updated");
		}

	}

}
