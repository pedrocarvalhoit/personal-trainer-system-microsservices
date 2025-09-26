package com.carvalho.pts_api_strength_test.repository;

import com.carvalho.pts_api_strength_test.entity.StrengthTestDescriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StrengthTestDescriptionRepository extends JpaRepository<StrengthTestDescriptionEntity, Long> {

}

