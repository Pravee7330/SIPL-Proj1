package com.nt.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamResult {


	private Integer id;
	 private Date dob;
	 private Integer semester;
	 private Double percentage;
}
