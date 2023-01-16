package com.sipl.yard.management.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import com.sipl.yard.management.controller.UserController;
import com.sipl.yard.management.dtos.UserMasterDto;
import com.sipl.yard.management.responses.UserApiResponse;
import com.sipl.yard.management.service.UserMasterService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserControllerImpl implements UserController {

	@Autowired
	private UserMasterService userMasterService;

	@Override
	public ResponseEntity<UserApiResponse> validateUser(@PathVariable String userId, @PathVariable String password) {
		log.info("<<Start>> validateUser called <<Start>>");
		ResponseEntity<UserApiResponse> userApiResponse = new ResponseEntity<>(
				userMasterService.validateUserMaster(userId, password), HttpStatus.OK);
		log.info("<<End>>validateUser<<End>>");
		return userApiResponse;
	}

	@Override
	public ResponseEntity<UserApiResponse> getAll() {
		log.info("<<START>> getAll called <<START>>");
		ResponseEntity<UserApiResponse> responseEntity = new ResponseEntity<>(userMasterService.getAll(),
				HttpStatus.OK);
		log.info("<<END>> getAll <<END>>");
		return responseEntity;
	}

	@Override
	public ResponseEntity<UserApiResponse> add(UserMasterDto userDto) {
		log.info("<<START>> add called <<START>>");
		ResponseEntity<UserApiResponse> responseEntity = new ResponseEntity<>(userMasterService.add(userDto),
				HttpStatus.OK);
		log.info("<<END>> add <<END>>");
		return responseEntity;
	}

	@Override
	public ResponseEntity<UserApiResponse> update(UserMasterDto userDto) {
		log.info("<<START>> update called <<START>>");
		ResponseEntity<UserApiResponse> responseEntity = new ResponseEntity<>(userMasterService.update(userDto),
				HttpStatus.OK);
		log.info("<<END>> update <<END>>");
		return responseEntity;
	}

	@Override
	public ResponseEntity<UserApiResponse> deleteById(UserMasterDto userDto) {
		log.info("<<START>> deleteById called <<START>>");
		ResponseEntity<UserApiResponse> responseEntity = new ResponseEntity<>(userMasterService.deleteById(userDto),
				HttpStatus.OK);
		log.info("<<END>> deleteById <<END>>");
		return responseEntity;
	}
}
