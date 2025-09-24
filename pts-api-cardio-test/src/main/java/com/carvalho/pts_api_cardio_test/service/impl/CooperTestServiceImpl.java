package com.carvalho.pts_api_cardio_test.service.impl;

import com.carvalho.pts_api_cardio_test.adapter.CooperTestAdapter;
import com.carvalho.pts_api_cardio_test.adapter.DescriptionAdapter;
import com.carvalho.pts_api_cardio_test.dto.CooperTestHistoryResponseDto;
import com.carvalho.pts_api_cardio_test.dto.CooperTestRequestDto;
import com.carvalho.pts_api_cardio_test.dto.CooperTestDto;
import com.carvalho.pts_api_cardio_test.dto.TestDescriptionResponseDto;
import com.carvalho.pts_api_cardio_test.entity.CooperTestDescriptionEntity;
import com.carvalho.pts_api_cardio_test.entity.CooperTestEntity;
import com.carvalho.pts_api_cardio_test.enums.CooperTestClassification;
import com.carvalho.pts_api_cardio_test.repository.CooperTestDescriptionRepository;
import com.carvalho.pts_api_cardio_test.repository.CooperTestRepository;
import com.carvalho.pts_api_cardio_test.service.CooperTestService;
import com.carvalho.pts_api_cardio_test.util.CooperTestUtil;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CooperTestServiceImpl implements CooperTestService {

    private final CooperTestRepository repository;
    private final CooperTestDescriptionRepository descriptionRepository;
    private final CooperTestAdapter adapter;
    private final DescriptionAdapter descriptionAdapter;

    public CooperTestServiceImpl(CooperTestRepository repository, CooperTestDescriptionRepository descriptionRepository, CooperTestAdapter adapter, DescriptionAdapter descriptionAdapter) {
        this.repository = repository;
        this.descriptionRepository = descriptionRepository;
        this.adapter = adapter;
        this.descriptionAdapter = descriptionAdapter;
    }

    @Override
    public CooperTestDto saveCooperTest(String athleteId, CooperTestRequestDto requestDto) {
        CooperTestEntity entity = CooperTestEntity.builder()
                .athleteId(athleteId)
                .athleteAge(requestDto.getAthleteAge())
                .athleteGender(requestDto.getAthleteGender())
                .distance(requestDto.getDistance())
                .vo2Max(CooperTestUtil.calculateVo2Max(requestDto.getDistance()))
                .build();

        CooperTestClassification classification = CooperTestUtil.getClassification(entity.getAthleteAge(), entity.getAthleteGender() ,entity.getVo2Max());
        entity.setClassification(classification);

        repository.save(entity);

        return adapter.entityToModel(entity);
    }

    @Override
    public CooperTestDto getLastVo2Max(String athleteId) {
        return adapter.entityToModel(repository.findTopByAthleteIdOrderByCreatedAtDesc(athleteId));
    }

    //Resturns Test Description
    @Override
    public TestDescriptionResponseDto getDescription() {
        CooperTestDescriptionEntity entity = descriptionRepository.findAll()
                .stream()
                .findFirst()
                .orElse(null);

        return descriptionAdapter.entityToModel(entity);
    }

    @Override
    public List<CooperTestHistoryResponseDto> getHistoryResults(String athleteId) {
        Pageable pageable = PageRequest.of(0, 12);
        List<CooperTestEntity> testsList = repository.findByAthleteIdOrderByCreatedAtAsc(athleteId, pageable);
        return testsList.stream()
                .map(adapter::toHistoryResponse)
                .toList();
    }

    @Override
    public Long delete(Long testId) {
        repository.deleteById(testId);
        return testId;
    }

}
