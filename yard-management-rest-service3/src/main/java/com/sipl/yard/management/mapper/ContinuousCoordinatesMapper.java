package com.sipl.yard.management.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.sipl.yard.management.dtos.ContinuousCoordinatesEntityDto;
import com.sipl.yard.management.entities.ContinuousCoordinatesEntity;

@Mapper(componentModel = "spring")
public interface ContinuousCoordinatesMapper {

	ContinuousCoordinatesEntityDto mapContinuousCoordinatesEntityToContinuousCoordinatesEntityDto(ContinuousCoordinatesEntity continuousCoordinatesEntity);

	ContinuousCoordinatesEntity mapContinuousCoordinatesEntityDtoToContinuousCoordinatesEntity(ContinuousCoordinatesEntityDto continuousCoordinatesEntityDto);
	
	List<ContinuousCoordinatesEntityDto> continuousCoordinatesEntityListToContinuousCoordinatesEntityDtoList(
			List<ContinuousCoordinatesEntity> continuousCoordinatesEntityList);
}
