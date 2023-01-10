package com.nt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = "com.nt")
public class VehicleProjectSipl2Application {

	public static void main(String[] args) {
		SpringApplication.run(VehicleProjectSipl2Application.class, args);
	}

}
