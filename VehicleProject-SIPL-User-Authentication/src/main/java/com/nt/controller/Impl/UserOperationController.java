package com.nt.controller.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.controller.UserController;
import com.nt.dto.AuthReq;
import com.nt.dto.UserMasterDTO;
import com.nt.response.UserApiResponse;
import com.nt.service.IUserMgmtService;


public class UserOperationController implements UserController {

    @Autowired	
	private IUserMgmtService service;
	
	
	@Override
	public ResponseEntity<UserApiResponse> addUser(UserMasterDTO userdto) {
		ResponseEntity<UserApiResponse> response= new ResponseEntity<>(service.addUser(userdto),HttpStatus.CREATED);
		return response;
	}


	@Override
	public ResponseEntity<UserApiResponse> updateUser(UserMasterDTO userdto) {
	
		ResponseEntity<UserApiResponse> response= new ResponseEntity<>(service.updateUser(userdto),HttpStatus.CREATED);
		return response;
	}


	@Override
	public ResponseEntity<UserApiResponse> deleteUser(int id) {
		// TODO Auto-generated method stub
		ResponseEntity<UserApiResponse> response= new ResponseEntity<>(service.deleteUser(id),HttpStatus.CREATED);
		return response;
	}

	@Override
	public String hello() {
		return "Hello";
	}

	@Override
	public ResponseEntity<?> createAuthToken(AuthReq authReq) throws Exception {
		return service.generate(authReq);
	}


	@Override
	public ResponseEntity<UserApiResponse> getAllUsers() {
		ResponseEntity<UserApiResponse> response = new ResponseEntity<UserApiResponse>((UserApiResponse) service.getAllUser(),HttpStatus.FOUND);
		return response;
	}




	


}
