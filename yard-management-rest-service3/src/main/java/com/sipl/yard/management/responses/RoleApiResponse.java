package com.sipl.yard.management.responses;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.sipl.yard.management.dtos.RoleDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleApiResponse {

	private RoleDto roleDto;
	private List<RoleDto> roleDtos;
	private HttpStatus status;
	private String message;
	private boolean error;

}
