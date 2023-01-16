package com.sipl.yard.management.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {

	private Integer id;
	private String name;
	private String description;
	private String createdBy;
	private String modifiedBy;
	private LocalDateTime creationTime;
	private LocalDateTime modifiedTime;
}
