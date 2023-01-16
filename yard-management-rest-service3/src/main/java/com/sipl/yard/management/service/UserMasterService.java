package com.sipl.yard.management.service;

import org.springframework.stereotype.Service;

import com.sipl.yard.management.dtos.UserMasterDto;
import com.sipl.yard.management.responses.UserApiResponse;

@Service
public interface UserMasterService {

	public UserApiResponse validateUserMaster(String userId, String password);

	public UserApiResponse deleteById(UserMasterDto userMasterDto);

	public UserApiResponse update(UserMasterDto userMasterDto);

	public UserApiResponse add(UserMasterDto userMasterDto);

	public UserApiResponse getAll();

}
