package com.nt.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.nt.listner.JobexecutionListner;
import com.nt.model.Employee;
import com.nt.processor.EmployeeInfoItemProcessor;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	
	@Autowired
	private JobBuilderFactory jobfactory;
	
	@Autowired
	private StepBuilderFactory stepfactory;

	
	@Autowired
	private EmployeeInfoItemProcessor processor;
	
	@Autowired
	private JobexecutionListner listner;

	@Autowired
	private DataSource ds;
	 
	
	
	@Bean(name="ffiReader")
	 public FlatFileItemReader<Employee> createFFIReader(){
		 
		 FlatFileItemReader<Employee> reader= new FlatFileItemReader<Employee>();
		 
//		 reader.setResource(new PathResource("e:\\Employee_Info.csv"));
		 reader.setResource(new ClassPathResource("Employee_Info.csv"));
		 
		 DefaultLineMapper<Employee> mapper= new DefaultLineMapper<Employee>();
		 
		 DelimitedLineTokenizer tokenizer=new DelimitedLineTokenizer();
		 
		 tokenizer.setDelimiter(",");
		 
		 tokenizer.setNames("empno","ename","eadd","salary");
		 
		 BeanWrapperFieldSetMapper<Employee> fieldsetmapper= new BeanWrapperFieldSetMapper<Employee>();
		 fieldsetmapper.setTargetType(Employee.class);
		 
		 
		 mapper.setFieldSetMapper(fieldsetmapper);
		 mapper.setLineTokenizer(tokenizer);
		 
		 reader.setLineMapper(mapper);
		 
		 
		 
		return reader;
		 
		 
		 
	 }
	
	
	@Bean(name="jbiw")
	public JdbcBatchItemWriter<Employee> createJBIWriter(){
		
		
	    JdbcBatchItemWriter<Employee> writer= new JdbcBatchItemWriter<Employee>();
	    
	    writer.setDataSource(ds);
	    
	    writer.setSql("INSERT INTO EMPLOYEE_BATCH VALUES(:empno,:ename,:eadd,:salary,:grossSalary,:netSal)");
		
	    BeanPropertyItemSqlParameterSourceProvider<Employee>  iteamprovider= new BeanPropertyItemSqlParameterSourceProvider<Employee>();
	    
	    writer.setItemSqlParameterSourceProvider(iteamprovider);
		
		
		return writer;
		
	}
	
	
	
	@Bean(name="step1")
	public Step createStep1() {
		 return stepfactory.get("step1")
				    .<Employee,Employee>chunk(5)
				    .reader(createFFIReader())
				    .processor(processor)
				    .writer(createJBIWriter())
	             	.build();
		
	}
	
	@Bean(name="job1")
	 public Job createJob1() {
		       return jobfactory.get("job1")
		    		   .listener(listner)
		    		   .incrementer(new RunIdIncrementer())
		    		   .start(createStep1())
		    		   .build();
	 }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
