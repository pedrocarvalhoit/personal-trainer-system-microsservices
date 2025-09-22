package com.carvalho.pts_api_athlete_report.service;

import com.carvalho.pts_api_athlete_report.dto.AthleteReportDto;
import com.carvalho.pts_api_athlete_report.dto.UserAthleteCreatedEventDto;
import com.carvalho.pts_api_athlete_report.entity.AthleteReportEntity;

public interface AthleteReportService {

    AthleteReportDto getLastByAthlete(String athleteId);

    void saveAthleteFromEvent(UserAthleteCreatedEventDto event);

    AthleteReportEntity generate(String athleteId);
}
