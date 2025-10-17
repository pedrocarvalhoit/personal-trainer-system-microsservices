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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StrengthTestServiceImpl implements StrengthTestService {

    private final StrengthTestRepository repository;
    private final StrengthTestDescriptionRepository descriptionRepository;

    public StrengthTestServiceImpl(StrengthTestRepository repository, StrengthTestDescriptionRepository descriptionRepository, DescriptionAdapter descriptionAdapter, ExerciseAdapter exerciseAdapter) {
        this.repository = repository;
        this.descriptionRepository = descriptionRepository;
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

        return Adapter.entityToModel(strengthTestEntity);
    }

    @Override
    public List<StrengthTestResponseDto> getAllTests(String athleteId) {
        List<StrengthTestEntity> entities = repository.findByAthleteIdOrderByCreatedAtDesc(athleteId);
        return entities.stream()
                .map(Adapter::entityToModel)
                .toList();
    }

    public Long delete(Long testId) {
        repository.deleteById(testId);
        return testId;
    }

    //Resturns Test Description
    @Override
    public TestDescriptionResponseDto getDescription() {
        StrengthTestDescriptionEntity entity = descriptionRepository.findAll()
                .stream()
                .findFirst()
                .orElse(null);

        return DescriptionAdapter.entityToModel(entity);
    }

    //Tests Names List
    public ExercisesResponseDto getAllExercieses() {
        List<String> exercises = new ArrayList<>();
        for (Exercise c : Exercise.values()){
            exercises.add(String.valueOf(c));
        }

        return ExerciseAdapter.exercisesResponseDto(exercises);
    }

    /**
     * Retrieves and organizes strength test results for the last 36 entries per athlete,
     * grouped by exercise. For each exercise, it calculates the cumulative average of max load over time.
     *
     * @param athleteId the ID of the athlete whose results are being fetched
     * @return a map where the key is the Exercise and the value is a list of DTOs containing monthly stats
     */
    public Map<Exercise, List<ExercisesResultsResponseDto>> getExerciseResults(String athleteId) {
        Pageable pageable = PageRequest.of(0, 36, Sort.by("createdAt").descending());

        Page<StrengthTestEntity> entities = repository.findByAthleteId(athleteId, pageable);

        // Group the test results by exercise type (e.g., Bench Press, Squat, Deadlift)
        Map<Exercise, List<StrengthTestEntity>> groupedByExercise = entities.stream()
                .collect(Collectors.groupingBy(StrengthTestEntity::getExercise));

        // Initialize the final result map: Exercise -> List of monthly result DTOs
        Map<Exercise, List<ExercisesResultsResponseDto>> result = new HashMap<>();

        // Iterate over each exercise group
        for (Map.Entry<Exercise, List<StrengthTestEntity>> entry : groupedByExercise.entrySet()) {
            Exercise exercise = entry.getKey();
            List<StrengthTestEntity> exerciseTests = entry.getValue();

            List<ExercisesResultsResponseDto> responses = new ArrayList<>();
            double totalLoad = 0.0; // used to accumulate maxLoad values
            int count = 1;          // used to calculate the running average

            // For each test in the current exercise group
            for (StrengthTestEntity test : exerciseTests) {
                totalLoad += test.getMaxLoad();          // add the current max load to the total
                double average = totalLoad / count;      // calculate the running average

                // Build a response DTO with monthly stats and max load information
                ExercisesResultsResponseDto response = ExercisesResultsResponseDto.builder()
                        .month(test.getCreatedAt().getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH)) // e.g., "January"
                        .maxLoadAvarage(average)
                        .maxLoad(test.getMaxLoad())
                        .max1Rm(test.getMax1Rm())
                        .build();

                responses.add(response);
                count++;
            }

            // Put the list of results into the final map for the current exercise
            result.put(exercise, responses);
        }

        return result;
    }


}
