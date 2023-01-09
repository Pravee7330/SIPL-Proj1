package com.nt.mapper;



import java.util.List;

import org.mapstruct.Mapper;

import com.nt.dto.VehicleDTO;
import com.nt.model.Vehicle;

@Mapper(componentModel = "spring")
public interface VehicleMapper {
		

	Vehicle mapVehicleDtoToVehicle(VehicleDTO vehicledto);
	
	

	List<VehicleDTO> mapVehicleListToVehicleDtoList(List<Vehicle> findAll);

	VehicleDTO mapVehicleToVehicleDto(Vehicle vehicle);
	

	//List<VehicleDTO> mapVehicleListToVehicleDtoList(List<Vehicle> vehicles);
	
//	Vehicle toVehicle(VehicleDTO vehicledto);	
	
	
}
