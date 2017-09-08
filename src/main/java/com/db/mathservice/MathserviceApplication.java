package com.db.mathservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MathserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MathserviceApplication.class, args);
	}
}
