package com.carvalho.pts_api_authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PtsApiAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(PtsApiAuthenticationApplication.class, args);
	}

}
