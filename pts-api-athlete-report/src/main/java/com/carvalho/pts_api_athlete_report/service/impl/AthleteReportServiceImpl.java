package com.carvalho.pts_api_athlete_report.service.impl;

import com.carvalho.pts_api_athlete_report.adapter.Adapter;
import com.carvalho.pts_api_athlete_report.dto.AthleteReportDto;
import com.carvalho.pts_api_athlete_report.dto.UserAthleteCreatedEventDto;
import com.carvalho.pts_api_athlete_report.entity.*;
import com.carvalho.pts_api_athlete_report.entity.enums.BMIStatus;
import com.carvalho.pts_api_athlete_report.entity.enums.BloodPressureStatus;
import com.carvalho.pts_api_athlete_report.entity.enums.WaistHipRisk;
import com.carvalho.pts_api_athlete_report.repository.AthleteReportRepository;
import com.carvalho.pts_api_athlete_report.repository.AthleteRepository;
import com.carvalho.pts_api_athlete_report.repository.BioimpedanceAssessmentRepository;
import com.carvalho.pts_api_athlete_report.repository.PerimetryAssessmentRepository;
import com.carvalho.pts_api_athlete_report.service.AthleteReportService;
import com.carvalho.pts_api_athlete_report.repository.InicialAssessmentRepository;
import org.springframework.stereotype.Service;

@Service
public class AthleteReportServiceImpl implements AthleteReportService {

    private final AthleteRepository athleteRepository;
    private final AthleteReportRepository athleteReportRepository;
    private final BioimpedanceAssessmentRepository bioimpedanceAssessmentRepository;
    private final InicialAssessmentRepository inicialAssessmentRepository;
    private final PerimetryAssessmentRepository perimetryAssessmentRepository;
    private final Adapter adapter;

    public AthleteReportServiceImpl(AthleteRepository repository, AthleteReportRepository athleteReportRepository, BioimpedanceAssessmentRepository bioimpedanceAssessmentRepository, InicialAssessmentRepository inicialAssessmentRepository, PerimetryAssessmentRepository perimetryAssessmentRepository, Adapter adapter) {
        this.athleteRepository = repository;
        this.athleteReportRepository = athleteReportRepository;
        this.bioimpedanceAssessmentRepository = bioimpedanceAssessmentRepository;
        this.inicialAssessmentRepository = inicialAssessmentRepository;
        this.perimetryAssessmentRepository = perimetryAssessmentRepository;
        this.adapter = adapter;
    }

    @Override
    public AthleteReportDto getLastByAthlete(String athleteId) {
        return adapter.entityToModel(athleteReportRepository.findTopByAthleteIdOrderByCreatedAtDesc(athleteId));
    }

    @Override
    public AthleteReportEntity generate(String athleteId) {
        AthleteEntity athleteEntity = athleteRepository.findById(athleteId).orElse(null);
        BioimpedanceAssessmentEntity bioimpedanceAssessmentEntity = bioimpedanceAssessmentRepository.findFirstByAthleteIdOrderByCreatedAtDesc(athleteId);
        InicialAssessmentEntity inicialAssessmentEntity = inicialAssessmentRepository.findFirstByAthleteIdOrderByCreatedAtDesc(athleteId);
        PerimetryAssessmentEntity perimetryAssessmentEntity = perimetryAssessmentRepository.findFirstByAthleteIdOrderByCreatedAtDesc(athleteId);

        return athleteReportRepository.save(AthleteReportEntity.builder()
                        .athlete(athleteEntity)
                        .BMIStatus(BMIStatus.from(bioimpedanceAssessmentEntity.getBmi()))
                        .bloodPressureStatus(BloodPressureStatus.classify(inicialAssessmentEntity.getSystolicBloodPressure(), inicialAssessmentEntity.getDiastolicBloodPressure()))
                        .waistHipRisk(WaistHipRisk.classify(perimetryAssessmentEntity.getWaist(), perimetryAssessmentEntity.getHip(), athleteEntity.getGender()))
                        .build());
    }

    @Override
    public void saveAthleteFromEvent(UserAthleteCreatedEventDto event) {
        athleteRepository.save(AthleteEntity.builder()
                .id(event.getAthleteId())
                .gender(event.getGender())
                .build());
    }



}
