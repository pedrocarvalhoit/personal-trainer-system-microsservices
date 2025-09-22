package com.carvalho.pts_api_workout_session.adapter;

import com.carvalho.pts_api_workout_session.dto.PersonalTrainerAndAthleteMappingDto;
import com.carvalho.pts_api_workout_session.dto.WorkoutSessioUpdateDataDto;
import com.carvalho.pts_api_workout_session.dto.WorkoutSessionDto;
import com.carvalho.pts_api_workout_session.entity.PersonalAthleteMappingEntity;
import com.carvalho.pts_api_workout_session.entity.WorkoutSessionEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class Adapter {

    public WorkoutSessionEntity modelToEntity(WorkoutSessionDto dto) {
        WorkoutSessionEntity entity = WorkoutSessionEntity.builder().build();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    public WorkoutSessionDto entityToModel(WorkoutSessionEntity entity) {
        WorkoutSessionDto dto = WorkoutSessionDto.builder().build();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public void udpateEntityFromDto(WorkoutSessionEntity entity, WorkoutSessioUpdateDataDto dto) {
        BeanUtils.copyProperties(dto, entity,  "id", "createdAt");
    }

    public PersonalAthleteMappingEntity modelToMapperEntity(PersonalTrainerAndAthleteMappingDto dto) {
        PersonalAthleteMappingEntity entity = PersonalAthleteMappingEntity.builder().build();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
