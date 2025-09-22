package com.carvalho.pts_api_perimetry_assessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PtsApiPerimetryAssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(PtsApiPerimetryAssessmentApplication.class, args);
	}

}
