package com.carvalho.pts_api_bioimpedance_assessment.service;

import com.carvalho.pts_api_bioimpedance_assessment.dto.UserAthleteCreatedEventDto;
import com.carvalho.pts_api_bioimpedance_assessment.dto.BioimpedanceAssessmentDto;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface BioimpedanceAssessmentService {

    BioimpedanceAssessmentDto saveBioimpedanceAssessment(String  athleteId, BioimpedanceAssessmentDto assessment) throws JsonProcessingException;

    void delete(Long id);

    List<BioimpedanceAssessmentDto> listAllByAthleteId(String athleteId);

    BioimpedanceAssessmentDto update(Long id, BioimpedanceAssessmentDto dto);

    Double getBioimpendanceWeight(Long bioimpedanceId);

    Double getBioimpendanceBasalMetabolism(Long bioimpedanceId);

    Double getBioimpendanceIdealWeight(Long bioimpedanceId);

    void saveAthleteFromEvent(UserAthleteCreatedEventDto event);
}
