package com.carvalho.pts_api_perimetry_assessment.service;

import com.carvalho.pts_api_perimetry_assessment.dto.PerimetryAssessmentToAthleteReportDto;

public interface PerimetryAssessmentEventPublisher {

    void publishToAthleteReport(PerimetryAssessmentToAthleteReportDto dto);

}
