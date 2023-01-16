package com.sipl.yard.management.responses;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.sipl.yard.management.dtos.ContinuousCoordinatesEntityDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContinuousCoordinatesApiResponse {
	private ContinuousCoordinatesEntityDto continuousCoordinatesEntityDto;
	private List<ContinuousCoordinatesEntityDto> continuousCoordinatesEntityDtos;
	private HttpStatus status;
	private String message;
	private boolean error;
}
