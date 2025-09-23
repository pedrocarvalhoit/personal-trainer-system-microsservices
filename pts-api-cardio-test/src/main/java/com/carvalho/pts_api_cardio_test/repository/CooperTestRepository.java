package com.carvalho.pts_api_cardio_test.repository;

import com.carvalho.pts_api_cardio_test.adapter.CooperTestAdapter;
import com.carvalho.pts_api_cardio_test.entity.CooperTestEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CooperTestRepository extends JpaRepository<CooperTestEntity, Long> {

    CooperTestEntity findTopByAthleteIdOrderByCreatedAtDesc(String athleteId);

    List<CooperTestEntity> findByAthleteIdOrderByCreatedAtDesc(String athleteId, Pageable pageable);
}

