package com.sipl.yard.management.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.sipl.yard.management.dtos.ContainerMasterDto;
import com.sipl.yard.management.entities.ContainerMaster;

@Mapper(componentModel = "spring")
public interface ContainerMasterMapper {

	ContainerMasterDto mapContainerMasterToContainerMasterDto(ContainerMaster containerMaster);

	ContainerMaster mapContainerMasterDtoToContainerMaster(ContainerMasterDto containerMasterDto);

	List<ContainerMasterDto> containerMasterListToContainerMasterDtoList(List<ContainerMaster> containerMasterList);

}
