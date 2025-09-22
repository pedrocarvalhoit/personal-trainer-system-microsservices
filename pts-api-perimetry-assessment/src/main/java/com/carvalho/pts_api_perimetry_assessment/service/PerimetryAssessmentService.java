package com.carvalho.pts_api_perimetry_assessment.service;

import com.carvalho.pts_api_perimetry_assessment.dto.PerimetryAssessmentDto;
import com.carvalho.pts_api_perimetry_assessment.dto.UserAthleteCreatedEventDto;

import java.util.List;

public interface PerimetryAssessmentService {


    PerimetryAssessmentDto savePerimetryAssessment(String athleteId, PerimetryAssessmentDto assessment);

    PerimetryAssessmentDto searchById(Long id);

    void delete(Long id);

    List<PerimetryAssessmentDto> listAllByAthleteId(String athleteId);

    PerimetryAssessmentDto update(Long id, PerimetryAssessmentDto dto);

    void saveAthleteFromEvent(UserAthleteCreatedEventDto event);
}
