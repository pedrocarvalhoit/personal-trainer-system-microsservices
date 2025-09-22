package com.carvalho.pts_api_perimetry_assessment.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@Builder
@EqualsAndHashCode
public class PerimetryAssessmentDto {

    private Long id;
    private LocalDateTime createdAt;

    private Long athleteId;

    private Double shoulder;
    private Double torax;
    private Double waist;
    private Double abdomen;
    private Double hip;
    private Double rightArm;
    private Double leftArm;
    private Double rightThigh;
    private Double leftThigh;
    private Double rightLeg;
    private Double leftLeg;

}
