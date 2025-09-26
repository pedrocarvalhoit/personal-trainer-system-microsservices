package com.carvalho.pts_api_strength_test.repository;

import com.carvalho.pts_api_strength_test.dto.ExercisesResultsResponseDto;
import com.carvalho.pts_api_strength_test.entity.StrengthTestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StrengthTestRepository extends JpaRepository<StrengthTestEntity, Long> {


    StrengthTestEntity findTopByAthleteIdOrderByCreatedAtDesc(String athleteId);

    @Query("""
    SELECT new com.carvalho.pts_api_strength_test.dto.ExercisesResultsResponseDto(
        FUNCTION('DATE_FORMAT', st.createdAt, '%Y-%m'),
        AVG(st.maxLoad),
        MAX(st.maxLoad),
        MAX(st.max1Rm),
        st.exercise
    )
    FROM StrengthTestEntity st
    WHERE st.athleteId = :athleteId
      AND st.createdAt >= :fromDate
    GROUP BY st.exercise, FUNCTION('DATE_FORMAT', st.createdAt, '%Y-%m')
    ORDER BY st.exercise, FUNCTION('DATE_FORMAT', st.createdAt, '%Y-%m')
""")
    List<ExercisesResultsResponseDto> findMonthlyExerciseResults(
            @Param("athleteId") String athleteId,
            @Param("fromDate") LocalDateTime fromDate
    );




}
