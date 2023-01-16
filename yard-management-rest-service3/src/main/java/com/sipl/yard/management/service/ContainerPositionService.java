package com.sipl.yard.management.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.sipl.yard.management.responses.ContainerPositionApiResponse;

@Service
public interface ContainerPositionService {
	
	public ContainerPositionApiResponse getContainerPositionByYard(Integer yardId, Optional<String> containerNumber);
	
	public ContainerPositionApiResponse getContainerPositionDetailsByContainer(String containerNumber);
	
	public ContainerPositionApiResponse getAllCoordinatesForReport(LocalDate startDate, LocalDate endDate,
			Optional<Integer> pageNum, Optional<Integer> pageSize);

	
	public ByteArrayInputStream getContainerPositionCSV(HttpServletResponse response, LocalDate startDate, LocalDate endDate );

}
