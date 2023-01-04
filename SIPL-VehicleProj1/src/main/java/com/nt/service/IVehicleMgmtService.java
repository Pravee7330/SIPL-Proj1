package com.nt.service;

import org.springframework.stereotype.Service;

import com.nt.Response.VehicleApiResponse;
import com.nt.dto.VehicleDTO;

@Service	
public interface IVehicleMgmtService {

	
	public VehicleApiResponse add(VehicleDTO vehicledto) ;
	
	public VehicleApiResponse update(VehicleDTO vehicledto);
	 
	 public VehicleApiResponse deletebyid(int id);
	 
	 public VehicleApiResponse  getAll();
}
