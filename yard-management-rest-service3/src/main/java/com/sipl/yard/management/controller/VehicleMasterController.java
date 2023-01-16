package com.sipl.yard.management.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sipl.yard.management.dtos.VehicleMasterDto;
import com.sipl.yard.management.responses.VehicleMasterApiResponse;

@RestController
@RequestMapping("/vehicles")
@CrossOrigin("*")
public interface VehicleMasterController {

	@PostMapping("/add")
	ResponseEntity<VehicleMasterApiResponse> add(@RequestBody VehicleMasterDto vehicleMasterDto);

	@PostMapping("/update")
	ResponseEntity<VehicleMasterApiResponse> update(@RequestBody VehicleMasterDto vehicleMasterDto);

	@PostMapping("/delete")
	ResponseEntity<VehicleMasterApiResponse> deleteById(@RequestBody VehicleMasterDto vehicleMasterDto);

	@GetMapping(path = "getAll")
	ResponseEntity<VehicleMasterApiResponse> getAll();

}
