package com.carvalho.pts_api_inicial_assessment.dto;


import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class InicialAssessmentToReportDto implements Serializable {

    private Long inicialAssessmentId;
    private String athleteId;
    private Integer systolicBloodPressure;
    private Integer diastolicBloodPressure;

}
