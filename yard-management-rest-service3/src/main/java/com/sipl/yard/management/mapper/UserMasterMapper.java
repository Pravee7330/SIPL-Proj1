package com.sipl.yard.management.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import com.sipl.yard.management.dtos.UserMasterDto;
import com.sipl.yard.management.entities.UserMaster;

@Mapper(componentModel = "spring")
public interface UserMasterMapper {

	@Mapping(target = "roleId", source = "roleMaster.id")
	@Mapping(target = "roleName", source = "roleMaster.name") 
	UserMasterDto mapUserMasterToUserMasterDto(UserMaster userMasterByUserMasterId);

	@Mapping(target = "roleMaster.id", source = "roleId")
	UserMaster mapUserMasterDtoToUserMaster(UserMasterDto userMasterDto);

	List<UserMasterDto> userMasterListToUserMasterDtoList(List<UserMaster> userMasterList);

	default Page<UserMasterDto> mapUserMasterToUserMasterDto(Page<UserMaster> userMasterFetchedFromDb) {
		return userMasterFetchedFromDb.map(this::mapUserMasterToUserMasterDto);
	}

}
