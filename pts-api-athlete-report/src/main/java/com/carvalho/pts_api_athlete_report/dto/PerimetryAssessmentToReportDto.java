package com.carvalho.pts_api_athlete_report.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PerimetryAssessmentToReportDto implements Serializable {

    private Long perimetryAssessmentId;
    private String athleteId;
    private Double waist;
    private Double hip;

}
