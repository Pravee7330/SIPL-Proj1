package com.sipl.yard.management.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleMasterDto {
	private int id;
	private String vehicleId;
	private String type;
}
