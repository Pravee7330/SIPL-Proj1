package com.nt.mapper;



import org.mapstruct.Mapper;

import com.nt.dto.VehicleDTO;
import com.nt.model.Vehicle;

@Mapper(componentModel = "spring")
public interface VehicleMapper {
		

		
	Vehicle toVehicle(VehicleDTO vehicledto);	
	
	
}
