package com.sipl.yard.management.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "container_placement_table")
public class ContainerPosition {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "container_number")
	private String containerNumber;

	@ManyToOne
	@JoinColumn(name = "container_master_id")
	private ContainerMaster containerMaster;

	@Column(name = "vehicle_id")
	private String vehicleId;

	@Column(name = "pickup_date")
	private LocalDate pickupDate;

	@Column(name = "pickup_time")
	private LocalTime pickupTime;

	@Column(name = "pickup_from")
	private String pickupFrom;

	@Column(name = "pickup_yard")
	private String pickupYard;

	@Column(name = "pickup_row")
	private Integer pickupRow;

	@Column(name = "pickup_column")
	private Integer pickupColumn;

	@Column(name = "pickup_tier")
	private Integer pickupTier;

	@Column(name = "drop_date")
	private LocalDate dropDate;

	@Column(name = "drop_time")
	private LocalTime dropTime;

	@Column(name = "drop_yard")
	private String dropYard;

	@Column(name = "drop_row")
	private Integer dropRow;

	@Column(name = "drop_column")
	private Integer dropColumn;

	@Column(name = "drop_tier")
	private Integer dropTier;

	@Column(name = "extra_row")
	private Integer extraRow;

	@Column(name = "extra_col")
	private Integer extraCol;

	@OneToOne
	@JoinColumn(name = "container_info_id")
	private ContainerInfoDetails containerInfoDetails;

	@Column(name = "is_send_to_tos")
	private Boolean isSendToTos;

	@ManyToOne
	@JoinColumn(name = "yard_id")
	private YardMaster yardMaster;

	@Embedded
	private AuditEntity auditEntity;
}










