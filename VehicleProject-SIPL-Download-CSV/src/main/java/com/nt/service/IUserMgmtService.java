package com.nt.service;

import org.springframework.stereotype.Service;

import com.nt.dto.UserDTO;
import com.nt.response.VehicleApiResponse;

@Service
public interface IUserMgmtService {

	public VehicleApiResponse add(UserDTO userdto) ;
}
