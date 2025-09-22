package com.carvalho.pts_api_athlete_report.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "perimetry_assessments")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class PerimetryAssessmentEntity {

    @Id
    private Long id;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "athlete_id", nullable = false)
    private String athleteId;

    @Column(name = "waist", nullable = false)
    private Double waist;

    @Column(name = "hip", nullable = false)
    private Double hip;

}
