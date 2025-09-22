package com.carvalho.pts_api_athlete_report.repository;

import com.carvalho.pts_api_athlete_report.entity.InicialAssessmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InicialAssessmentRepository extends JpaRepository<InicialAssessmentEntity, Long> {
    InicialAssessmentEntity findFirstByAthleteIdOrderByCreatedAtDesc(String athleteId);
}
