package com.carvalho.pts_api_perimetry_assessment.adapter;

import com.carvalho.pts_api_perimetry_assessment.dto.PerimetryAssessmentDto;
import com.carvalho.pts_api_perimetry_assessment.entity.PerimetryAssessmentEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class Adapter {

    public PerimetryAssessmentEntity perimetryAssessmentModelToEntity(PerimetryAssessmentDto dto) {
        PerimetryAssessmentEntity entity = PerimetryAssessmentEntity.builder().build();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    public  PerimetryAssessmentDto perimetryAssessmentEntityToModel(PerimetryAssessmentEntity entity) {
        PerimetryAssessmentDto dto = PerimetryAssessmentDto.builder().build();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public void updateEntityFromDto(PerimetryAssessmentEntity existingEntity, PerimetryAssessmentDto dto){
        BeanUtils.copyProperties(dto, existingEntity,  "id", "createdAt");
    }
}
