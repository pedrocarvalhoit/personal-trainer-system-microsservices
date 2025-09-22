package com.carvalho.pts_api_inicial_assessment.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "inicial_assessments")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class InicialAssessmentEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "athlete_id")
    private AthleteEntity athlete;

    @Column(name = "athlete_age", nullable = false)
    private Integer athleteAge;

    @Column(name = "sedentary", nullable = false)
    private boolean sedentary;

    @Column(name = "smoker", nullable = false)
    private boolean smoker;

    @Column(name = "resting_heart_rate", nullable = false)
    private Integer restingHeartRate;

    @Column(name = "max_heart_rate")
    private Integer maxHeartRate;

    @Column(name = "systolic_blood_pressure", nullable = false)
    private Integer systolicBloodPressure;

    @Column(name = "diastolic_blood_pressure", nullable = false)
    private Integer diastolicBloodPressure;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        InicialAssessmentEntity that = (InicialAssessmentEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
