package com.nt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

//@Data
@Entity
@Table(name="Vehicle")
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", registrationNumber=" + registrationNumber + ", color=" + color + ", brand="
				+ brand + ", vehicleType=" + vehicleType + ", weight=" + weight + "]";
	}

	public Vehicle(Integer id, String registrationNumber, String color, String brand, String vehicleType,
			double weight) {
		super();
		this.id = id;
		this.registrationNumber = registrationNumber;
		this.color = color;
		this.brand = brand;
		this.vehicleType = vehicleType;
		this.weight = weight;
	}

	public Vehicle() {
		super();
	}
	
	
	
	
}
