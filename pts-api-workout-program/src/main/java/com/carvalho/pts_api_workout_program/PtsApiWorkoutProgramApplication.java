package com.carvalho.pts_api_workout_program;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
@EnableScheduling
public class PtsApiWorkoutProgramApplication {

	public static void main(String[] args) {
		SpringApplication.run(PtsApiWorkoutProgramApplication.class, args);
	}

}
