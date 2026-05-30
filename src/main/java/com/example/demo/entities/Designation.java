package com.example.demo.entities;

import org.springframework.validation.annotation.Validated;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "tbl_designation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Validated
public class Designation {

	@Id
	@SequenceGenerator(name = "designation_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "designation_seq", strategy = GenerationType.IDENTITY)
	Long designationId;

	@Size(min = 2, max = 20, message = "Designation name must have at least two characters")
	String designationName;
}
