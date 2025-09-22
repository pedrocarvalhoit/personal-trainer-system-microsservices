package com.carvalho.pts_api_inicial_assessment.repository;

import com.carvalho.pts_api_inicial_assessment.entity.InicialAssessmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InicialAssessmentRepository extends JpaRepository<InicialAssessmentEntity, Long> {

    List<InicialAssessmentEntity> findAllByAthleteId(String athleteId);

}
