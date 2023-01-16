package com.sipl.yard.management.responses;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.sipl.yard.management.dtos.ContainerMasterDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContainerMasterApiResponse {
	private ContainerMasterDto containerMasterDto;
	private List<ContainerMasterDto> containerMasterDtos;
	private HttpStatus status;
	private String message;
	private boolean error;
}
