package com.carvalho.pts_api_cardio_test.repository;

import com.carvalho.pts_api_cardio_test.adapter.CooperTestAdapter;
import com.carvalho.pts_api_cardio_test.entity.CooperTestDescriptionEntity;
import com.carvalho.pts_api_cardio_test.entity.CooperTestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CooperTestDescriptionRepository extends JpaRepository<CooperTestDescriptionEntity, Long> {

}

