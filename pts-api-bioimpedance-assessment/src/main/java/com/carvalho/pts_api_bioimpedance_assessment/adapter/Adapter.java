package com.carvalho.pts_api_bioimpedance_assessment.adapter;

import com.carvalho.pts_api_bioimpedance_assessment.dto.BioimpedanceAssessmentDto;
import com.carvalho.pts_api_bioimpedance_assessment.entity.BioimpedanceAssessmentEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class Adapter {

    public BioimpedanceAssessmentEntity bioimpedanceAssessmentModelToEntity(BioimpedanceAssessmentDto dto){
        BioimpedanceAssessmentEntity entity = BioimpedanceAssessmentEntity.builder().build();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    public BioimpedanceAssessmentDto bioimpedanceEntityToModel(BioimpedanceAssessmentEntity entity){
        BioimpedanceAssessmentDto dto = BioimpedanceAssessmentDto.builder().build();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public void updateEntityFromDto(BioimpedanceAssessmentEntity existingEntity, BioimpedanceAssessmentDto dto){
        BeanUtils.copyProperties(dto, existingEntity, "id", "createdAt");
    }


}
