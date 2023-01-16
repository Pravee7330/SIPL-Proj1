package com.sipl.yard.management.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

import com.sipl.yard.management.entities.AuditEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContinuousCoordinatesEntityDto {

	private Long id;
	private String vehicleId;
	private Double latitude;
	private Double longitude;
	private float altitude;
	private LocalTime coordinateTime;
	private LocalDate date;
	private LocalTime time;
	private AuditEntity auditEntity;

}
