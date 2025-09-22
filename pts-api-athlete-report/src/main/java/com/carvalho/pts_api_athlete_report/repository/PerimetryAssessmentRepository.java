package com.carvalho.pts_api_athlete_report.repository;

import com.carvalho.pts_api_athlete_report.entity.PerimetryAssessmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerimetryAssessmentRepository extends JpaRepository<PerimetryAssessmentEntity, Long> {

    PerimetryAssessmentEntity findFirstByAthleteIdOrderByCreatedAtDesc(String athleteId);

}
