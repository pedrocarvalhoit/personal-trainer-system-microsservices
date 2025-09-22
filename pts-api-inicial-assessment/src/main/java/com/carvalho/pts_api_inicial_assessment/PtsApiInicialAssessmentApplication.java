package com.carvalho.pts_api_inicial_assessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PtsApiInicialAssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(PtsApiInicialAssessmentApplication.class, args);
	}

}
