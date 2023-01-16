package com.sipl.yard.management.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class ContainerMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int size;
	private String isoCode;
	private float length;
	private float width;
	private float height;
	private float weight;

}
