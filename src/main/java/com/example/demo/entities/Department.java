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
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "tbl_department")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Validated
public class Department {

	@Id
	@SequenceGenerator(name = "dept_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "dept_seq", strategy = GenerationType.IDENTITY)
	Long departmentId;

	@Size(min = 2,max = 20, message="Department name must have at least two characters")
	String departmentName;
	
	@ManyToOne
	@JoinColumn(name="company_id")
	Company company;
}
