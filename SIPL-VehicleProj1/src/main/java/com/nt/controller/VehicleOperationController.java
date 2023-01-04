package com.nt.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.nt.Response.VehicleApiResponse;
import com.nt.dto.VehicleDTO;
import com.nt.service.IVehicleMgmtService;

import io.swagger.models.HttpMethod;

@RestController
@RequestMapping("/vehicle")
public class VehicleOperationController implements Controller {

	

	@Autowired
	private IVehicleMgmtService   service;

	@Override
	public ResponseEntity<VehicleApiResponse> addvehicle(VehicleDTO vehicledto) {
		ResponseEntity<VehicleApiResponse> response = new ResponseEntity<>(service.add(vehicledto),HttpStatus.CREATED);
		return response;
	}

	@Override
	public ResponseEntity<VehicleApiResponse> updateVehicle(VehicleDTO vehicledto) {
		ResponseEntity<VehicleApiResponse> response = new ResponseEntity<>(service.update(vehicledto),HttpStatus.CREATED);
		return response; 
	}

		@Override
		public ResponseEntity<VehicleApiResponse> getAll() {
			ResponseEntity<VehicleApiResponse> response = new ResponseEntity<>(service.getAll(),HttpStatus.CREATED);
			return response; 
	}
	
	@Override
	public ResponseEntity<VehicleApiResponse> deleteVehicle(int id) {
		 ResponseEntity<VehicleApiResponse> response = new  ResponseEntity<>(service.deletebyid(id),HttpStatus.CREATED);
		 return response; 
	}

	@Override
	public ResponseEntity<VehicleApiResponse> pageing(int id ,int size) {
		 ResponseEntity<VehicleApiResponse> response = new  ResponseEntity<>(service.showPageRecords(id, size),HttpStatus.CREATED);
		return response;
	}

	

	@Override
	public ResponseEntity<String> getVehicleList() {
		ResponseEntity<String> response= new ResponseEntity<>(service.callapi(),HttpStatus.CREATED);
		
		// TODO Auto-generated method stub
		return response;
	}
	
	




}
