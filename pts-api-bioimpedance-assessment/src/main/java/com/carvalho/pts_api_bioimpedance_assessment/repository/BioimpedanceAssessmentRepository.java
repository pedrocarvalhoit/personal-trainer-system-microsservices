package com.carvalho.pts_api_bioimpedance_assessment.repository;

import com.carvalho.pts_api_bioimpedance_assessment.entity.BioimpedanceAssessmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BioimpedanceAssessmentRepository extends JpaRepository<BioimpedanceAssessmentEntity, Long> {

    List<BioimpedanceAssessmentEntity> findAllByAthleteId(String athleteId);
}
