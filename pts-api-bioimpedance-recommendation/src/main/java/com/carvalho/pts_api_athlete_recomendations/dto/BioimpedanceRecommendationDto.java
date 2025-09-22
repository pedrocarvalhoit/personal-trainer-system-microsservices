package com.carvalho.pts_api_athlete_recomendations.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@Builder
@EqualsAndHashCode
public class BioimpedanceRecommendationDto {

    private Long id;
    private LocalDateTime createdAt;
    private Long bioimpedanceId;
    private Double watterIntake;
    private Double caloriesIntakeToAchieveIdealWeightSlow;
    private Double caloriesIntakeToAchieveIdealWeightModerate;
    private Double caloriesIntakeToAchieveIdealWeightFast;
    private Double daysToAchieveIdealWeightSlow;
    private Double daysToAchieveIdealWeightModerate;
    private Double daysToAchieveIdealWeightFast;


}
