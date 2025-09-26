package com.carvalho.pts_api_strength_test.adapter;

import com.carvalho.pts_api_strength_test.dto.StrengthTestResponseDto;
import com.carvalho.pts_api_strength_test.entity.StrengthTestEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class Adapter {

    public StrengthTestResponseDto entityToModel(StrengthTestEntity strengthTestEntity) {
        StrengthTestResponseDto dto = StrengthTestResponseDto.builder().build();
        BeanUtils.copyProperties(strengthTestEntity, dto);
        return dto;
    }

}
