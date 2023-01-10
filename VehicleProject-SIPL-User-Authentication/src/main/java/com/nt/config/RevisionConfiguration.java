package com.nt.config;



import javax.persistence.EntityManagerFactory;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

@Configuration

public class RevisionConfiguration {

	
	
	private final EntityManagerFactory entityManagerFactory;
	
	public RevisionConfiguration(EntityManagerFactory entityManagerFactory) {
	this.entityManagerFactory = entityManagerFactory;
	}
	@Bean
	AuditReader auditReader() {
	return AuditReaderFactory.get(entityManagerFactory.createEntityManager());
	}

	
	@Bean
	public RestTemplate restTemplate() {
	          return new RestTemplate();
	}

	
	@Bean
	  public BCryptPasswordEncoder createPwdEncoder() {
		  return new BCryptPasswordEncoder();
	  }
}
