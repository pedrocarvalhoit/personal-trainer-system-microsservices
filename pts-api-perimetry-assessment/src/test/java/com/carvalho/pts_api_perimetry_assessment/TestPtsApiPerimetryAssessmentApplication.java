package com.carvalho.pts_api_perimetry_assessment;

import org.springframework.boot.SpringApplication;

public class TestPtsApiPerimetryAssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.from(PtsApiPerimetryAssessmentApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
