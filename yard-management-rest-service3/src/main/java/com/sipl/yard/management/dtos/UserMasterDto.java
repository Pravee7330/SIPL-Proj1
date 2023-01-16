package com.sipl.yard.management.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMasterDto {

	private Integer id;
	private String username;
	private String password;
	private Boolean active;
	private Integer roleId;
	private String roleName;
	private String createdBy;
	private LocalDateTime creationTime;
	private String modifiedBy;
	private LocalDateTime modifiedTime;

}
