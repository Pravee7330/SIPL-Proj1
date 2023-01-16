package com.sipl.yard.management.responses;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import com.sipl.yard.management.dtos.ContainerPositionDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContainerPositionApiResponse {
	private ContainerPositionDto containerPositionDto;
	private List<ContainerPositionDto> containerPositionDtos;
	private Page<ContainerPositionDto> containerPositionDtoPage;
	private HttpStatus httpStatus;
	private String message;
	private boolean error;
}
