package com.nt.dto;

import com.nt.model.Role;

import lombok.Data;

@Data
public class UserDTO {

	  private Integer id;
	  private String email;
	  private String password;
	  private String firstname;
	  private String lastname;
	  private  Role roles;
}
