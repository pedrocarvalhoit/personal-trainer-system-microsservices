package com.carvalho.pts_api_bioimpedance_assessment.repository;

import com.carvalho.pts_api_bioimpedance_assessment.entity.AthleteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AthleteRepository extends JpaRepository<AthleteEntity, String> {

}
