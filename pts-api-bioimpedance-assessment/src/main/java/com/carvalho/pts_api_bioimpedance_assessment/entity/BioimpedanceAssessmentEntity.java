package com.carvalho.pts_api_bioimpedance_assessment.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "athlete_id")
    private AthleteEntity athlete;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "height", nullable = false)
    private Double height;

    @Column(name = "body_weight", nullable = false)
    private Double bodyWeight;

    @Column(name = "bmi", nullable = false)
    private Double bmi;

    @Column(name = "body_fat_percentual", nullable = false)
    private Double bodyFatPercentual;

    @Column(name = "body_mass_percentual", nullable = false)
    private Double bodyMassPercentual;

    @Column(name = "basal_metabolism_rate_calories", nullable = false)
    private Double basalMetabolismRateCalories;

    @Column(name = "visceral_fat_indice", nullable = false)
    private Integer visceralFatIndice;

    @Column(name = "ideal_weight", nullable = false)
    private Double idealWeight;

    @Column(name = "ideal_body_fat_percentual", nullable = false)
    private Double idealBodyFatPercentual;

    @Column(name = "notes")
    private String notes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BioimpedanceAssessmentEntity that = (BioimpedanceAssessmentEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
