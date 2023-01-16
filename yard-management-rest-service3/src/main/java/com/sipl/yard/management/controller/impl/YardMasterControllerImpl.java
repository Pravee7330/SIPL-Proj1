package com.sipl.yard.management.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.sipl.yard.management.controller.YardMasterController;
import com.sipl.yard.management.dtos.YardMasterDto;
import com.sipl.yard.management.responses.YardMasterApiResponse;
import com.sipl.yard.management.service.YardMasterService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class YardMasterControllerImpl  implements YardMasterController{

	@Autowired
	YardMasterService  yardMasterService;
	
	@Override
	public ResponseEntity<YardMasterApiResponse> getAll() {
		log.info("<<START>> getAll called <<START>>");
		ResponseEntity<YardMasterApiResponse> responseEntity = new ResponseEntity<>(yardMasterService.getAll(),HttpStatus.OK);
		log.info("<<END>> getAll <<END>>");
		return responseEntity;
	}

	@Override
	public ResponseEntity<YardMasterApiResponse> add(YardMasterDto yardMasterDto) {
		log.info("<<START>> add called <<START>>");
		ResponseEntity<YardMasterApiResponse> responseEntity = new ResponseEntity<>(yardMasterService.add(yardMasterDto),HttpStatus.OK);
		log.info("<<END>> add <<END>>");
		return responseEntity;
	}

	@Override
	public ResponseEntity<YardMasterApiResponse> updateYard(YardMasterDto yardMasterDto) {
		log.info("<<START>> updateYard called <<START>>");
		ResponseEntity<YardMasterApiResponse> responseEntity = new ResponseEntity<>(yardMasterService.updateYard(yardMasterDto),HttpStatus.OK);
		log.info("<<END>> updateYard <<END>>");
		return responseEntity;
	}

	@Override
	public ResponseEntity<YardMasterApiResponse> deleteById(YardMasterDto yardMasterDto) {
		log.info("<<START>> deleteById called <<START>>");
		ResponseEntity<YardMasterApiResponse> responseEntity = new ResponseEntity<>(yardMasterService.deleteById(yardMasterDto),HttpStatus.OK);
		log.info("<<END>> deleteById <<END>>");
		return responseEntity;
	}

}
