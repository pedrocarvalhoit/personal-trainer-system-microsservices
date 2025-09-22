package com.carvalho.pts_api_athlete_report.repository;

import com.carvalho.pts_api_athlete_report.entity.AthleteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AthleteRepository extends JpaRepository<AthleteEntity, String> {
}
