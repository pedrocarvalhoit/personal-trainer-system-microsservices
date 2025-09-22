package com.carvalho.pts_api_athlete_recomendations.service.impl;

import com.carvalho.pts_api_athlete_recomendations.adapter.Adapter;
import com.carvalho.pts_api_athlete_recomendations.dto.BioimpedanceRecommendationDto;
import com.carvalho.pts_api_athlete_recomendations.entity.BioimpedanceRecommendationEntity;
import com.carvalho.pts_api_athlete_recomendations.repository.BioimpedanceRecommendationRepository;
import com.carvalho.pts_api_athlete_recomendations.service.calculation.RecommendationCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@AutoConfigureWireMock(port = 0)
class BioimpedanceRecommendationServiceImplTest {

    @Mock
    private BioimpedanceRecommendationRepository repository;

    @Mock
    private Adapter adapter;

    @Mock
    private RecommendationCalculator calculator;

    @InjectMocks
    private BioimpedanceRecommendationServiceImpl service;

    private BioimpedanceRecommendationEntity bioimpedanceRecommendationEntity;
    private BioimpedanceRecommendationDto bioimpedanceRecommendationDto;

    @BeforeEach
    public void setup(){
        bioimpedanceRecommendationEntity = new BioimpedanceRecommendationEntity();
        bioimpedanceRecommendationEntity.setId(1L);
        bioimpedanceRecommendationEntity.setBioimpedanceId(1L);
        bioimpedanceRecommendationEntity.setCreatedAt(LocalDateTime.of(2025, 2, 6, 12, 0));

        bioimpedanceRecommendationDto = BioimpedanceRecommendationDto.builder()
                .id(1L)
                .createdAt(LocalDateTime.of(2025, 2, 6, 12, 0))
                .bioimpedanceId(1L)
                .watterIntake(2000.0)
                .caloriesIntakeToAchieveIdealWeightSlow(2000.0)
                .caloriesIntakeToAchieveIdealWeightModerate(2500.0)
                .caloriesIntakeToAchieveIdealWeightFast(3000.0)
                .daysToAchieveIdealWeightSlow(50.0)
                .daysToAchieveIdealWeightModerate(45.0)
                .daysToAchieveIdealWeightFast(40.0)
                .build();
    }

    BioimpedanceRecommendationServiceImplTest(){

    }

}