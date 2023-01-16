package com.sipl.yard.management.service;

import org.springframework.stereotype.Service;

import com.sipl.yard.management.responses.RoleApiResponse;

@Service
public interface RoleService {
	
	public RoleApiResponse getAll();

}
