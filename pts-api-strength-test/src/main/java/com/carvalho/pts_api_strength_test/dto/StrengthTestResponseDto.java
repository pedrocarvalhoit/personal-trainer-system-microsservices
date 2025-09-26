package com.carvalho.pts_api_strength_test.dto;

import com.carvalho.pts_api_strength_test.enums.Exercise;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@Builder
@EqualsAndHashCode
public class StrengthTestResponseDto {

    private Long id;
    private LocalDateTime createdAt;

    private String athleteId;

    private Double maxLoad;

    private Double max1Rm;

    private Exercise exercise;


}
