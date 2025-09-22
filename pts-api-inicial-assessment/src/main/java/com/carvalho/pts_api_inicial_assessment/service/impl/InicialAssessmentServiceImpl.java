package com.carvalho.pts_api_inicial_assessment.service.impl;

import com.carvalho.pts_api_inicial_assessment.adapter.Adapter;
import com.carvalho.pts_api_inicial_assessment.dto.InicialAssessmentDto;
import com.carvalho.pts_api_inicial_assessment.dto.InicialAssessmentResponse;
import com.carvalho.pts_api_inicial_assessment.dto.InicialAssessmentToReportDto;
import com.carvalho.pts_api_inicial_assessment.dto.UserAthleteCreatedEventDto;
import com.carvalho.pts_api_inicial_assessment.entity.AthleteEntity;
import com.carvalho.pts_api_inicial_assessment.entity.InicialAssessmentEntity;
import com.carvalho.pts_api_inicial_assessment.exception.InicialAssessmentNotFoundException;
import com.carvalho.pts_api_inicial_assessment.repository.AthleteRepository;
import com.carvalho.pts_api_inicial_assessment.repository.InicialAssessmentRepository;
import com.carvalho.pts_api_inicial_assessment.service.InicialAssessmentService;
import com.carvalho.pts_api_inicial_assessment.util.InicialAssessmentUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InicialAssessmentServiceImpl implements InicialAssessmentService {

    private final InicialAssessmentRepository repository;
    private final Adapter adapter;
    private final AthleteRepository athleteRepository;
    private final InicialAssessmentEventPublisherImpl publisher;

    public InicialAssessmentServiceImpl(InicialAssessmentRepository repository, Adapter adapter, AthleteRepository athleteRepository, InicialAssessmentEventPublisherImpl publisher) {
        this.repository = repository;
        this.adapter = adapter;
        this.athleteRepository = athleteRepository;
        this.publisher = publisher;
    }

    @Override
    public InicialAssessmentDto saveInicialAssessment(String athleteId, InicialAssessmentDto assessment) {
        AthleteEntity athlete = athleteRepository.findById(athleteId).orElseThrow(() -> new RuntimeException("athlete not found"));

        InicialAssessmentEntity inicialAssessmentEntity = adapter.inicialAssessmentModelToEntity(assessment);
        inicialAssessmentEntity.setAthlete(athlete);
        inicialAssessmentEntity.setMaxHeartRate(InicialAssessmentUtil.setMaxHeartRate(assessment.getAthleteAge()));

        repository.save(inicialAssessmentEntity);

        publisher.publishToAthleteReport(InicialAssessmentToReportDto.builder()
                .athleteId(athleteId)
                .inicialAssessmentId(inicialAssessmentEntity.getId())
                .systolicBloodPressure(inicialAssessmentEntity.getSystolicBloodPressure())
                .diastolicBloodPressure(inicialAssessmentEntity.getDiastolicBloodPressure())
                .build());

        return adapter.inicialAssessmentEntityToModel(inicialAssessmentEntity);
    }

    @Override
    public List<InicialAssessmentResponse> listAllByAthleteId(String athleteId) {
        List<InicialAssessmentEntity> entities = repository.findAllByAthleteId(athleteId);
        return entities.stream()
                .map(adapter::toInicialAssessmentResponse)
                .toList();
    }

    @Override
    public InicialAssessmentDto update(Long id, InicialAssessmentDto dto) {
        InicialAssessmentEntity existingEntity = repository.findById(id).orElseThrow(() -> {
            return InicialAssessmentNotFoundException.forInicialAssessmentId(String.valueOf(id));
        });

        adapter.updateEntityFromDto(existingEntity, dto);

        InicialAssessmentEntity updatedEntity = repository.save(existingEntity);

        return adapter.inicialAssessmentEntityToModel(updatedEntity);
    }

    @Override
    public void delete(Long id) {
        Optional<InicialAssessmentEntity> assessment = repository.findById(id);
        InicialAssessmentEntity response = assessment.orElseThrow(() -> {
            return InicialAssessmentNotFoundException.forInicialAssessmentId(String.valueOf(id));
        });
        repository.delete(response);
    }

    @Override
    public void saveAthleteFromEvent(UserAthleteCreatedEventDto event) {
        AthleteEntity entity = AthleteEntity.builder()
                .id(event.getAthleteId())
                .build();

        athleteRepository.save(entity);
    }


}
