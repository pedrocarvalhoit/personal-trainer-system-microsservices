package com.carvalho.pts_api_strength_test.service;

import com.carvalho.pts_api_strength_test.dto.*;
import jakarta.validation.Valid;

import java.util.List;

public interface StrengthTestService {

    StrengthTestResponseDto save(String athleteId, @Valid StrengthTestRequestDto request);

    StrengthTestResponseDto getLastTest(String athleteId);

    TestDescriptionResponseDto getDescription();

    ExercisesResponseDto getAllExercieses();

    List<ExercisesResultsResponseDto> getExerciseResults(String athleteId);
}
