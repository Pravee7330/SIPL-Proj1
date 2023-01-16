package com.sipl.yard.management.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class YardMasterDto {
	
	private int id;
	private String yard;
	private int rows;
	private int columns;
	private int tier;

}
