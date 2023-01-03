package com.nt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = "com.nt")
@EnableSwagger2
public class SiplVehicleProj1Application {

	public static void main(String[] args) {
		SpringApplication.run(SiplVehicleProj1Application.class, args);
	}

}
