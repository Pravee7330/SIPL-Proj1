package com.sipl.yard.management.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContainerMasterDto {
	private int id;
	private int size;
	private String isoCode;
	private float length;
	private float width;
	private float height;
	private float weight;
}
