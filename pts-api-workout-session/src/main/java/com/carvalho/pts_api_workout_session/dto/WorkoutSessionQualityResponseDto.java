package com.carvalho.pts_api_workout_session.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
/*This clas is used to see avarage stats for training sessions quality */
public class WorkoutSessionQualityResponseDto {

    private String month;
    private double clientSubjectEffortAvarage;
    private double ptQualityEffortAvarage;

}