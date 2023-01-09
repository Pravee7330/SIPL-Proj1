package com.nt.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="Vehicle")
@Audited
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="regNum")
	private String registrationNumber;
	
	private String color;
	
	private String brand;
	

	
	private String vehicleType;
	
	private double weight;

	private LocalDate date;
	
}
