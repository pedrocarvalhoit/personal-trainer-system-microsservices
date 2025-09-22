package com.carvalho.pts_api_athlete_recomendations;

import org.springframework.boot.SpringApplication;

public class TestPtsApiAthleteRecomendationsApplication {

	public static void main(String[] args) {
		SpringApplication.from(PtsApiBioimpedanceRecomendationApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
