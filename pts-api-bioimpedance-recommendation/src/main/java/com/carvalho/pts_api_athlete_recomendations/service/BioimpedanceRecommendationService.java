package com.carvalho.pts_api_athlete_recomendations.service;

import com.carvalho.pts_api_athlete_recomendations.dto.BioimpedanceAssessmentCreatedEventDto;
import com.carvalho.pts_api_athlete_recomendations.dto.BioimpedanceRecommendationDto;

import java.util.List;

public interface BioimpedanceRecommendationService {

    void saveFromEvent(BioimpedanceAssessmentCreatedEventDto event);

    BioimpedanceRecommendationDto searchById(Long id);

    void delete(Long id);

    BioimpedanceRecommendationDto listLastByAthlete(String athleteId);

    BioimpedanceRecommendationDto update(Long id, BioimpedanceRecommendationDto dto);

}
