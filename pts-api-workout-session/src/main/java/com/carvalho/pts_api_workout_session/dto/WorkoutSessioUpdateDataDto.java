package com.carvalho.pts_api_workout_session.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@EqualsAndHashCode
public class WorkoutSessioUpdateDataDto {
    private String workoutProgramName;

    private LocalDate sessionDate;

    private LocalTime sessionTime;

    private Integer clientSubjectiveEffort;

    private Integer ptQualityEffortIndicative;
}
