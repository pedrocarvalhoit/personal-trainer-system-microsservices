package com.carvalho.pts_api_athlete_report.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "bioimpedance_assessments")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class BioimpedanceAssessmentEntity {

    @Id
    private Long id;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "athlete_id", nullable = false)
    private String athleteId;

    @Column(name = "bmi", nullable = false)
    private Double bmi;
}
