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
@Table(name = "raw_coordinate_table")
public class RawCoordinatesEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "rover_id")
	private int roverId;
	
	@Column(name = "raw_coordinates")
	private String coordinates;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "date")
	private LocalDate date;
	
	@Column(name = "time")
	private LocalTime time;
	
	@Column(name = "transaction_sequence")
	private long transactionSequence;
}
