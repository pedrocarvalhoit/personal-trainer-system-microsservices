package com.carvalho.pts_api_inicial_assessment;

import org.springframework.boot.SpringApplication;

public class TestPtsApiAssessmentsApplication {

	public static void main(String[] args) {
		SpringApplication.from(PtsApiInicialAssessmentApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
