package com.carvalho.pts_api_perimetry_assessment.repository;

import com.carvalho.pts_api_perimetry_assessment.entity.PerimetryAssessmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerimetryAssessmentRepository extends JpaRepository<PerimetryAssessmentEntity, Long> {
    List<PerimetryAssessmentEntity> findAllByAthleteId(String athleteId);
}
