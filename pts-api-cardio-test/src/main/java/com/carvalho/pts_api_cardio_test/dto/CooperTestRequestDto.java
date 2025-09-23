package com.carvalho.pts_api_cardio_test.dto;

import lombok.*;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CooperTestRequestDto {

    private Double distance;

    private Integer athleteAge;

    private String athleteGender;
}
