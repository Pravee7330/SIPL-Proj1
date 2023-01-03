package com.nt.service;

import org.springframework.stereotype.Service;

import com.nt.dto.VehicleDTO;

@Service	
public interface IVehicleMgmtService {

	
	public String add(VehicleDTO vehicledto) ;
}
