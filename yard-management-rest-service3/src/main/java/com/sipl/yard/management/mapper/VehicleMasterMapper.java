package com.sipl.yard.management.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.sipl.yard.management.dtos.VehicleMasterDto;
import com.sipl.yard.management.entities.VehicleMaster;

@Mapper(componentModel = "spring")
public interface VehicleMasterMapper {
	VehicleMasterDto mapVehicleMasterToVehicleMasterDto(VehicleMaster vehicleMaster);

	VehicleMaster mapVehicleMasterDtoToVehicleMaster(VehicleMasterDto vehicleMasterDto);

	List<VehicleMasterDto> vehicleMasterListToVehicleMasterDtoList(List<VehicleMaster> vehicleMasterList);
}
