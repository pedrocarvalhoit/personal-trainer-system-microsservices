package com.carvalho.pts_api_athlete_report.repository;

import com.carvalho.pts_api_athlete_report.entity.BioimpedanceAssessmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BioimpedanceAssessmentRepository extends JpaRepository<BioimpedanceAssessmentEntity, Long> {

    BioimpedanceAssessmentEntity findFirstByAthleteIdOrderByCreatedAtDesc(String athleteId);

}
