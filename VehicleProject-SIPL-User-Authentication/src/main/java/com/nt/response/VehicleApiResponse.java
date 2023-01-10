package com.nt.response;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.nt.dto.VehicleDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleApiResponse {

	  private VehicleDTO vehicledtoresponse;
	  
	  private List<VehicleDTO> vehicledtos;
	  
	  private HttpStatus status;
	  
	  private String message;
	  
	  private boolean error;
		
	  
	
}
