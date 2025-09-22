package com.carvalho.pts_api_bioimpedance_assessment.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BioimpedanceAssessmentCreatedEventDto implements Serializable{

    private Long bioimpedanceId;
    private String athleteId;
    private Double bodyWeight;
    private Double idealWeight;
    private Double basalMetabolism;

}
