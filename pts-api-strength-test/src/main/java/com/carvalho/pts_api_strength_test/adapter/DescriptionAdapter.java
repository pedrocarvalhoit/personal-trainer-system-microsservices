package com.carvalho.pts_api_strength_test.adapter;

import com.carvalho.pts_api_strength_test.dto.TestDescriptionResponseDto;
import com.carvalho.pts_api_strength_test.entity.StrengthTestDescriptionEntity;
import org.springframework.stereotype.Component;

@Component
public class DescriptionAdapter {

    public static TestDescriptionResponseDto entityToModel(StrengthTestDescriptionEntity entity) {
        if (entity == null) return null;
        return TestDescriptionResponseDto.builder()
                .description(entity.getDescription())
                .build();
    }

}
