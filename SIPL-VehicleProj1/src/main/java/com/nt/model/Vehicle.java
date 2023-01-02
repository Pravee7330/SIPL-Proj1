package com.nt.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="Vehicle")
public class Vehicle {

	
	@Id
	private Integer registration_number;
	
	private String color;
	
	private String brand;
	
	private String vehicle_number;
	
	private String vehicle_type;
	
	private double weight;
	
}
