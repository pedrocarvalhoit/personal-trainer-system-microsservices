package com.carvalho.pts_api_bioimpedance_assessment;

import org.springframework.boot.SpringApplication;

public class TestPtsApiBioimpedanceAssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.from(PtsApiBioimpedanceAssessmentApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
