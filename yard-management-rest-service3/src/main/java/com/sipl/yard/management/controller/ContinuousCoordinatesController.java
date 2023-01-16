package com.sipl.yard.management.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sipl.yard.management.responses.ContinuousCoordinatesApiResponse;

@RestController
@RequestMapping(path = "/continuous_coordinates")
@CrossOrigin("*")
public interface ContinuousCoordinatesController {

	@GetMapping("/get-latest-continuous-coordinate/{vehicleId}")
	ResponseEntity<ContinuousCoordinatesApiResponse> getContinuousDataByVehicleId(@PathVariable String vehicleId);
	
	@GetMapping("/get-all-continuous-coordinate")
	ResponseEntity<ContinuousCoordinatesApiResponse> getContinuousCoordinatesByDistinctVehicleId();

}
