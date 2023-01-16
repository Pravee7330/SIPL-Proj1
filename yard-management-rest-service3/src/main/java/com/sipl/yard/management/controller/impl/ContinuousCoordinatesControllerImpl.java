package com.sipl.yard.management.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.sipl.yard.management.controller.ContinuousCoordinatesController;
import com.sipl.yard.management.responses.ContinuousCoordinatesApiResponse;
import com.sipl.yard.management.service.ContinousCoordinatesService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ContinuousCoordinatesControllerImpl implements ContinuousCoordinatesController {

	@Autowired
	private ContinousCoordinatesService continousCoordinatesService;

	@Override
	public ResponseEntity<ContinuousCoordinatesApiResponse> getContinuousDataByVehicleId(String vehicleId) {
		log.info("<<Start>> getContinuousDataByVehicleId called <<Start>>");
		ResponseEntity<ContinuousCoordinatesApiResponse> continuousCoordinatesApiResponse = new ResponseEntity<>(
				continousCoordinatesService.getContinuousDataByVehicleId(vehicleId), HttpStatus.OK);
		log.info("<<End>> getContinuousDataByVehicleId <<End>>");
		return continuousCoordinatesApiResponse;
	}

	@Override
	public ResponseEntity<ContinuousCoordinatesApiResponse> getContinuousCoordinatesByDistinctVehicleId() {
		log.info("<<Start>> getContinuousCoordinatesByDistinctVehicleId called <<Start>>");
		ResponseEntity<ContinuousCoordinatesApiResponse> continuousCoordinatesApiResponse = new ResponseEntity<>(
				continousCoordinatesService.getContinuousCoordinatesByDistinctVehicleId(), HttpStatus.OK);
		log.info("<<End>> getContinuousCoordinatesByDistinctVehicleId <<End>>");
		return continuousCoordinatesApiResponse;
	}

}
