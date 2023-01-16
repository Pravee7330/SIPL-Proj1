package com.sipl.yard.management.entities;

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
@Table(name = "yard_master")
public class YardMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String yard;

	private int rows;

	private int columns;

	private int tier;

}
