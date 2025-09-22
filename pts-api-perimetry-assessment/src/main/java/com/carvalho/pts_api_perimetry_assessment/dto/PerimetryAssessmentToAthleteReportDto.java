package com.carvalho.pts_api_perimetry_assessment.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PerimetryAssessmentToAthleteReportDto implements Serializable {

    private Long perimetryAssessmentId;
    private String athleteId;
    private Double waist;
    private Double hip;

}
