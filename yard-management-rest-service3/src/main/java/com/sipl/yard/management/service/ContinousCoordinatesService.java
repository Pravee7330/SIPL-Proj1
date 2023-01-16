package com.sipl.yard.management.service;

import org.springframework.stereotype.Service;

import com.sipl.yard.management.responses.ContinuousCoordinatesApiResponse;

@Service
public interface ContinousCoordinatesService {
	
	ContinuousCoordinatesApiResponse getContinuousDataByVehicleId(String vehicleId);
	
	ContinuousCoordinatesApiResponse getContinuousCoordinatesByDistinctVehicleId();

}
