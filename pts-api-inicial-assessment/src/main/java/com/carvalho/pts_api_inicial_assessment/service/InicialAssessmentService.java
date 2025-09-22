package com.carvalho.pts_api_inicial_assessment.service;

import com.carvalho.pts_api_inicial_assessment.dto.InicialAssessmentDto;
import com.carvalho.pts_api_inicial_assessment.dto.InicialAssessmentResponse;
import com.carvalho.pts_api_inicial_assessment.dto.UserAthleteCreatedEventDto;

import java.util.List;

public interface InicialAssessmentService {

    InicialAssessmentDto saveInicialAssessment(String athleteId, InicialAssessmentDto assessment);


    void delete(Long id);

    List<InicialAssessmentResponse> listAllByAthleteId(String athleteId);

    InicialAssessmentDto update(Long id, InicialAssessmentDto dto);

    void saveAthleteFromEvent(UserAthleteCreatedEventDto event);
}
