package com.sipl.yard.management.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.sipl.yard.management.dtos.ContainerPositionDto;
import com.sipl.yard.management.entities.ContainerPosition;

@Mapper(componentModel = "spring")
public interface ContainerPositionMapper {
	
	ContainerPositionDto mapContainerPositionToContainerPositionDto(ContainerPosition containerPosition);

	ContainerPosition mapContainerPositionDtoToContainerPosition(ContainerPositionDto containerPositionDto);
	
	List<ContainerPositionDto> containerPositionListToContainerPositionDtoList(
			List<ContainerPosition> containerPositionList);
	
}
