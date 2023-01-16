package com.sipl.yard.management.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.sipl.yard.management.controller.ContainerMasterController;
import com.sipl.yard.management.dtos.ContainerMasterDto;
import com.sipl.yard.management.responses.ContainerMasterApiResponse;
import com.sipl.yard.management.service.ContainerMasterService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ContainerMasterControllerImpl implements ContainerMasterController {

	@Autowired
	private ContainerMasterService containerMasterService;

	@Override
	public ResponseEntity<ContainerMasterApiResponse> getAll() {
		log.info("<<START>> getAll called <<START>>");
		ResponseEntity<ContainerMasterApiResponse> responseEntity = new ResponseEntity<>(
				containerMasterService.getAll(), HttpStatus.OK);
		log.info("<<END>> getAll <<END>>");
		return responseEntity;
	}

	@Override
	public ResponseEntity<ContainerMasterApiResponse> add(ContainerMasterDto containerMasterDto) {
		log.info("<<START>> add called <<START>>");
		ResponseEntity<ContainerMasterApiResponse> responseEntity = new ResponseEntity<>(
				containerMasterService.add(containerMasterDto), HttpStatus.OK);
		log.info("<<END>> add <<END>>");
		return responseEntity;
	}

	@Override
	public ResponseEntity<ContainerMasterApiResponse> update(ContainerMasterDto containerMasterDto) {
		log.info("<<START>> update called <<START>>");
		ResponseEntity<ContainerMasterApiResponse> responseEntity = new ResponseEntity<>(
				containerMasterService.updateContainer(containerMasterDto), HttpStatus.OK);
		log.info("<<END>> update <<END>>");
		return responseEntity;
	}

	@Override
	public ResponseEntity<ContainerMasterApiResponse> deleteById(ContainerMasterDto containerMasterDto) {
		log.info("<<START>> deleteById called <<START>>");
		ResponseEntity<ContainerMasterApiResponse> responseEntity = new ResponseEntity<>(
				containerMasterService.deleteById(containerMasterDto), HttpStatus.OK);
		log.info("<<END>> deleteById <<END>>");
		return responseEntity;
	}

}
