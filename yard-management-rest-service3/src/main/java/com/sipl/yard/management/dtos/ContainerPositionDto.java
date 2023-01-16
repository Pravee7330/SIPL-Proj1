package com.sipl.yard.management.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

import com.sipl.yard.management.entities.AuditEntity;
import com.sipl.yard.management.entities.ContainerInfoDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContainerPositionDto {
	private Long id;
	private String containerNumber;
	private String vehicleId;
	private LocalDate pickupDate;
	private LocalTime pickupTime;
	private String pickupFrom;
	private String pickupYard;
	private Integer pickupRow;
	private Integer pickupColumn;
	private Integer pickupTier;
	private LocalDate dropDate;
	private LocalTime dropTime;
	private String dropYard;
	private Integer dropRow;
	private Integer dropColumn;
	private Integer dropTier;
	private Integer extraRow;
	private Integer extraCol;
	private ContainerInfoDetails containerInfoDetails;
	private Boolean isSendToTos;
	private YardMasterDto yardMaster;
	private AuditEntity auditEntity;
}
