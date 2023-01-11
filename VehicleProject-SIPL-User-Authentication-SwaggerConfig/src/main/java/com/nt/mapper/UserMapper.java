package com.nt.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.nt.dto.UserMasterDTO;
import com.nt.model.UserMaster;

@Mapper(componentModel = "spring")

public interface UserMapper {
	
	UserMapper INSTANCE 	 = Mappers.getMapper(UserMapper.class);
	
	UserMaster mapUserMasterDTOToUserMaster( UserMasterDTO userMasterDto);

	List<UserMasterDTO> mapUserMasterListToUserMasterDtoList(List<UserMaster> userMasters);
	
	UserMasterDTO mapUserMasterToUserMasterDTO( UserMaster userMaster);

}
