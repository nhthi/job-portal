package com.nht.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class JobPortalApplicationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobPortalApplicationServiceApplication.class, args);
	}

}
