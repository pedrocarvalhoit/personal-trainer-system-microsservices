package com.carvalho.pts_api_cardio_test.dto;

import com.carvalho.pts_api_cardio_test.enums.CooperTestClassification;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
public class CooperTestDto {

    private Long id;
    private String athleteId;
    private Integer athleteAge;
    private String athleteGender;
    private Double distance;
    private Double vo2Max;
    private CooperTestClassification classification;

}
