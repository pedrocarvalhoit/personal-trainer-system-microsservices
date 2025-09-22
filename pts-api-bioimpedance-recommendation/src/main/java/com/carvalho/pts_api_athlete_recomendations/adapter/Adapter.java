package com.carvalho.pts_api_athlete_recomendations.adapter;

import com.carvalho.pts_api_athlete_recomendations.dto.BioimpedanceRecommendationDto;
import com.carvalho.pts_api_athlete_recomendations.entity.BioimpedanceRecommendationEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class Adapter {

    public BioimpedanceRecommendationEntity bioimpedanceAssessmentModelToEntity(BioimpedanceRecommendationDto dto) {
        BioimpedanceRecommendationEntity entity = BioimpedanceRecommendationEntity.builder().build();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    public BioimpedanceRecommendationDto bioimpedanceRecommendationEntityToModel(BioimpedanceRecommendationEntity entity) {
        BioimpedanceRecommendationDto dto = BioimpedanceRecommendationDto.builder().build();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public void updateEntityFromDto(BioimpedanceRecommendationEntity existingEntity, BioimpedanceRecommendationDto dto) {
        BeanUtils.copyProperties(dto, existingEntity,  "id", "createdAt");
    }
}
