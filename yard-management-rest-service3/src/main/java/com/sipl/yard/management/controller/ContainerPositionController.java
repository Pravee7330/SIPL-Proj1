package com.sipl.yard.management.controller;

import java.time.LocalDate;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sipl.yard.management.responses.ContainerPositionApiResponse;

@RestController
@RequestMapping("/container_position_details")
@CrossOrigin("*")
public interface ContainerPositionController {

	@GetMapping(path = "/getContainerPositionByYard/{yardId}")
	ResponseEntity<ContainerPositionApiResponse> getContainerPositionByYard(Integer yardId,
			Optional<String> containerNumber);

	@GetMapping(path = "/get-containerPosition-bycontainer/{containerNumber}")
	ResponseEntity<ContainerPositionApiResponse> getContainerPositionDetailsByContainer(String containerNumber);

	@GetMapping(path = "report/get-all-coordinates/{startDate}/{endDate}")
	ResponseEntity<ContainerPositionApiResponse> getAllCoordinatesForReport(
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
			@RequestParam Optional<Integer> pageNum, @RequestParam Optional<Integer> pageSize);

	@GetMapping("/report/get-all-ContainerPositionCSV")
	public void getContainerPositionCSV(HttpServletResponse httpServletResponse,
			@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate);


}
