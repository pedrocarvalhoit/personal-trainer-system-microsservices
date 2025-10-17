package com.carvalho.pts_api_strength_test.adapter;

import com.carvalho.pts_api_strength_test.dto.ExercisesResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExerciseAdapter {

    public static ExercisesResponseDto exercisesResponseDto(List<String> exercises) {
        return ExercisesResponseDto.builder()
                .exercises(exercises)
                .build();
    }

}
