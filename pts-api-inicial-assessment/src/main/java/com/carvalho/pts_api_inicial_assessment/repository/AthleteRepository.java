package com.carvalho.pts_api_inicial_assessment.repository;

import com.carvalho.pts_api_inicial_assessment.entity.AthleteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AthleteRepository extends JpaRepository<AthleteEntity, String> {
}
