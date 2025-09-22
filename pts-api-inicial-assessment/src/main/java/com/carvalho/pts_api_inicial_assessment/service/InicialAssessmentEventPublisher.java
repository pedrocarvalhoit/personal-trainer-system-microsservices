package com.carvalho.pts_api_inicial_assessment.service;

import com.carvalho.pts_api_inicial_assessment.dto.InicialAssessmentToReportDto;

public interface InicialAssessmentEventPublisher {

    void publishToAthleteReport(InicialAssessmentToReportDto dto);

}
