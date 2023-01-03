package com.nt.dto;

import javax.persistence.Id;

import lombok.Data;

@Data

public class VehicleDTO {


	private Integer id;
	
	
	private String registrationNumber;
	
	private String color;
	
	private String brand;
		
	private String vehicleType;
	
	private double weight;
	
	
}
