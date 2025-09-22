package com.carvalho.pts_api_athlete_report.service.assessment;

import com.carvalho.pts_api_athlete_report.adapter.Adapter;
import com.carvalho.pts_api_athlete_report.dto.PerimetryAssessmentToReportDto;
import com.carvalho.pts_api_athlete_report.repository.PerimetryAssessmentRepository;
import org.springframework.stereotype.Service;

@Service
public class PerimetryAssessmentService {

    private final PerimetryAssessmentRepository perimetryAssessmentRepository;
    private final Adapter adapter;

    public PerimetryAssessmentService(PerimetryAssessmentRepository perimetryAssessmentRepository, Adapter adapter) {
        this.perimetryAssessmentRepository = perimetryAssessmentRepository;
        this.adapter = adapter;
    }

    public void savePerimetryAssessmentFromEvent(PerimetryAssessmentToReportDto event) {
        perimetryAssessmentRepository.save(adapter.toPerimetryAssessmentEntity(event));
    }
}
