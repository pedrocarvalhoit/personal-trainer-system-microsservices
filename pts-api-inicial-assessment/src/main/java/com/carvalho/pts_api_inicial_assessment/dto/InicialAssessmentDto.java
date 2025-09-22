package com.carvalho.pts_api_inicial_assessment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@Builder
@EqualsAndHashCode
public class InicialAssessmentDto {

    private Long id;
    private LocalDateTime createdAt;

    private Long athleteId;

    private Integer athleteAge;
    private boolean sedentary;
    private boolean smoker;
    private Integer restingHeartRate;
    private Integer systolicBloodPressure;
    private Integer diastolicBloodPressure;

}
