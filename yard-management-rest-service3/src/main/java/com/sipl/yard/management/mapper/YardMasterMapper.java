package com.sipl.yard.management.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.sipl.yard.management.dtos.YardMasterDto;
import com.sipl.yard.management.entities.YardMaster;

@Mapper(componentModel = "spring")
public interface YardMasterMapper {

	YardMasterDto mapYardMasterToYardMasterDto(YardMaster yardMaster);

	YardMaster mapYardMasterDtoToYardMaster(YardMasterDto yardMasterDto);

	List<YardMasterDto> yardMasterListToYardMasterDtoList(List<YardMaster> yardMasterList);

}
