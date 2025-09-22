package com.carvalho.pts_api_workout_program.service;

import com.carvalho.pts_api_workout_program.dto.WorkoutProgramDto;
import com.carvalho.pts_api_workout_program.dto.WorkoutProgramUpdateRequestDto;
import com.carvalho.pts_api_workout_program.entity.WorkoutProgramEntity;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface WorkoutProgramService {
    WorkoutProgramDto save(String athleteId, WorkoutProgramDto requestBody);

    List<WorkoutProgramDto> getAllForAthlete(String athleteId);

    WorkoutProgramDto update(Long programId, @Valid WorkoutProgramUpdateRequestDto request);

    List<WorkoutProgramEntity> findProgramByEndDateBefore(LocalDate currentDate);

    void saveForScheduler(WorkoutProgramEntity program);

    void exportToPdf(HttpServletResponse response, Long programId) throws IOException;
}
