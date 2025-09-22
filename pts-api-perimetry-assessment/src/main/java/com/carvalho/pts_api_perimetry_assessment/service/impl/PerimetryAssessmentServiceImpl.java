package com.carvalho.pts_api_perimetry_assessment.service.impl;

import com.carvalho.pts_api_perimetry_assessment.adapter.Adapter;
import com.carvalho.pts_api_perimetry_assessment.dto.PerimetryAssessmentDto;
import com.carvalho.pts_api_perimetry_assessment.dto.PerimetryAssessmentToAthleteReportDto;
import com.carvalho.pts_api_perimetry_assessment.dto.UserAthleteCreatedEventDto;
import com.carvalho.pts_api_perimetry_assessment.entity.AthleteEntity;
import com.carvalho.pts_api_perimetry_assessment.entity.PerimetryAssessmentEntity;
import com.carvalho.pts_api_perimetry_assessment.repository.AthleteRepository;
import com.carvalho.pts_api_perimetry_assessment.repository.PerimetryAssessmentRepository;
import com.carvalho.pts_api_perimetry_assessment.service.PerimetryAssessmentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerimetryAssessmentServiceImpl implements PerimetryAssessmentService {

    private final PerimetryAssessmentRepository repository;
    private final Adapter adapter;
    private final AthleteRepository athleteRespository;
    private final PerimetryAssessmentEventPublisherImpl publisher;

    public PerimetryAssessmentServiceImpl(PerimetryAssessmentRepository repository, Adapter adapter, AthleteRepository athleteRespository, PerimetryAssessmentEventPublisherImpl publisher) {
        this.repository = repository;
        this.adapter = adapter;
        this.athleteRespository = athleteRespository;
        this.publisher = publisher;
    }

    @Override
    public PerimetryAssessmentDto savePerimetryAssessment(String athleteId, PerimetryAssessmentDto assessment) {
        AthleteEntity athlete = athleteRespository.findById(athleteId).orElseThrow(() -> new RuntimeException("athlete not found"));
        PerimetryAssessmentEntity entity = adapter.perimetryAssessmentModelToEntity(assessment);
        entity.setAthlete(athlete);

        repository.save(entity);

        publisher.publishToAthleteReport(PerimetryAssessmentToAthleteReportDto.builder()
                        .athleteId(athleteId)
                        .perimetryAssessmentId(entity.getId())
                        .waist(entity.getWaist())
                        .hip(entity.getHip())
                        .build());

        return adapter.perimetryAssessmentEntityToModel(entity);
    }

    @Override
    public void saveAthleteFromEvent(UserAthleteCreatedEventDto event) {
        AthleteEntity athlete = AthleteEntity.builder()
                .id(event.getAthleteId())
                .build();

        athleteRespository.save(athlete);
    }

    @Override
    public PerimetryAssessmentDto searchById(Long id) {
        PerimetryAssessmentEntity response = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Perimetry Assessnent not found"));
        return adapter.perimetryAssessmentEntityToModel(response);
    }

    @Override
    public List<PerimetryAssessmentDto> listAllByAthleteId(String athleteId) {
        List<PerimetryAssessmentEntity> entities = repository.findAllByAthleteId(athleteId);
        return entities.stream()
                .map(adapter::perimetryAssessmentEntityToModel)
                .toList();
    }

    @Override
    public PerimetryAssessmentDto update(Long id, PerimetryAssessmentDto dto) {
        PerimetryAssessmentEntity existingEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Perimetry Assessment not found"));

        adapter.updateEntityFromDto(existingEntity, dto);

        PerimetryAssessmentEntity updatedEntity = repository.save(existingEntity);

        return adapter.perimetryAssessmentEntityToModel(updatedEntity);
    }

    @Override
    public void delete(Long id) {
        Optional<PerimetryAssessmentEntity> assessment = repository.findById(id);
        PerimetryAssessmentEntity response = assessment.orElseThrow(() -> new EntityNotFoundException("Perimetry Assessnent not found"));
        repository.delete(response);
    }


}
