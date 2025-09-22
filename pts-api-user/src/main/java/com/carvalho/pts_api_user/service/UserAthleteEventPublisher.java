package com.carvalho.pts_api_user.service;

import com.carvalho.pts_api_user.dto.PersonalTrainerAndAthleteMappingDto;
import com.carvalho.pts_api_user.dto.UserAthleteCreatedEventDto;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface UserAthleteEventPublisher {

    void publish(UserAthleteCreatedEventDto userAthleteCreatedEventDto) throws JsonProcessingException;

    void publishToPersonalAthleteMapping(PersonalTrainerAndAthleteMappingDto dto) throws JsonProcessingException;

}
