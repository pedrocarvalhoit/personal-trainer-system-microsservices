package com.carvalho.pts_api_strength_test.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExerciseStatsResponseDto {

    private String month;
    private double maxLoadAvarage;
    private double maxLoad;
    private double max1Rm;

}
