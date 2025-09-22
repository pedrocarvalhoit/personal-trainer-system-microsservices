package com.carvalho.pts_api_workout_program.adapter;

import com.carvalho.pts_api_workout_program.dto.WorkoutProgramDto;
import com.carvalho.pts_api_workout_program.dto.WorkoutProgramUpdateRequestDto;
import com.carvalho.pts_api_workout_program.entity.WorkoutProgramEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class Adapter {

    public WorkoutProgramEntity workoutProgramModelToEntity(WorkoutProgramDto dto) {
        WorkoutProgramEntity entity = WorkoutProgramEntity.builder().build();
        BeanUtils.copyProperties(dto, entity, "athleteId");
        return entity;
    }

    public WorkoutProgramDto workoutProgramEntityToModel(WorkoutProgramEntity entity) {
        WorkoutProgramDto dto = WorkoutProgramDto.builder().build();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public void updateEntityFromDto(WorkoutProgramEntity existingEntity, WorkoutProgramUpdateRequestDto dto) {
        BeanUtils.copyProperties(dto, existingEntity);
    }

    public WorkoutProgramDto toWorkoutProgramResponse(WorkoutProgramEntity entity) {
        WorkoutProgramDto response = WorkoutProgramDto.builder().build();
        BeanUtils.copyProperties(entity, response);
        return response;
    }
}
