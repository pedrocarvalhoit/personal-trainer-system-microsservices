package com.carvalho.pts_api_inicial_assessment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InicialAssessmentResponse {

    private Long id;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime createdAt;
    private boolean sedentary;
    private boolean smoker;
    private Integer restingHeartRate;
    private Integer systolicBloodPressure;
    private Integer diastolicBloodPressure;

}
