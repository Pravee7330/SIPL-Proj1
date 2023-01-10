package com.nt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.dto.AuthReq;
import com.nt.dto.UserMasterDTO;
import com.nt.response.UserApiResponse;

@RestController
@RequestMapping("/user")
public interface UserController {

	
	@PostMapping("/addusers")
	public ResponseEntity<UserApiResponse> addUser(@RequestBody UserMasterDTO userdto);

	@GetMapping("/allusers")
	ResponseEntity<UserApiResponse> getAllUsers();
	
	
	
	@PutMapping("/updateuser/{id}")
	ResponseEntity<UserApiResponse> updateUser(@RequestBody UserMasterDTO userMasterDto);
	
	@DeleteMapping("/deleteuser/{id}")
	ResponseEntity<UserApiResponse> deleteUser(@PathVariable("id") int id);
	
	@GetMapping("/hello")
	String hello();
	
	@PostMapping("/auth")
	ResponseEntity<?> createAuthToken(@RequestBody AuthReq authReq) throws Exception;
}
