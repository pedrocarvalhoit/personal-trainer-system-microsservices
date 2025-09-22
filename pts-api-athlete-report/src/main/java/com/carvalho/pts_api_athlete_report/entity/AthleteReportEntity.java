package com.carvalho.pts_api_athlete_report.entity;

import com.carvalho.pts_api_athlete_report.entity.enums.BloodPressureStatus;
import com.carvalho.pts_api_athlete_report.entity.enums.BMIStatus;
import com.carvalho.pts_api_athlete_report.entity.enums.WaistHipRisk;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Represents the entity for athlete report using data from all 3 assessemnts.
 * It is important to stablish the health condition of the athlete
 */

@Entity
@Table(name = "athlete_reports")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class AthleteReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "athlete_id")
    private AthleteEntity athlete;

    @Column(name = "blood_pressure_status")
    private BloodPressureStatus bloodPressureStatus; //Inicial Assessment

    @Column(name = "waist_hip_risk")
    private WaistHipRisk waistHipRisk; //Perimetry Assessment

    @Column(name = "IMC_status")
    private BMIStatus BMIStatus; //Bioimpedance Assessment

}
