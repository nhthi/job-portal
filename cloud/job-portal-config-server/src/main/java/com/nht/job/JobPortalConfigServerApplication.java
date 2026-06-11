package com.nht.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class JobPortalConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobPortalConfigServerApplication.class, args);
	}

}
