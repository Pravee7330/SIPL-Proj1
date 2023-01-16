package com.sipl.yard.management.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sipl.yard.management.dtos.YardMasterDto;
import com.sipl.yard.management.responses.YardMasterApiResponse;

@RestController
@RequestMapping("/yard")
@CrossOrigin("*")
public interface YardMasterController {
	
	@PostMapping("/add")
	ResponseEntity<YardMasterApiResponse> add(@RequestBody YardMasterDto yardMasterDto);
	
	@PostMapping("/update")
	ResponseEntity<YardMasterApiResponse> updateYard(@RequestBody YardMasterDto yardMasterDto);
	
	@PostMapping("/delete")
	ResponseEntity<YardMasterApiResponse> deleteById(@RequestBody YardMasterDto yardMasterDto);

	@GetMapping(path = "getAll")
	ResponseEntity<YardMasterApiResponse> getAll();

}
