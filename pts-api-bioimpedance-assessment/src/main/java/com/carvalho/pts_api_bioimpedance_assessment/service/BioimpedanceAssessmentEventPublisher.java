package com.carvalho.pts_api_bioimpedance_assessment.service;

import com.carvalho.pts_api_bioimpedance_assessment.dto.BioimpedanceAssessmentCreatedEventDto;
import com.carvalho.pts_api_bioimpedance_assessment.dto.BioimpedanceToAthleteReportDto;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface BioimpedanceAssessmentEventPublisher {

    void publish(BioimpedanceAssessmentCreatedEventDto bioimpedanceAssessmentCreatedEvent) throws JsonProcessingException;

    void publishToAthleteReport(BioimpedanceToAthleteReportDto dto);

}
