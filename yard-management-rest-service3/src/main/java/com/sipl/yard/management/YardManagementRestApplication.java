package com.sipl.yard.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = ("com.sipl"))
@EnableCaching
@EnableSwagger2
public class YardManagementRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(YardManagementRestApplication.class, args);
	}
	
}
