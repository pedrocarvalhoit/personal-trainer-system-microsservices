package com.carvalho.pts_api_workout_session.repository;

import com.carvalho.pts_api_workout_session.entity.WorkoutSessionEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WorkoutSessionRepository extends JpaRepository<WorkoutSessionEntity, Long> {

    Page<WorkoutSessionEntity> findAllByAthleteId(String athleteId, Pageable pageable);

    List<WorkoutSessionEntity> findAllByAthleteId(String athleteId);

    @Query("SELECT ws FROM WorkoutSessionEntity ws " +
            "WHERE ws.athleteId = :athleteId " +
            "AND ws.sessionDate BETWEEN :startDate AND :endDate ")
    List<WorkoutSessionEntity> findTotalSessionsByAthleteIdForPeriod(@Param("athleteId") String athleteId,
                                                                     @Param("startDate") LocalDate startDate,
                                                                     @Param("endDate") LocalDate endDate);

    @Query("SELECT ws FROM WorkoutSessionEntity ws " +
            "WHERE ws.athleteId = :athleteId " +
            "AND ws.sessionDate BETWEEN :startDate AND :endDate " +
            "AND ws.executed = true")
    List<WorkoutSessionEntity> findTotalSessionsExecutedByAthleteIdForPeriod(@Param("athleteId") String athleteId,
                                                                             @Param("startDate") LocalDate startDate,
                                                                             @Param("endDate") LocalDate endDate);

    @Query("SELECT ws FROM WorkoutSessionEntity ws " +
            "WHERE ws.athleteId = :athleteId " +
            "AND ws.sessionDate BETWEEN :startDate AND :endDate " +
            "AND ws.executed = false")
    List<WorkoutSessionEntity> findTotalSessionsNotExecutedByAthleteIdForPeriod(@Param("athleteId") String athleteId,
                                                                                @Param("startDate") LocalDate startDate,
                                                                                @Param("endDate") LocalDate endDate);


    List<WorkoutSessionEntity> findAllByExecutedIsTrueAndAthleteId(String athleteId);

    List<WorkoutSessionEntity> findAllByExecutedIsFalseAndAthleteId(String athleteId);

    @Query("SELECT ws.athleteId, COUNT(ws) FROM WorkoutSessionEntity ws " +
            "WHERE ws.athleteId IN :athletes GROUP BY ws.athleteId " +
            "ORDER BY COUNT(ws) DESC")
    List<Object[]> findTopAthletes(@Param("athletes") List<String> athletes, Pageable pageable);

    @Query("""
    SELECT a.fullName, 
           (SELECT COUNT(ws) 
            FROM WorkoutSessionEntity ws 
            WHERE ws.athleteId = a.id
              AND ws.sessionDate BETWEEN :startDate AND :endDate) 
    FROM AthleteEntity a
    WHERE a.id IN :athleteIds
    ORDER BY 2 DESC
    """)
    List<Object[]> findTopAthletesBySessionCount(
            @Param("athleteIds") List<String> athleteIds,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            Pageable pageable
    );



}
