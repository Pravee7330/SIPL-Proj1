package com.sipl.yard.management.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sipl.yard.management.dtos.UserMasterDto;
import com.sipl.yard.management.entities.RoleMaster;
import com.sipl.yard.management.entities.UserMaster;
import com.sipl.yard.management.mapper.UserMasterMapper;
import com.sipl.yard.management.repository.UserRespository;
import com.sipl.yard.management.responses.UserApiResponse;
import com.sipl.yard.management.service.UserMasterService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserMasterServiceImpl implements UserMasterService {

	@Autowired
	private UserRespository userRepository;

	@Autowired
	private UserMasterMapper userMasterMapper;

	public UserApiResponse validateUserMaster(String userId, String password) {
		log.info("<<start>>validateUserMaster<<start>>");
		final Optional<UserMaster> userMasterFromDb = userRepository.findByActiveUserName(userId);
		log.debug("UserMaster validate endpoint response from DB: " + userMasterFromDb);
		if (userMasterFromDb.isPresent()) {
			final UserMaster userMaster = userMasterFromDb.get();
			if (userMaster.getPassword().equals(password)) {
				final UserApiResponse userApiResponseResponseEntity = new UserApiResponse(
						userMasterMapper.mapUserMasterToUserMasterDto(userMaster), null, HttpStatus.OK,
						"User validated successfully", false);

				log.debug("UserMaster validate, sending response back: " + userApiResponseResponseEntity);
				return userApiResponseResponseEntity;
			} else {
				return new UserApiResponse(null, null, HttpStatus.UNAUTHORIZED, "Invalid Password", true);
			}
		}
		log.info("<<end>>validateUserMaster<<end>>");
		return new UserApiResponse(null, null, HttpStatus.UNAUTHORIZED, "User Doesn't Exists", true);
	}

	@CacheEvict(value = "getAllUserMaster", allEntries = true)
	@Override
	public UserApiResponse add(UserMasterDto userMasterDto) {
		log.info("<<start>>In add method<<start>>");
		try {
			if (userRepository.findByActiveUserName(userMasterDto.getUsername()).isPresent()) {
				return new UserApiResponse(null, null, HttpStatus.ALREADY_REPORTED,
						userMasterDto.getUsername() + " already present", true);
			} else {
				userMasterDto.setCreationTime(LocalDateTime.now());
				UserMaster dataFromdb = userRepository
						.save(userMasterMapper.mapUserMasterDtoToUserMaster(userMasterDto));
				return new UserApiResponse(userMasterMapper.mapUserMasterToUserMasterDto(dataFromdb), null,
						HttpStatus.CREATED, "user added successfully", false);
			}
		} catch (Exception e) {
			log.error("Exception in add method of UserMaster",e);
		}
		return new UserApiResponse(null, null, HttpStatus.INTERNAL_SERVER_ERROR, "Server Error", true);
	}
	
	@CacheEvict(value = "getAllUserMaster", allEntries = true)
	@Override
	public UserApiResponse update(UserMasterDto userMasterDto) {
		log.info("<<start>>In update method<<start>>");
		try {
			Optional<UserMaster> userMasterFatchFromDb = userRepository.findById(userMasterDto.getId());
			if (userMasterFatchFromDb.isPresent()) {
				UserMaster userDetailsFromDb = userMasterFatchFromDb.get();
				if (userMasterDto.getPassword() != null) {
					userDetailsFromDb.setPassword(userMasterDto.getPassword() );
				}
				userDetailsFromDb.setActive(userMasterDto.getActive());
				userDetailsFromDb.setModifiedBy(userMasterDto.getModifiedBy());
				userDetailsFromDb.setModifiedTime(LocalDateTime.now());
				if (userMasterDto.getId() != null) {
					RoleMaster roleMaster = new RoleMaster();
					roleMaster.setId(userMasterDto.getRoleId());
					userDetailsFromDb.setRoleMaster(roleMaster);
				}
				UserMaster updateduser = userRepository.save(userDetailsFromDb);
				log.info("<<end>>update<<end>>");
				return new UserApiResponse(userMasterMapper.mapUserMasterToUserMasterDto(updateduser), null,
						HttpStatus.OK, "user Details Updated successfully", false);

			} else {
				return new UserApiResponse(null, null, HttpStatus.NOT_FOUND, "User Id Does Not Exists",
						true);
			}
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
			log.error("user updateuser JDBCConnectionException: ", e);
			return new UserApiResponse(null, null, HttpStatus.INTERNAL_SERVER_ERROR, "Server Error", true);
		}

	}
	
	@CacheEvict(value = "getAllUserMaster", allEntries = true)
	@Override
	public UserApiResponse deleteById(UserMasterDto userMasterDto) {
		log.info("<<start>>In deleteById method<<start>>");
		try {
			int id = userMasterDto.getId();
			log.debug("In deleteById method Id: " + id);
			final Optional<UserMaster> userFetchedFromDb = userRepository.findById(id);
			log.debug("user Details Fetched From Db " + userFetchedFromDb);
			if (userFetchedFromDb.isPresent()) {
				UserMaster userMaster = userFetchedFromDb.get();
				userRepository.deleteById(id);
				return new UserApiResponse(userMasterMapper.mapUserMasterToUserMasterDto(userMaster), null,
						HttpStatus.OK, "user " + userMaster.getUsername() + " deleted successfully", false);
			} else {
				log.info("<<end>>In deleteById method<<end>>");
				return new UserApiResponse(null, null, HttpStatus.NOT_FOUND, "User id doesn't exists", true);
			}
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
			log.error("user deleteById JDBCConnectionException:", e);
		} catch (final Exception e) {
			log.error("user deleteById Exception: ", e);
		}
		return new UserApiResponse(null, null, HttpStatus.INTERNAL_SERVER_ERROR, "Server Error", true);
	}

	@Cacheable("getAllUserMaster")
	@Override
	public UserApiResponse getAll() {
		log.info("<<start>>In getAll method<<start>>");
		try {
			List<UserMaster> userMasters = (List<UserMaster>) userRepository.findAll();
			if (!userMasters.isEmpty()) {
				return new UserApiResponse(null,userMasterMapper.userMasterListToUserMasterDtoList(userMasters),
						HttpStatus.FOUND, "User master found successfully", false);
			} else {
				return new UserApiResponse(null, null, HttpStatus.NOT_FOUND, "Records not found", true);
			}
		} catch (Exception e) {
			log.error("Exception in getAll ", e);
		}
		return new UserApiResponse(null, null, HttpStatus.INTERNAL_SERVER_ERROR, "Server error", true);
	}
}
