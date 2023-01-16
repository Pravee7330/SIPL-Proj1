package com.sipl.yard.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sipl.yard.management.entities.RoleMaster;
import com.sipl.yard.management.mapper.RoleMasterMapper;
import com.sipl.yard.management.repository.RoleRespository;
import com.sipl.yard.management.responses.RoleApiResponse;
import com.sipl.yard.management.service.RoleService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRespository roleRepository;

	@Autowired
	private RoleMasterMapper roleMasterMapper;

	@Cacheable("getAllRoleMaster")
	@Override
	public RoleApiResponse getAll() {
		log.info("<<start>>In getAll method<<start>>");
		try {
			List<RoleMaster> roleMasters = (List<RoleMaster>) roleRepository.findAll();
			if (!roleMasters.isEmpty() ) {
				return new RoleApiResponse(null, roleMasterMapper.roleListToRoleDtoList(roleMasters), HttpStatus.FOUND,
						"Role master found successfully", false);
			} else {
				return new RoleApiResponse(null, null, HttpStatus.NOT_FOUND, "Records not found", true);
			}
		} catch (Exception e) {
			log.error("Exception in getAll ", e);
		}
		return new RoleApiResponse(null, null, HttpStatus.INTERNAL_SERVER_ERROR, "Server error", true);
	}

}
