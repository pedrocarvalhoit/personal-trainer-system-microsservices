package com.carvalho.pts_api_athlete_report.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "inicial_assessments")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class InicialAssessmentEntity {

    @Id
    private Long id;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "athlete_id", nullable = false)
    private String athleteId;

    @Column(name = "systolic_blood_pressure", nullable = false)
    private Integer systolicBloodPressure;

    @Column(name = "diastolic_blood_pressure")
    private Integer diastolicBloodPressure;
}
