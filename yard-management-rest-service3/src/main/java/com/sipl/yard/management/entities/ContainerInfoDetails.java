package com.sipl.yard.management.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@Entity
@ToString
@Table(name = "container_info_details")
public class ContainerInfoDetails implements Serializable {
	private static final long serialVersionUID = 1174342617962341559L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "unique_transaction_id", unique = true, nullable = false)
	private String uniqueTransactionId;

	@Column(name = "vehicle_id")
	private String vehicleId;

	@Column(name = "container_no")
	private String containerNumber;

	@ManyToOne
	@JoinColumn(name = "container_master_id")
	private ContainerMaster containerMaster;

	@Column(name = "timestamp")
	private LocalDateTime timestamp;
}
