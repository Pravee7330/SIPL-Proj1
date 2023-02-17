package com.nt.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.nt.model.ExamResult;

@Component
public class ExamResultItemProcessor implements ItemProcessor<ExamResult ,ExamResult> {

	@Override
	public ExamResult process(ExamResult emp) throws Exception {
	
		  
		  
		  if(emp.getPercentage()>=90) {
			  
			ExamResult result= new ExamResult();
			
			result.setId(emp.getId());
			result.setDob(emp.getDob());
			result.setSemester(emp.getSemester());
			result.setPercentage(emp.getPercentage());
			
		      return result;
		  }
		  else {
	          	return null;
		}
	}


}
