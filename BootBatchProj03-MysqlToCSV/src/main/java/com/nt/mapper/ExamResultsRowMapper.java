package com.nt.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.nt.model.ExamResult;

@Component
public class ExamResultsRowMapper implements RowMapper<ExamResult> {

	@Override
	public ExamResult mapRow(ResultSet rs, int rowNum) throws SQLException {
		 return new ExamResult(rs.getInt(1),
                 rs.getDate(2),
                 rs.getInt(3),
                 rs.getDouble(4)
           
);
	
	}
	RowMapper<ExamResult> mapper=(rs,rowNum)->{
		 return new ExamResult(rs.getInt(1),
				                                       rs.getDate(2),
				                                       rs.getInt(3),
				                                       rs.getDouble(4)
				                                 
				 );
		
	};
	
	
}
