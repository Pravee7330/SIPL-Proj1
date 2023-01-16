package com.sipl.yard.management.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.sipl.yard.management.dtos.RoleDto;
import com.sipl.yard.management.entities.RoleMaster;

@Mapper(componentModel = "spring")
public interface RoleMasterMapper {
	
	RoleDto mapRoleToRoleDto(RoleMaster roleMaster);

	RoleMaster mapRoleDtoToRole(RoleDto roleDto);

	List<RoleDto> roleListToRoleDtoList(List<RoleMaster> roleList);


}
