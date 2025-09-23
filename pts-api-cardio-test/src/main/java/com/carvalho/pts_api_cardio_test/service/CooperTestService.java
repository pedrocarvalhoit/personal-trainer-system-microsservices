package com.carvalho.pts_api_cardio_test.service;

import com.carvalho.pts_api_cardio_test.dto.CooperTestHistoryResponseDto;
import com.carvalho.pts_api_cardio_test.dto.CooperTestRequestDto;
import com.carvalho.pts_api_cardio_test.dto.CooperTestDto;
import com.carvalho.pts_api_cardio_test.dto.TestDescriptionResponseDto;
import jakarta.validation.Valid;

import java.util.List;

public interface CooperTestService {

    CooperTestDto saveCooperTest(String athleteId, @Valid CooperTestRequestDto requestDto);

    CooperTestDto getLastVo2Max(String athleteId);

    TestDescriptionResponseDto getDescription();

    List<CooperTestHistoryResponseDto> getHistoryResults(String athleteId);

    Long delete(Long testId);
}
