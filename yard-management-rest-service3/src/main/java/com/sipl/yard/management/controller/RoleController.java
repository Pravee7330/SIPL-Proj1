package com.sipl.yard.management.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sipl.yard.management.responses.RoleApiResponse;

@RestController
@RequestMapping("/roles")
@CrossOrigin("*")
public interface RoleController {
	
	@GetMapping(path = "getAll")
	ResponseEntity<RoleApiResponse> getAll();

}
