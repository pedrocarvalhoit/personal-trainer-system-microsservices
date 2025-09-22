package com.carvalho.pts_api_athlete_report.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BioimpedanceToAthleteReportDto implements Serializable {

    private Long bioimpedanceId;

    private String athleteId;

    private Double bmi;

}
