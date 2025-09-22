package com.carvalho.pts_api_athlete_report.dto;

import com.carvalho.pts_api_athlete_report.entity.AthleteEntity;
import com.carvalho.pts_api_athlete_report.entity.enums.BMIStatus;
import com.carvalho.pts_api_athlete_report.entity.enums.BloodPressureStatus;
import com.carvalho.pts_api_athlete_report.entity.enums.WaistHipRisk;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@Builder
@EqualsAndHashCode
public class AthleteReportDto {

    private Long id;
    private LocalDateTime createdAt;
    private BloodPressureStatus bloodPressureStatus;
    private WaistHipRisk waistHipRisk;
    private BMIStatus BMIStatus;
}
