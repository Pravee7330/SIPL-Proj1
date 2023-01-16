package com.sipl.yard.management.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.sipl.yard.management.controller.RoleController;
import com.sipl.yard.management.responses.RoleApiResponse;
import com.sipl.yard.management.service.RoleService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RoleControllerImpl implements RoleController {
	
	@Autowired
	private RoleService roleService;

	@Override
	public ResponseEntity<RoleApiResponse> getAll() {
		log.info("<<START>> getAll called <<START>>");
		ResponseEntity<RoleApiResponse> responseEntity = new ResponseEntity<>(roleService.getAll(),
				HttpStatus.OK);
		log.info("<<END>> getAll <<END>>");
		return responseEntity;
	}

}
