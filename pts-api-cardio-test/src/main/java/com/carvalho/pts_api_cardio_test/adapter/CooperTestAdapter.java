package com.carvalho.pts_api_cardio_test.adapter;

import com.carvalho.pts_api_cardio_test.dto.CooperTestDto;
import com.carvalho.pts_api_cardio_test.dto.CooperTestHistoryResponseDto;
import com.carvalho.pts_api_cardio_test.entity.CooperTestEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.time.format.TextStyle;
import java.util.Locale;

@Component
public class CooperTestAdapter {

    public CooperTestDto entityToModel(CooperTestEntity entity) {
        CooperTestDto dto = CooperTestDto.builder().build();
        BeanUtils.copyProperties(entity, dto, "createdAt");
        return dto;
    }

    public CooperTestHistoryResponseDto toHistoryResponse(CooperTestEntity cooperTestEntity) {
        return CooperTestHistoryResponseDto.builder()
                .month(cooperTestEntity.getCreatedAt().getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH))
                .result(cooperTestEntity.getVo2Max())
                .build();

    }
}
