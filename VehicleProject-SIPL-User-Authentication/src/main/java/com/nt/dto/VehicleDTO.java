package com.nt.dto;

import java.time.LocalDate;

import lombok.Data;

@Data

public class VehicleDTO {


	private Integer id;
	
	
	private String registrationNumber;
	
	private String color;
	
	private String brand;
		
	private String vehicleType;
	
	private double weight;
	private LocalDate date;
	
}
