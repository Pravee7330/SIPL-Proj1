package com.sipl.yard.management.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sipl.yard.management.dtos.UserMasterDto;
import com.sipl.yard.management.responses.UserApiResponse;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public interface UserController {
	
    @GetMapping("/validate/{userId}/{password}")
    ResponseEntity<UserApiResponse> validateUser(String userId, String password);

	@PostMapping("/add")
	ResponseEntity<UserApiResponse> add(@RequestBody UserMasterDto userDto);
	
	@PostMapping("/update")
	ResponseEntity<UserApiResponse> update(@RequestBody UserMasterDto userDto);
	
	@PostMapping("/delete")
	ResponseEntity<UserApiResponse> deleteById(@RequestBody UserMasterDto userDto);

	@GetMapping(path = "getAllhhhh")
	ResponseEntity<UserApiResponse> getAll();
}
