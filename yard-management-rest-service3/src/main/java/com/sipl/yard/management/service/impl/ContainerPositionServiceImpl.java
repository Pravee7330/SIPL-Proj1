package com.sipl.yard.management.service.impl;

import java.io.ByteArrayInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sipl.yard.management.dtos.ContainerPositionDto;
import com.sipl.yard.management.entities.ContainerPosition;
import com.sipl.yard.management.mapper.ContainerPositionMapper;
import com.sipl.yard.management.repository.ContainerPositionRepository;
import com.sipl.yard.management.responses.ContainerPositionApiResponse;
import com.sipl.yard.management.service.ContainerPositionService;
import com.sipl.yard.management.util.CSVHelper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ContainerPositionServiceImpl implements ContainerPositionService {

	@Autowired
	private ContainerPositionRepository containerPositionRepository;

	@Autowired
	private ContainerPositionMapper containerPositionMapper;

	public ContainerPositionApiResponse getContainerPositionByYard(Integer yardId, Optional<String> containerNumber) {
		try {
			log.info("<<START>>Inside ContainerPositionServiceImpl,  getContainerPositionByYard <<START>>");
			List<ContainerPosition> containerPositionFromDb = null;
			if (containerNumber.isPresent()) {
				containerPositionFromDb = containerPositionRepository.getConatinerInfoByYardIdAndcontainerNumber(yardId,
						containerNumber);
				if (containerPositionFromDb != null) {
					return new ContainerPositionApiResponse(null,
							containerPositionMapper
									.containerPositionListToContainerPositionDtoList(containerPositionFromDb),
							null, HttpStatus.FOUND, "ContainerPosition found successfully", false);

				}
			} else {
				containerPositionFromDb = containerPositionRepository.getConatinerInfoByYardId(yardId);
				if (containerPositionFromDb != null) {
					return new ContainerPositionApiResponse(null,
							containerPositionMapper
									.containerPositionListToContainerPositionDtoList(containerPositionFromDb),
							null, HttpStatus.FOUND, "ContainerPosition found successfully", false);

				}
			}
			return new ContainerPositionApiResponse(null, null, null, HttpStatus.NOT_FOUND, "Record not found", true);
		} catch (Exception e) {
			log.error("Exception in getContainerPositionByYard ", e);
		}
		return new ContainerPositionApiResponse(null, null, null, HttpStatus.INTERNAL_SERVER_ERROR, "server_error",
				true);
	}

	@Override
	public ContainerPositionApiResponse getContainerPositionDetailsByContainer(String containerNumber) {
		try {
			log.info("<<START>>Inside ContainerPositionServiceImpl,  getContainerPositionDetailsByContainer <<START>>");
			if (containerNumber != null) {
				ContainerPosition containerPositionFromDb = containerPositionRepository
						.findContainerPositionDetailsByContainer(containerNumber);
				if (containerPositionFromDb != null) {
					return new ContainerPositionApiResponse(
							containerPositionMapper.mapContainerPositionToContainerPositionDto(containerPositionFromDb),
							null, null, HttpStatus.FOUND, "ContainerPosition found successfully", false);

				} else {
					return new ContainerPositionApiResponse(null, null, null, HttpStatus.NOT_FOUND, "Record not found",
							true);
				}
			} else {
				return new ContainerPositionApiResponse(null, null, null, HttpStatus.BAD_REQUEST, "Record not found",
						true);
			}
		} catch (Exception e) {
			log.error("Exception in getContainerPositionByYard ", e);
		}
		return new ContainerPositionApiResponse(null, null, null, HttpStatus.INTERNAL_SERVER_ERROR, "server_error",
				true);
	}

	@Override
	public ContainerPositionApiResponse getAllCoordinatesForReport(LocalDate startDate, LocalDate endDate,
			Optional<Integer> pageNum, Optional<Integer> pageSize) {
		try {
			log.info("<<START>>Inside ContainerPositionServiceImpl,  getAllCoordinatesForReport <<START>>");
			List<ContainerPosition> containerPositionEntity = containerPositionRepository.getPickupDropData(startDate,
					endDate);
			List<ContainerPositionDto> containerPositionDto = containerPositionMapper
					.containerPositionListToContainerPositionDtoList(containerPositionEntity);
			if (!containerPositionEntity.isEmpty()) {
				Page<ContainerPositionDto> containerPositionDtoPage = toPage(containerPositionDto,
						PageRequest.of(pageNum.orElse(0), pageSize.orElse(10)));
				return new ContainerPositionApiResponse(null, null, containerPositionDtoPage, HttpStatus.FOUND,
						"All Coordinates Found Successfully", false);
			} else {
				return new ContainerPositionApiResponse(null, null, null, HttpStatus.NOT_FOUND, "Coordinates Not Found",
						true);
			}
		} catch (Exception e) {
			log.error("Error occurred in getAllCoordinatesForReport  ", e);
		}
		return new ContainerPositionApiResponse(null, null, null, HttpStatus.INTERNAL_SERVER_ERROR,
				"Internal Server Error", true);
	}

	private Page<ContainerPositionDto> toPage(List<ContainerPositionDto> list, Pageable pageable) {
		if (pageable.getOffset() >= list.size()) {
			return Page.empty();
		}
		int startIndex = (int) pageable.getOffset();
		int endIndex = (int) ((pageable.getOffset() + pageable.getPageSize()) > list.size() ? list.size()
				: pageable.getOffset() + pageable.getPageSize());
		List<ContainerPositionDto> subList = list.subList(startIndex, endIndex);
		return new PageImpl<>(subList, pageable, list.size());
	}

	@Override
	public ByteArrayInputStream getContainerPositionCSV(HttpServletResponse response, LocalDate startDate,
			LocalDate endDate) {
		response.setContentType("application/csv");
		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
		String currentDateTime = dateFormat.format(new Date());
		String headerkey = "Content-Disposition";
		String headervalue = "attachment; filename=dgp_position_report_" + currentDateTime + ".csv";
		response.setHeader(headerkey, headervalue);
		List<ContainerPosition> containerposition = containerPositionRepository
				.findAllByDateGreaterThanEqualAndDateLessThanEqual(startDate, endDate);

		return CSVHelper.containerPositionToCSV(containerposition, response, startDate, endDate);

	}

}
