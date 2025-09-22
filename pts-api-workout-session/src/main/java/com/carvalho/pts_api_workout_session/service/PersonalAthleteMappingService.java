package com.carvalho.pts_api_workout_session.service;

import com.carvalho.pts_api_workout_session.dto.PersonalTrainerAndAthleteMappingDto;

public interface PersonalAthleteMappingService {

    void saveMappingPersonalAthlete(PersonalTrainerAndAthleteMappingDto event);

}
