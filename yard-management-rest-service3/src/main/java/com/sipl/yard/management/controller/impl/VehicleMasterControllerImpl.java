package com.sipl.yard.management.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.sipl.yard.management.controller.VehicleMasterController;
import com.sipl.yard.management.dtos.VehicleMasterDto;
import com.sipl.yard.management.responses.VehicleMasterApiResponse;
import com.sipl.yard.management.service.VehicleMasterService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class VehicleMasterControllerImpl implements VehicleMasterController {

	@Autowired
	private VehicleMasterService vehicleMasterService;

	@Override
	public ResponseEntity<VehicleMasterApiResponse> getAll() {
		log.info("<<START>> getAll called <<START>>");
		ResponseEntity<VehicleMasterApiResponse> responseEntity = new ResponseEntity<>(vehicleMasterService.getAll(),
				HttpStatus.OK);
		log.info("<<END>> getAll <<END>>");
		return responseEntity;
	}

	@Override
	public ResponseEntity<VehicleMasterApiResponse> add(VehicleMasterDto vehicleMasterDto) {
		log.info("<<START>> add called <<START>>");
		ResponseEntity<VehicleMasterApiResponse> responseEntity = new ResponseEntity<>(
				vehicleMasterService.add(vehicleMasterDto), HttpStatus.OK);
		log.info("<<END>> add <<END>>");
		return responseEntity;
	}

	@Override
	public ResponseEntity<VehicleMasterApiResponse> update(VehicleMasterDto vehicleMasterDto) {
		log.info("<<START>> update called <<START>>");
		ResponseEntity<VehicleMasterApiResponse> responseEntity = new ResponseEntity<>(
				vehicleMasterService.update(vehicleMasterDto), HttpStatus.OK);
		log.info("<<END>> update <<END>>");
		return responseEntity;
	}

	@Override
	public ResponseEntity<VehicleMasterApiResponse> deleteById(VehicleMasterDto vehicleMasterDto) {
		log.info("<<START>> deleteById called <<START>>");
		ResponseEntity<VehicleMasterApiResponse> responseEntity = new ResponseEntity<>(
				vehicleMasterService.deleteById(vehicleMasterDto), HttpStatus.OK);
		log.info("<<END>> deleteById <<END>>");
		return responseEntity;
	}

}
