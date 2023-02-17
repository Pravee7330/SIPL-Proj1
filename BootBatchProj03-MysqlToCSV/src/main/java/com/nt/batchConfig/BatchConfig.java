package com.nt.batchConfig;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.nt.model.ExamResult;
import com.nt.processor.ExamResultItemProcessor;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	private JobBuilderFactory  jobFactory;
	
	@Autowired
	private StepBuilderFactory   stepFactory;
	
	@Autowired
	private JobExecutionListener listner;
	@Autowired
	private ExamResultItemProcessor processor;
	
	@Autowired
	private DataSource ds;
	
	/*
	 * @Bean public JobExecutionListener listner() { return new
	 * JobExecutionListener(); }
	 */

	@Bean(name="jdir")
	public JdbcCursorItemReader<ExamResult>  createReader(){
		
		
		JdbcCursorItemReader<ExamResult> reader= new JdbcCursorItemReader<>();
		
		reader.setDataSource(ds);
		
		reader.setSql("SELECT ID,DOB,SEMESTER,PERCENTAGE FROM EXAM_RESULT");
		
		reader.setRowMapper(  ( rs, rowNumb)-> new ExamResult(
			                                                                                rs.getInt(1),
			                                                                                rs.getDate(2),
			                                                                                rs.getInt(3),
			                                                                                rs.getDouble(4)
		));
		
		return reader;
	}
	
	
	
	
	
	
	@Bean(name="ffiw")
      public FlatFileItemWriter<ExamResult> createWriter(){
    	  
    	  
		return new FlatFileItemWriterBuilder<ExamResult>()
				
				    .name("writer")
				    .resource(new FileSystemResource("e:\\TopBrains.csv"))
				    .lineSeparator("\r\n")
				    .delimited().delimiter(",")
				    .names(new String[] {"id","dob","semester","percentage"})
				    .build()
				;
    	  
    	  
    	  
      }	
	
	
	@Bean(name="job1")
	         public  Job createjob() {
				return jobFactory.get("job1")
						.listener(listner)
						.incrementer(new RunIdIncrementer())
						.start(createStep1())
						.build();
	        	 
	         }
	
	
	@Bean(name="Step1")
	public Step createStep1() {
		return stepFactory.get("Step1")
				.<ExamResult,ExamResult>chunk(300000)
				.reader(createReader())
				 .processor(processor)
				.writer( createWriter())
				.build();
				
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
