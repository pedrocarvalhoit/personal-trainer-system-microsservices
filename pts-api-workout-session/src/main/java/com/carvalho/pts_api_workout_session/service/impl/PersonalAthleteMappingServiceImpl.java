package com.carvalho.pts_api_workout_session.service.impl;

import com.carvalho.pts_api_workout_session.adapter.Adapter;
import com.carvalho.pts_api_workout_session.dto.PersonalTrainerAndAthleteMappingDto;
import com.carvalho.pts_api_workout_session.repository.PersonalAthleteMappingRepository;
import com.carvalho.pts_api_workout_session.service.PersonalAthleteMappingService;
import org.springframework.stereotype.Service;

@Service
public class PersonalAthleteMappingServiceImpl implements PersonalAthleteMappingService {

    private final Adapter adapter;
    private final PersonalAthleteMappingRepository repository;

    public PersonalAthleteMappingServiceImpl(Adapter adapter, PersonalAthleteMappingRepository repository) {
        this.adapter = adapter;
        this.repository = repository;
    }

    @Override
    public void saveMappingPersonalAthlete(PersonalTrainerAndAthleteMappingDto event) {
        repository.save(adapter.modelToMapperEntity(event));
    }
}
