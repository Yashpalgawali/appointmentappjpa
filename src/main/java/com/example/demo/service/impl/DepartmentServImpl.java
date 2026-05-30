package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.DepartmentDto;
import com.example.demo.entities.Company;
import com.example.demo.entities.Department;
import com.example.demo.exceptions.GlobalException;
import com.example.demo.exceptions.ResourceAlreadyExistsException;
import com.example.demo.exceptions.ResourceNotExistsException;
import com.example.demo.mapper.DepartmentMapper;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.service.ICompanyService;
import com.example.demo.service.IDepartmentService;

import lombok.RequiredArgsConstructor;

@Service("deptserv")
@RequiredArgsConstructor
public class DepartmentServImpl implements IDepartmentService {

	private final DepartmentRepository deptrepo;

	private final ICompanyService compserv;

	@Override
	public void createDepartment(Department department) {

		String trimmedDeptName = department.getDepartmentName().trim();
		Optional<Department> deptObj = deptrepo.findByDepartmentName(trimmedDeptName);

		if (deptObj.isPresent()) {
			throw new ResourceAlreadyExistsException("Department " + trimmedDeptName + " is already present");
		}

		Department dept = deptrepo.save(department);
		if (dept == null) {
			throw new GlobalException("Department " + trimmedDeptName + " is not saved");
		}
	}

	@Override
	public DepartmentDto getDepartmentById(Long dept_id) {

		Department dept = deptrepo.findById(dept_id)
				.orElseThrow(() -> new ResourceNotExistsException("No Department found for given ID " + dept_id));
		
		DepartmentDto deptDto = DepartmentMapper.mapToDepartmentDto(dept, new DepartmentDto());
		return deptDto;
	}

	@Override
	public void updateDepartment(Department department) {
		String trimmedDeptName = department.getDepartmentName().trim();
		Department deptObj = deptrepo.findById(department.getDepartmentId())
				.orElseThrow(() -> new ResourceNotExistsException(
						"Department is not found for given ID " + department.getDepartmentId()));

		int result = deptrepo.updateDepartment(department.getDepartmentId(), trimmedDeptName,
				department.getCompany().getCompanyId());
		if (result < 0) {
			throw new GlobalException("Department " + trimmedDeptName + " is not updated");
		}

	}

	@Override
	public List<DepartmentDto> getAllDepartments() {
		List<Department> deptList = deptrepo.findAll();
		List<DepartmentDto> deptDtoList = deptList.stream().map((dept) -> {

			DepartmentDto deptDto = DepartmentMapper.mapToDepartmentDto(dept, new DepartmentDto());
			return deptDto;
		}).collect(Collectors.toList());
		return deptDtoList;
	}

	@Override
	public List<DepartmentDto> getAllDepartmentsByCompanyId(Long compId) {

		Company company = compserv.getCompanyById(compId);

		List<Department> deptList = deptrepo.findByCompany(company);
		List<DepartmentDto> deptDtoList = deptList.stream().map((dept) -> {
			DepartmentDto deptDto = DepartmentMapper.mapToDepartmentDto(dept, new DepartmentDto());
			return deptDto;
		}).collect(Collectors.toList());
		return deptDtoList;
	}

}
