package com.nt.Response;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.nt.dto.VehicleDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class VehicleApiResponse {

	  private VehicleDTO vehicledtoresponse;
	  
	  private List<VehicleDTO> vehicledtos;
	  
	  private HttpStatus status;
	  
	  private String message;
	  
	  private boolean error;

	public VehicleDTO getVehicledtoresponse() {
		return vehicledtoresponse;
	}

	public void setVehicledtoresponse(VehicleDTO vehicledtoresponse) {
		this.vehicledtoresponse = vehicledtoresponse;
	}

	public List<VehicleDTO> getVehicledtos() {
		return vehicledtos;
	}

	public void setVehicledtos(List<VehicleDTO> vehicledtos) {
		this.vehicledtos = vehicledtos;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public VehicleApiResponse(VehicleDTO vehicledtoresponse, List<VehicleDTO> vehicledtos, HttpStatus status,
			String message, boolean error) {
		super();
		this.vehicledtoresponse = vehicledtoresponse;
		this.vehicledtos = vehicledtos;
		this.status = status;
		this.message = message;
		this.error = error;
	}

	public VehicleApiResponse() {
		super();
	}
	  
	
}
