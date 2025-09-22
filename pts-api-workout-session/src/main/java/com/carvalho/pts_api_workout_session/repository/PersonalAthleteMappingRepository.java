package com.carvalho.pts_api_workout_session.repository;

import com.carvalho.pts_api_workout_session.entity.PersonalAthleteMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalAthleteMappingRepository extends JpaRepository<PersonalAthleteMappingEntity, Long> {

    List<PersonalAthleteMappingEntity> findAllByPersonalId(String loggedPersonalId);
}
