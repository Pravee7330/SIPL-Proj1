package com.nt.service;



import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nt.dto.AuthReq;
import com.nt.dto.UserMasterDTO;
import com.nt.response.UserApiResponse;

@Service
public interface IUserMgmtService  {

	public UserApiResponse addUser(UserMasterDTO userdto) ;
	
	public UserApiResponse updateUser(UserMasterDTO userdto);
	 
	 public UserApiResponse deleteUser(int id);

		public ResponseEntity<?> generate(AuthReq authReq);

	public Object getAllUser();
	 
}
