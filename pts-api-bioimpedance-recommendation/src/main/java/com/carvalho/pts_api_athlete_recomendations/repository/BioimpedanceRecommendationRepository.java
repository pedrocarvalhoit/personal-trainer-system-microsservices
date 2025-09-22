package com.carvalho.pts_api_athlete_recomendations.repository;

import com.carvalho.pts_api_athlete_recomendations.entity.BioimpedanceRecommendationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BioimpedanceRecommendationRepository extends JpaRepository<BioimpedanceRecommendationEntity, Long> {

    BioimpedanceRecommendationEntity findFirstByAthleteIdOrderByCreatedAtDesc(String athleteId);

}
