package com.nt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.model.Vehicle;
import com.nt.service.IVehicleMgmtService;

@RestController
@RequestMapping("/vehicle")
public class VehicleOperationController {

	@Autowired
	private IVehicleMgmtService   service;
	
	
	@PostMapping("/register")
	public ResponseEntity<String> saveClub(@RequestBody Vehicle vehicle){
		try {
			// use service
			String msg=service.register(vehicle);
			return new ResponseEntity<String>(msg,HttpStatus.CREATED);
		}catch(Exception e) {
		       	e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	
	}
}
