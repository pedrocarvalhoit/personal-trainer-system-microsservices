package com.carvalho.pts_api_user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PtsApiUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(PtsApiUserApplication.class, args);
	}

}
