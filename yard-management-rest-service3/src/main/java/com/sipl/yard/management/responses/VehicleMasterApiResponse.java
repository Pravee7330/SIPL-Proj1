package com.sipl.yard.management.responses;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.sipl.yard.management.dtos.VehicleMasterDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VehicleMasterApiResponse {
	private VehicleMasterDto vehicleMasterDto;
	private List<VehicleMasterDto> vehicleMasterDtos;
	private HttpStatus status;
	private String message;
	private boolean error;
}
