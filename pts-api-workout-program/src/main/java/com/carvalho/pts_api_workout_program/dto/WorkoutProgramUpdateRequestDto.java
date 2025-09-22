package com.carvalho.pts_api_workout_program.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class WorkoutProgramUpdateRequestDto {


    private String title;

    private LocalDate startDate;

    private LocalDate endDate;

    private String trainingSessionContent;

    private String note;

    private boolean enabled;

}
