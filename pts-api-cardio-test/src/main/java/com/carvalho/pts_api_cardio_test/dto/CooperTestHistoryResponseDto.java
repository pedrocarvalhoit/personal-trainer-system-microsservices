package com.carvalho.pts_api_cardio_test.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.TextStyle;
import java.util.Locale;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CooperTestHistoryResponseDto {

    private String month;

    private Double result;

}
