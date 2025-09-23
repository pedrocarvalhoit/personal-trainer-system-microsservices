package com.carvalho.pts_api_cardio_test.adapter;

import com.carvalho.pts_api_cardio_test.dto.CooperTestDto;
import com.carvalho.pts_api_cardio_test.dto.TestDescriptionResponseDto;
import com.carvalho.pts_api_cardio_test.entity.CooperTestDescriptionEntity;
import com.carvalho.pts_api_cardio_test.entity.CooperTestEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class DescriptionAdapter {

    public TestDescriptionResponseDto entityToModel(CooperTestDescriptionEntity entity) {
        TestDescriptionResponseDto dto = TestDescriptionResponseDto.builder().build();
        BeanUtils.copyProperties(entity, dto, "id");
        return dto;
    }

}
