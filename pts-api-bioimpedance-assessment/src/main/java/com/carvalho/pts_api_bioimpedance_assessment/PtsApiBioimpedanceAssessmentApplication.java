package com.carvalho.pts_api_bioimpedance_assessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PtsApiBioimpedanceAssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(PtsApiBioimpedanceAssessmentApplication.class, args);
	}

}
