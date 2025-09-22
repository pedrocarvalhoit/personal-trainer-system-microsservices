package com.carvalho.pts_api_athlete_recomendations.service.impl;

import com.carvalho.pts_api_athlete_recomendations.adapter.Adapter;
import com.carvalho.pts_api_athlete_recomendations.dto.BioimpedanceAssessmentCreatedEventDto;
import com.carvalho.pts_api_athlete_recomendations.dto.BioimpedanceRecommendationDto;
import com.carvalho.pts_api_athlete_recomendations.entity.BioimpedanceRecommendationEntity;
import com.carvalho.pts_api_athlete_recomendations.exception.BioimpedanceRecommendationNotFoundException;
import com.carvalho.pts_api_athlete_recomendations.repository.BioimpedanceRecommendationRepository;
import com.carvalho.pts_api_athlete_recomendations.service.BioimpedanceRecommendationService;
import com.carvalho.pts_api_athlete_recomendations.service.calculation.EventBasedRecommendationCalculator;
import com.carvalho.pts_api_athlete_recomendations.service.calculation.RecommendationCalculator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BioimpedanceRecommendationServiceImpl implements BioimpedanceRecommendationService {

    private final BioimpedanceRecommendationRepository repository;
    private final Adapter adapter;
    private final RecommendationCalculator recommendationCalculator;
    private final EventBasedRecommendationCalculator eventBasedRecommendationCalculator;

    public BioimpedanceRecommendationServiceImpl(BioimpedanceRecommendationRepository repository, Adapter adapter, RecommendationCalculator recommendationCalculator, EventBasedRecommendationCalculator eventBasedRecommendationCalculator) {
        this.repository = repository;
        this.adapter = adapter;
        this.recommendationCalculator = recommendationCalculator;
        this.eventBasedRecommendationCalculator = eventBasedRecommendationCalculator;
    }

    @Override
    public void saveFromEvent(BioimpedanceAssessmentCreatedEventDto event) {
        BioimpedanceRecommendationEntity bioimpedanceRecommendationEntity = BioimpedanceRecommendationEntity.builder()
                .bioimpedanceId(event.getBioimpedanceId())
                .athleteId(event.getAthleteId())
                .watterIntake(eventBasedRecommendationCalculator.calculateWaterIntake(event.getBodyWeight()))
                .caloriesIntakeToAchieveIdealWeightSlow(eventBasedRecommendationCalculator.calculateCaloriesIntakeSlow(event.getBodyWeight(), event.getIdealWeight(), event.getBasalMetabolism()))
                .caloriesIntakeToAchieveIdealWeightModerate(eventBasedRecommendationCalculator.calculateCaloriesIntakeModerate(event.getBodyWeight(), event.getIdealWeight(), event.getBasalMetabolism()))
                .caloriesIntakeToAchieveIdealWeightFast(eventBasedRecommendationCalculator.calculateCaloriesIntakeFast(event.getBodyWeight(), event.getIdealWeight(), event.getBasalMetabolism()))
                .daysToAchieveIdealWeightSlow(eventBasedRecommendationCalculator.daysToAchieveIdealWeightSlow(event.getBodyWeight(), event.getIdealWeight()))
                .daysToAchieveIdealWeightModerate(eventBasedRecommendationCalculator.daysToAchieveIdealWeightModerate(event.getBodyWeight(), event.getIdealWeight()))
                .daysToAchieveIdealWeightFast(eventBasedRecommendationCalculator.daysToAchieveIdealWeightFast(event.getBodyWeight(), event.getIdealWeight()))
                .build();
        repository.save(bioimpedanceRecommendationEntity);
    }

    @Override
    public BioimpedanceRecommendationDto searchById(Long id) {
        BioimpedanceRecommendationEntity response = repository.findById(id).orElseThrow(() ->{
            return BioimpedanceRecommendationNotFoundException.forBioimepdanceRecommendationId(String.valueOf(id));
        });
        return adapter.bioimpedanceRecommendationEntityToModel(response);
    }

    @Override
    public BioimpedanceRecommendationDto listLastByAthlete(String athleteId) {
        BioimpedanceRecommendationEntity entity = repository.findFirstByAthleteIdOrderByCreatedAtDesc(athleteId);
        return adapter.bioimpedanceRecommendationEntityToModel(entity);
    }

    @Override
    public BioimpedanceRecommendationDto update(Long id, BioimpedanceRecommendationDto dto) {
        BioimpedanceRecommendationEntity existingEntity = repository.findById(id)
                .orElseThrow(() -> {
                    return BioimpedanceRecommendationNotFoundException.forBioimepdanceRecommendationId(String.valueOf(id));
                });

        adapter.updateEntityFromDto(existingEntity, dto);

        BioimpedanceRecommendationEntity updatedEntity = repository.save(existingEntity);

        return adapter.bioimpedanceRecommendationEntityToModel(updatedEntity);
    }

    @Override
    public void delete(Long id) {
        Optional<BioimpedanceRecommendationEntity> recommendation = repository.findById(id);
        BioimpedanceRecommendationEntity response = recommendation.orElseThrow(() -> {
            return BioimpedanceRecommendationNotFoundException.forBioimepdanceRecommendationId(String.valueOf(id));
        });
        repository.delete(response);
    }


}
