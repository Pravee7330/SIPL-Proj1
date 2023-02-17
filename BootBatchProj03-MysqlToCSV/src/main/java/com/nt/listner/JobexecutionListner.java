package com.nt.listner;

import java.util.Date;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class JobexecutionListner implements JobExecutionListener {

	private long start, end;
	
	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("JobexecutionListner.beforeJob()");
		 start=System.currentTimeMillis();
		 System.out.println("job starts at time @ "+new Date());
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("JobexecutionListner.afterJob()");
		end=System.currentTimeMillis();
		System.out.println("job start at time @"+new Date());
		System.out.println(" job execution time :: "+(end-start)+" ms ");
		System.out.println(" job status :: "+jobExecution.getStatus());

	}

}
