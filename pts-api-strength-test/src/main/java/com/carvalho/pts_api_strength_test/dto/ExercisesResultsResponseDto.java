package com.carvalho.pts_api_strength_test.dto;

import com.carvalho.pts_api_strength_test.enums.Exercise;
import lombok.*;

@Getter
@Setter
public class ExercisesResultsResponseDto {

    private String month;
    private Double maxLoadAvarage;
    private Double maxLoad;
    private Double max1Rm;
    private Exercise exercise;

    public ExercisesResultsResponseDto(Object month, Double maxLoadAvarage, Double maxLoad, Double max1Rm, Exercise exercise) {
        this.month = month != null ? month.toString() : null;
        this.maxLoadAvarage = maxLoadAvarage != null ? maxLoadAvarage : 0.0;
        this.maxLoad = maxLoad != null ? maxLoad : 0.0;
        this.max1Rm = max1Rm != null ? max1Rm : 0.0;
        this.exercise = exercise;
    }


}
