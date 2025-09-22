package com.carvalho.pts_api_bioimpedance_assessment.service.impl;

import com.carvalho.pts_api_bioimpedance_assessment.adapter.Adapter;
import com.carvalho.pts_api_bioimpedance_assessment.dto.BioimpedanceToAthleteReportDto;
import com.carvalho.pts_api_bioimpedance_assessment.dto.UserAthleteCreatedEventDto;
import com.carvalho.pts_api_bioimpedance_assessment.dto.BioimpedanceAssessmentCreatedEventDto;
import com.carvalho.pts_api_bioimpedance_assessment.dto.BioimpedanceAssessmentDto;
import com.carvalho.pts_api_bioimpedance_assessment.entity.AthleteEntity;
import com.carvalho.pts_api_bioimpedance_assessment.entity.BioimpedanceAssessmentEntity;
import com.carvalho.pts_api_bioimpedance_assessment.exception.BioimpedanceAssessmentNotFoundException;
import com.carvalho.pts_api_bioimpedance_assessment.repository.AthleteRepository;
import com.carvalho.pts_api_bioimpedance_assessment.repository.BioimpedanceAssessmentRepository;
import com.carvalho.pts_api_bioimpedance_assessment.service.BioimpedanceAssessmentEventPublisher;
import com.carvalho.pts_api_bioimpedance_assessment.service.BioimpedanceAssessmentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class BioimpedanceAssessmentServiceImpl implements BioimpedanceAssessmentService {

    private final BioimpedanceAssessmentRepository repository;
    private final AthleteRepository athleteRepository;
    private final Adapter adapter;
    private final BioimpedanceAssessmentEventPublisher publisher;

    public BioimpedanceAssessmentServiceImpl(BioimpedanceAssessmentRepository repository, AthleteRepository athleteRepository, Adapter adapter, BioimpedanceAssessmentEventPublisher publisher) {
        this.repository = repository;
        this.athleteRepository = athleteRepository;
        this.adapter = adapter;
        this.publisher = publisher;
    }

    @Override
    public BioimpedanceAssessmentDto saveBioimpedanceAssessment(String  athleteId, BioimpedanceAssessmentDto assessment) throws JsonProcessingException {
        AthleteEntity athleteEntity = athleteRepository.findById(athleteId).orElseThrow(() ->
                new RuntimeException("Athlete not found"));

        BioimpedanceAssessmentEntity entity = adapter.bioimpedanceAssessmentModelToEntity(assessment);
        entity.setAthlete(athleteEntity);
        repository.save(entity);

        log.info(entity.getId());
        publisher.publish(BioimpedanceAssessmentCreatedEventDto.builder()
                .athleteId(athleteId)
                .bioimpedanceId(entity.getId())
                .bodyWeight(assessment.getBodyWeight())
                .idealWeight(assessment.getIdealWeight())
                .basalMetabolism(assessment.getBasalMetabolismRateCalories())
                .build());

        publisher.publishToAthleteReport(BioimpedanceToAthleteReportDto.builder()
                .bioimpedanceId(entity.getId())
                .athleteId(athleteId)
                .bmi(entity.getBmi())
                .build());

        return adapter.bioimpedanceEntityToModel(entity);
    }

    @Override
    public List<BioimpedanceAssessmentDto> listAllByAthleteId(String athleteId) {
        List<BioimpedanceAssessmentEntity> entities = repository.findAllByAthleteId(athleteId);
        return entities.stream()
                .map(adapter::bioimpedanceEntityToModel)
                .toList();
    }

    @Override
    public void delete(Long id) {
        Optional<BioimpedanceAssessmentEntity> assessment = repository.findById(id);
        BioimpedanceAssessmentEntity response = assessment.orElseThrow(() -> {
            return BioimpedanceAssessmentNotFoundException.forBioimpedanceAssessmentId(String.valueOf(id));
        });
        repository.delete(response);
    }

    @Override
    public BioimpedanceAssessmentDto update(Long id, BioimpedanceAssessmentDto dto) {
        BioimpedanceAssessmentEntity existingEntity = repository.findById(id).orElseThrow(() -> {
            return BioimpedanceAssessmentNotFoundException.forBioimpedanceAssessmentId(String.valueOf(id));
        });

        adapter.updateEntityFromDto(existingEntity, dto);

        BioimpedanceAssessmentEntity updatedEntity = repository.save(existingEntity);

        return adapter.bioimpedanceEntityToModel(updatedEntity);
    }

    @Override
    public Double getBioimpendanceWeight(Long bioimpedanceId) {
        BioimpedanceAssessmentEntity assessment = getBioimpedanceAssessmentEntity(bioimpedanceId);
        return assessment.getBodyWeight();
    }

    @Override
    public Double getBioimpendanceIdealWeight(Long bioimpedanceId) {
        BioimpedanceAssessmentEntity assessment = getBioimpedanceAssessmentEntity(bioimpedanceId);
        return assessment.getIdealWeight();
    }

    @Override
    public Double getBioimpendanceBasalMetabolism(Long bioimpedanceId) {
        BioimpedanceAssessmentEntity assessment = getBioimpedanceAssessmentEntity(bioimpedanceId);
        return assessment.getBasalMetabolismRateCalories();
    }

    private BioimpedanceAssessmentEntity getBioimpedanceAssessmentEntity(Long bioimpedanceId) {
        return repository.findById(bioimpedanceId)
                .orElseThrow(() -> BioimpedanceAssessmentNotFoundException.forBioimpedanceAssessmentId(bioimpedanceId.toString()));
    }


    public void saveAthleteFromEvent(UserAthleteCreatedEventDto event) {
        AthleteEntity entity = AthleteEntity.builder()
                .id(event.getAthleteId())
                .build();

        athleteRepository.save(entity);
    }
}
