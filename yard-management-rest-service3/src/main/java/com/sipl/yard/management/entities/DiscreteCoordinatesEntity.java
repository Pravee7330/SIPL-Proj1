package com.sipl.yard.management.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "discrete_coordinate_table")
public class DiscreteCoordinatesEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "rover_id")
	private Integer roverId;

	@Column(name = "latitude")
	private Double latitude;

	@Column(name = "longitude")
	private Double longitude;

	@Column(name = "altitude")
	private Double altitude;

	@Column(name = "date")
	private LocalDate date;

	@Column(name = "time")
	private LocalTime time;

	@Column(name = "isManual")
	private Boolean isManual;

	@Column(name = "fixed")
	private Boolean fixed;

	@Column(name = "quality")
	private String quality;

	@Column(name = "satellites")
	private Integer satellites;

	@Column(name = "hdop")
	private Float hdop;

	@Column(name = "transaction_sequence")
	private Long transactionSequence;
}
