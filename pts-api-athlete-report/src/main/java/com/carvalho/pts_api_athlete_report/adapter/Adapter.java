package com.carvalho.pts_api_athlete_report.adapter;

import com.carvalho.pts_api_athlete_report.dto.AthleteReportDto;
import com.carvalho.pts_api_athlete_report.dto.BioimpedanceToAthleteReportDto;
import com.carvalho.pts_api_athlete_report.dto.InicialAssessmentToReportDto;
import com.carvalho.pts_api_athlete_report.dto.PerimetryAssessmentToReportDto;
import com.carvalho.pts_api_athlete_report.entity.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class Adapter {

    public BioimpedanceAssessmentEntity toBioimpedanceEntity(BioimpedanceToAthleteReportDto event) {
        return BioimpedanceAssessmentEntity.builder()
                .id(event.getBioimpedanceId())
                .athleteId(event.getAthleteId())
                .bmi(event.getBmi())
                .build();
    }

    public InicialAssessmentEntity toInicialAssessmentEntity(InicialAssessmentToReportDto event) {
        return InicialAssessmentEntity.builder()
                .id(event.getInicialAssessmentId())
                .athleteId(event.getAthleteId())
                .systolicBloodPressure(event.getSystolicBloodPressure())
                .diastolicBloodPressure(event.getDiastolicBloodPressure())
                .build();
    }


    public PerimetryAssessmentEntity toPerimetryAssessmentEntity(PerimetryAssessmentToReportDto event) {
        return PerimetryAssessmentEntity.builder()
                .id(event.getPerimetryAssessmentId())
                .athleteId(event.getAthleteId())
                .waist(event.getWaist())
                .hip(event.getHip())
                .build();
    }

    public AthleteReportDto entityToModel(AthleteReportEntity entity) {
        AthleteReportDto dto = AthleteReportDto.builder().build();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
}
