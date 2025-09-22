package com.carvalho.pts_api_athlete_report.service.assessment;

import com.carvalho.pts_api_athlete_report.dto.BioimpedanceToAthleteReportDto;
import com.carvalho.pts_api_athlete_report.repository.BioimpedanceAssessmentRepository;
import com.carvalho.pts_api_athlete_report.adapter.Adapter;
import org.springframework.stereotype.Service;

@Service
public class BioimpedanceAssessmentService {

    private final BioimpedanceAssessmentRepository repository;
    private final Adapter adapter;

    public BioimpedanceAssessmentService(BioimpedanceAssessmentRepository repository, Adapter adapter) {
        this.repository = repository;
        this.adapter = adapter;
    }

    public void saveBioimpedanceAssessmentFromEvent(BioimpedanceToAthleteReportDto event) {
        repository.save(adapter.toBioimpedanceEntity(event));
    }
}
