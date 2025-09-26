package com.carvalho.pts_api_strength_test.dto;

import com.carvalho.pts_api_strength_test.enums.Exercise;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@Builder
@EqualsAndHashCode
public class StrengthTestRequestDto {

    private Double maxLoad;

    private Exercise exercise;


}
