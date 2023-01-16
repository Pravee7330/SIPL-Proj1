package com.sipl.yard.management.service;

import org.springframework.stereotype.Service;

import com.sipl.yard.management.dtos.VehicleMasterDto;
import com.sipl.yard.management.responses.VehicleMasterApiResponse;

@Service
public interface VehicleMasterService {

	VehicleMasterApiResponse deleteById(VehicleMasterDto vehicleMasterDtod);

	VehicleMasterApiResponse update(VehicleMasterDto vehicleMasterDto);

	VehicleMasterApiResponse add(VehicleMasterDto vehicleMasterDto);

	VehicleMasterApiResponse getAll();

}
