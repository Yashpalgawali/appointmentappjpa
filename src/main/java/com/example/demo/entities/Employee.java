package com.example.demo.entities;

import org.springframework.validation.annotation.Validated;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "tbl_employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Validated
public class Employee {

	@Id
	@SequenceGenerator(name = "employee_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "employee_seq", strategy = GenerationType.IDENTITY)
	Long employeeId;

	@Size(min = 2, max = 20, message = "Employee name must have at least two characters")
	String employeeName;
	
	@Email(message = "Please Enter valid email Address")
	String employeeEmail;
	
	Long employeeContact;
	
	@ManyToOne
	@JoinColumn(name = "department_id")
	Department department;

	@ManyToOne
	@JoinColumn(name = "designation_id")
	Designation designation;
	
	
	
}

