package com.nt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.nt.Response.VehicleApiResponse;
import com.nt.dto.VehicleDTO;

public interface Controller {

	@Autowired
	public static final RestTemplate restTemplate = new RestTemplate();
	
	
	@PostMapping("/register")
	public ResponseEntity<VehicleApiResponse> addvehicle(@RequestBody VehicleDTO vehicledto);
	
	@PutMapping("/update")
	public ResponseEntity<VehicleApiResponse> updateVehicle(@RequestBody VehicleDTO vehicledto);
	
	
	@GetMapping("/getall")
	public ResponseEntity<VehicleApiResponse> getAll();

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<VehicleApiResponse> deleteVehicle(@PathVariable("id") int id );

	
	@GetMapping("/page/{no}/{size}")
	public ResponseEntity<VehicleApiResponse> pageing(@PathVariable("no") int id ,@PathVariable("size") int size);
	

	@PostMapping("/callapi")
	  public ResponseEntity<String> getVehicleList();
	
}
