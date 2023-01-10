package com.nt.service.impl;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.nt.Util.JwtUtil;
import com.nt.dto.AuthReq;
import com.nt.dto.UserMasterDTO;
import com.nt.mapper.UserMapper;
import com.nt.model.UserMaster;
import com.nt.repository.IUserMasterRepo;
import com.nt.response.AuthApiResp;
import com.nt.response.UserApiResponse;
import com.nt.service.IUserMgmtService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class IUserMasterServiceImpl implements IUserMgmtService  {

	@Autowired
	IUserMasterRepo repo;

	@Autowired
	UserMapper userMapper;

	@Autowired
  private 	JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsService detailsService;
	
	
//	@Autowired
//	BCryptPasswordEncoder bCryptPasswordEncoder;

//	Add User
	public UserApiResponse addUser(UserMasterDTO userMasterDto) {
		log.info("<<start>>In addUser method<<start>>");
		try {
			log.debug("In addUser method ");
			if (Optional.ofNullable(repo.findByUsername(userMasterDto.getUsername())).isPresent()) {
				log.info("<<end>>In addUser method<<end>>");
				return new UserApiResponse(null, null, HttpStatus.ALREADY_REPORTED, "Username Aleady exists", true);
			} else {
				UserMaster userMaster = userMapper.mapUserMasterDTOToUserMaster(userMasterDto);
//				userMaster.setPassword(bCryptPasswordEncoder.encode(userMasterDto.getPassword()));
				repo.save(userMaster);
				log.debug("User saved to Db " + userMaster);
				UserMasterDTO userResponse = userMapper.mapUserMasterToUserMasterDTO(userMaster);
				userResponse.setPassword(null);
				return new UserApiResponse(userResponse, null, HttpStatus.CREATED, "Your data is added", false);
			}
		} catch (Exception e) {
			log.error("User addUser Exception: ", e);
		}
		return new UserApiResponse(null, null, HttpStatus.INTERNAL_SERVER_ERROR, "Error Occured while adding data", true);
	}

//	GetAllUsers
	public UserApiResponse getAllUser() {
		log.info("<<start>>In getAllUser method<<start>>");
		try {
			log.debug("In getAllUser method ");
			List<UserMaster> userList = repo.findAll();
			if (userList.isEmpty()) {
				log.info("<<end>>In getAllUser method<<end>>");
				return new UserApiResponse(null, null, HttpStatus.NOT_FOUND, "No Data Exists", true);
			} else {

				// List<UserMaster> userList = userList.stream().map(u -> u.setPassword(null));
				for (UserMaster user : userList) {
					user.setPassword(null);
				}
				List<UserMasterDTO> userDtoList = userMapper.mapUserMasterListToUserMasterDtoList(userList);

				return new UserApiResponse(null, userDtoList, HttpStatus.FOUND, "All Users list", false);
			}
		} catch (Exception e) {
			log.error("User getAllUser Exception: ", e);
		}
		return new UserApiResponse(null, null, HttpStatus.INTERNAL_SERVER_ERROR, "Error Occured while getting data", true);
	}

//	Update User
	public UserApiResponse updateUser(UserMasterDTO userMasterDto) {
		log.info("<<start>>In updateUser method<<start>>");
		try {
			Optional<UserMaster> findById = repo.findById(userMasterDto.getId());
			log.debug("In updateUser method user: " + findById);
			if (Optional.of(findById).isEmpty()) {
				log.info("<<end>>In updateUser method<<end>>");
				return new UserApiResponse(null, null, HttpStatus.NOT_FOUND,
						"No user found with " + userMasterDto.getUsername(), true);
			} else {

				UserMaster updatedUser = userMapper.mapUserMasterDTOToUserMaster(userMasterDto);
				updatedUser.setId(findById.get().getId());
				updatedUser.setUsername(findById.get().getUsername());
				repo.save(updatedUser);

				return new UserApiResponse(userMapper.mapUserMasterToUserMasterDTO(updatedUser), null, HttpStatus.OK,
						"User Updated", false);
			}
		} catch (Exception e) {
			log.error("User updateUser Exception: ", e);
		}
		return new UserApiResponse(null, null, HttpStatus.INTERNAL_SERVER_ERROR, "Error Occured while updating data",
				true);
	}

//	Delete User
	public UserApiResponse deleteUser(int id) {
		log.info("<<start>>In deleteUser method<<start>>");
		try {
			Optional<UserMaster> findById = repo.findById(id);
			log.debug("In deleteUser method user: " + findById);
			if (Optional.of(findById).isPresent()) {
				log.debug("User Details Fetched From Db ");
				repo.delete(findById.get());
				log.debug("User Details Deleted From Db ");
				return new UserApiResponse(null, null, HttpStatus.OK, "User Deleted Successfully", false);
			} else {
				log.info("<<end>>In deleteUser method<<end>>");
				return new UserApiResponse(null, null, HttpStatus.NOT_FOUND, "No user found with " + findById, true);
			}

		} catch (Exception e) {
			log.error("User deleteUser Exception: ", e);
		}
		return new UserApiResponse(null, null, HttpStatus.INTERNAL_SERVER_ERROR, "Error Occured while deleting Data",
				true);
	}

	//Generate
	public ResponseEntity<?> generate(AuthReq authReq) throws Exception {
		try {
			authenticationManager.authenticate(new 
					UsernamePasswordAuthenticationToken(authReq.getUsername(), authReq.getPassword()));

		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect Username Password" , e);	
		}
		
		final UserDetails userdetails = detailsService.loadUserByUsername(authReq.getUsername());
		
		final String jwt = jwtUtil.generateToken(userdetails);
		
		return ResponseEntity.ok( new AuthApiResp(jwt));
	}
}
