package com.sipl.yard.management.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sipl.yard.management.dtos.ContainerMasterDto;
import com.sipl.yard.management.responses.ContainerMasterApiResponse;

@RestController
@RequestMapping("/containers")
@CrossOrigin("*")
public interface ContainerMasterController {
	
	@PostMapping("/add")
	ResponseEntity<ContainerMasterApiResponse> add(@RequestBody ContainerMasterDto containerMasterDto);
	
	@PostMapping("/update")
	ResponseEntity<ContainerMasterApiResponse> update(@RequestBody ContainerMasterDto containerMasterDto);
	
	@PostMapping("/delete")
	ResponseEntity<ContainerMasterApiResponse> deleteById(@RequestBody ContainerMasterDto containerMasterDto);

	@GetMapping(path = "getAll")
	ResponseEntity<ContainerMasterApiResponse> getAll();
	
	
}
