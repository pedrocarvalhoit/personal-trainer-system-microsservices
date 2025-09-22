package com.carvalho.pts_api_bioimpedance_assessment.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@Builder
@EqualsAndHashCode
public class BioimpedanceAssessmentDto {

    private Long id;
    private LocalDateTime createdAt;
    private Integer age;
    private Double height;
    private Double bodyWeight;
    private Double bmi;
    private Double bodyFatPercentual;
    private Double bodyMassPercentual;
    private Double basalMetabolismRateCalories;
    private Integer visceralFatIndice;
    private Double idealWeight;
    private Double idealBodyFatPercentual;
    private String notes;

}
