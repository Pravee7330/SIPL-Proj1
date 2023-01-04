package com.nt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.nt.Response.VehicleApiResponse;
import com.nt.dto.VehicleDTO;

public interface Controller {

	
	@PostMapping("/register")
	public ResponseEntity<VehicleApiResponse> addvehicle(@RequestBody VehicleDTO vehicledto);
	
	@PutMapping("/update/{id}")
	public ResponseEntity<VehicleApiResponse> updateVehicle(@RequestBody VehicleDTO vehicledto);
	
	
	@GetMapping("/getall")
	public ResponseEntity<VehicleApiResponse> getAll();

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<VehicleApiResponse> deleteVehicle(@PathVariable("id") int id );

}
