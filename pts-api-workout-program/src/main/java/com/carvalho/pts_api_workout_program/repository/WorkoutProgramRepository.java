package com.carvalho.pts_api_workout_program.repository;

import com.carvalho.pts_api_workout_program.entity.WorkoutProgramEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WorkoutProgramRepository extends JpaRepository<WorkoutProgramEntity, Long> {

    List<WorkoutProgramEntity> findAllByAthleteId(String athleteId);

    List<WorkoutProgramEntity> findAllByEndDateBeforeAndEnabledTrue(LocalDate date);
}
