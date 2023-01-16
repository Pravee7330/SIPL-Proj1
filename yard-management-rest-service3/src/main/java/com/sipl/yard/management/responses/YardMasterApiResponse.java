package com.sipl.yard.management.responses;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.sipl.yard.management.dtos.YardMasterDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class YardMasterApiResponse {
	
	private YardMasterDto yardMasterDto;
	private List<YardMasterDto> yardMasterDtos;
	private HttpStatus status;
	private String message;
	private boolean error;

}
