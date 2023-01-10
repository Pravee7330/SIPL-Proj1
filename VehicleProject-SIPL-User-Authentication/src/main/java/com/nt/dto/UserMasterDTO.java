package com.nt.dto;

import java.util.List;

import com.nt.model.RoleMaster;

import lombok.Data;

@Data
public class UserMasterDTO {

	  private Integer id;
	  private String username;
	  private String password;
	  private String firstname;
	  private String lastname;

	 private  List<RoleMaster> role;
}
