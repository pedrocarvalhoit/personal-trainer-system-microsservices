package com.carvalho.pts_api_inicial_assessment.adapter;

import com.carvalho.pts_api_inicial_assessment.dto.InicialAssessmentDto;
import com.carvalho.pts_api_inicial_assessment.dto.InicialAssessmentResponse;
import com.carvalho.pts_api_inicial_assessment.entity.InicialAssessmentEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class Adapter {

    public InicialAssessmentEntity inicialAssessmentModelToEntity(InicialAssessmentDto dto) {
        InicialAssessmentEntity entity = InicialAssessmentEntity.builder().build();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    public InicialAssessmentDto inicialAssessmentEntityToModel(InicialAssessmentEntity entity) {
        InicialAssessmentDto dto = InicialAssessmentDto.builder().build();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public void updateEntityFromDto(InicialAssessmentEntity existingEntity, InicialAssessmentDto dto) {
        BeanUtils.copyProperties(dto, existingEntity,  "id", "createdAt");
    }

    public InicialAssessmentResponse toInicialAssessmentResponse(InicialAssessmentEntity entity) {
        InicialAssessmentResponse response = InicialAssessmentResponse.builder().build();
        BeanUtils.copyProperties(entity, response);
        return response;
    }
}
