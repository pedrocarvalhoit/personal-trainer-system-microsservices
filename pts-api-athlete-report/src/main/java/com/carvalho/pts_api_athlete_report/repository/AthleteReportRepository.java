package com.carvalho.pts_api_athlete_report.repository;

import com.carvalho.pts_api_athlete_report.dto.AthleteReportDto;
import com.carvalho.pts_api_athlete_report.entity.AthleteReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AthleteReportRepository extends JpaRepository<AthleteReportEntity, Long> {

    AthleteReportEntity findTopByAthleteIdOrderByCreatedAtDesc(String athleteId);

}
