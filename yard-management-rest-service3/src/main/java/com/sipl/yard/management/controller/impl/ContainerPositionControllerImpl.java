package com.sipl.yard.management.controller.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.sipl.yard.management.controller.ContainerPositionController;
import com.sipl.yard.management.responses.ContainerPositionApiResponse;
import com.sipl.yard.management.service.ContainerPositionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ContainerPositionControllerImpl implements ContainerPositionController {

	@Autowired
	private ContainerPositionService containerPositionService;

	@Override
	public ResponseEntity<ContainerPositionApiResponse> getContainerPositionByYard(@PathVariable Integer yardId,
			@RequestParam Optional<String> containerNumber) {
		log.info("<<Start>> getContainerPositionByYard called <<Start>>");
		ResponseEntity<ContainerPositionApiResponse> containerPositionApiResponse = new ResponseEntity<>(
				containerPositionService.getContainerPositionByYard(yardId, containerNumber), HttpStatus.OK);
		log.info("<<End>>getContainerPositionByYard<<End>>");
		return containerPositionApiResponse;
	}

	@Override
	public ResponseEntity<ContainerPositionApiResponse> getContainerPositionDetailsByContainer(
			@PathVariable String containerNumber) {
		log.info("<<Start>> getContainerPositionDetailsByContainer called <<Start>>");
		ResponseEntity<ContainerPositionApiResponse> containerPositionApiResponse = new ResponseEntity<>(
				containerPositionService.getContainerPositionDetailsByContainer(containerNumber), HttpStatus.OK);
		log.info("<<End>>getContainerPositionDetailsByContainer<<End>>");
		return containerPositionApiResponse;
	}

	@Override
	public ResponseEntity<ContainerPositionApiResponse> getAllCoordinatesForReport(LocalDate startDate,
			LocalDate endDate, Optional<Integer> pageNum, Optional<Integer> pageSize) {
		log.info("<<Start>> getAllCoordinatesForReport called <<Start>>");
		ResponseEntity<ContainerPositionApiResponse> containerPositionApiResponse = new ResponseEntity<>(
				containerPositionService.getAllCoordinatesForReport(startDate, endDate, pageNum, pageSize),
				HttpStatus.OK);
		log.info("<<End>>getAllCoordinatesForReport<<End>>");
		return containerPositionApiResponse;
	}

	@Override
	public void getContainerPositionCSV(HttpServletResponse httpServletResponse, LocalDate startDate,
			LocalDate endDate) {
		InputStreamResource file = new InputStreamResource(
				containerPositionService.getContainerPositionCSV(httpServletResponse, startDate, endDate));

	}

}
