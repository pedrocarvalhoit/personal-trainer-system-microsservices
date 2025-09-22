package com.carvalho.pts_api_athlete_report.service.assessment;

import com.carvalho.pts_api_athlete_report.adapter.Adapter;
import com.carvalho.pts_api_athlete_report.dto.InicialAssessmentToReportDto;
import com.carvalho.pts_api_athlete_report.repository.InicialAssessmentRepository;
import org.springframework.stereotype.Service;

@Service
public class InicialAssessmentService {

    private final InicialAssessmentRepository inicialAssessmentRepository;
    private final Adapter adapter;

    public InicialAssessmentService(InicialAssessmentRepository inicialAssessmentRepository, Adapter adapter) {
        this.inicialAssessmentRepository = inicialAssessmentRepository;
        this.adapter = adapter;
    }

    public void saveInicialAssessmentFromEvent(InicialAssessmentToReportDto event) {
        inicialAssessmentRepository.save(adapter.toInicialAssessmentEntity(event));
    }
}
