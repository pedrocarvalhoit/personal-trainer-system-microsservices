package com.carvalho.pts_api_strength_test.service.impl;

import com.carvalho.pts_api_strength_test.adapter.DescriptionAdapter;
import com.carvalho.pts_api_strength_test.adapter.ExerciseAdapter;
import com.carvalho.pts_api_strength_test.dto.*;
import com.carvalho.pts_api_strength_test.entity.StrengthTestDescriptionEntity;
import com.carvalho.pts_api_strength_test.entity.StrengthTestEntity;
import com.carvalho.pts_api_strength_test.enums.Exercise;
import com.carvalho.pts_api_strength_test.repository.StrengthTestDescriptionRepository;
import com.carvalho.pts_api_strength_test.repository.StrengthTestRepository;
import com.carvalho.pts_api_strength_test.service.StrengthTestService;
import com.carvalho.pts_api_strength_test.adapter.Adapter;
import com.carvalho.pts_api_strength_test.util.StrengthTestUtil;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class StrengthTestServiceImpl implements StrengthTestService {

    private final StrengthTestRepository repository;
    private final StrengthTestDescriptionRepository descriptionRepository;
    private final Adapter adapter;
    private final DescriptionAdapter descriptionAdapter;
    private final ExerciseAdapter exerciseAdapter;

    public StrengthTestServiceImpl(StrengthTestRepository repository, StrengthTestDescriptionRepository descriptionRepository, Adapter adapter, DescriptionAdapter descriptionAdapter, ExerciseAdapter exerciseAdapter) {
        this.repository = repository;
        this.descriptionRepository = descriptionRepository;
        this.adapter = adapter;
        this.descriptionAdapter = descriptionAdapter;
        this.exerciseAdapter = exerciseAdapter;
    }

    @Override
    public StrengthTestResponseDto save(String athleteId, StrengthTestRequestDto request) {
        StrengthTestEntity strengthTestEntity = StrengthTestEntity.builder()
                .athleteId(athleteId)
                .maxLoad(request.getMaxLoad())
                .exercise(request.getExercise())
                .build();
        strengthTestEntity.setMax1Rm(StrengthTestUtil.calculateRm(request.getMaxLoad()));

        repository.save(strengthTestEntity);

        return adapter.entityToModel(strengthTestEntity);
    }

    @Override
    public StrengthTestResponseDto getLastTest(String athleteId) {
        return adapter.entityToModel(repository.findTopByAthleteIdOrderByCreatedAtDesc(athleteId));
    }

    //Resturns Test Description
    @Override
    public TestDescriptionResponseDto getDescription() {
        StrengthTestDescriptionEntity entity = descriptionRepository.findAll()
                .stream()
                .findFirst()
                .orElse(null);

        return descriptionAdapter.entityToModel(entity);
    }

    //Tests Names List
    public ExercisesResponseDto getAllExercieses() {
        List<String> exercises = new ArrayList<>();
        for (Exercise c : Exercise.values()){
            exercises.add(String.valueOf(c));
        }

        return exerciseAdapter.exercisesResponseDto(exercises);
    }

    @Override
    public List<ExercisesResultsResponseDto> getExerciseResults(String athleteId) {
        LocalDateTime twelveMonthsAgor = LocalDateTime.now().minusMonths(12).with(TemporalAdjusters.firstDayOfMonth());

        return repository.findMonthlyExerciseResults(athleteId, twelveMonthsAgor);
    }

}
