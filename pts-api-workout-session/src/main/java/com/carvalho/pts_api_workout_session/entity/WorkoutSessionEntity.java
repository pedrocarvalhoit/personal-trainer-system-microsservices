package com.carvalho.pts_api_workout_session.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "workout_sessions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EntityListeners(AuditingEntityListener.class)
public class WorkoutSessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "athleteId")
    private String athleteId;

    @Column(name = "workout_program_name")
    private String workoutProgramName;

    @Column(name = "session_date")
    private LocalDate sessionDate;

    @Column(name = "session_time")
    private LocalTime sessionTime;

    @Column(name = "client_subjective_effort")
    private Integer clientSubjectiveEffort;

    @Column(name = "pt_quality_effort_indicative")
    private Integer ptQualityEffortIndicative;

    @Column(name = "executed")
    private boolean executed;

}
