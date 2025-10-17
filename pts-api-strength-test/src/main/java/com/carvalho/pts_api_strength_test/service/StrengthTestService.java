package com.carvalho.pts_api_strength_test.service;

import com.carvalho.pts_api_strength_test.dto.*;
import com.carvalho.pts_api_strength_test.enums.Exercise;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

public interface StrengthTestService {

    StrengthTestResponseDto save(String athleteId, @Valid StrengthTestRequestDto request);

    List<StrengthTestResponseDto> getAllTests(String athleteId);

    TestDescriptionResponseDto getDescription();

    ExercisesResponseDto getAllExercieses();

    Map<Exercise, List<ExercisesResultsResponseDto>> getExerciseResults(String athleteId);

    Long delete(Long testId);
}
