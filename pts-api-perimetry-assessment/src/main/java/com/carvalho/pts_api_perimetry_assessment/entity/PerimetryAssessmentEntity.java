package com.carvalho.pts_api_perimetry_assessment.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "perimetry_assessment")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class PerimetryAssessmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "athlete_id")
    private AthleteEntity athlete;

    @Column(name = "sholder", nullable = false)
    private Double shoulder;

    @Column(name = "torax", nullable = false)
    private Double torax;

    @Column(name = "waist", nullable = false)
    private Double waist;

    @Column(name = "abdomen", nullable = false)
    private Double abdomen;

    @Column(name = "hip", nullable = false)
    private Double hip;

    @Column(name = "rightArm", nullable = false)
    private Double rightArm;

    @Column(name = "leftArm", nullable = false)
    private Double leftArm;

    @Column(name = "rightThigh", nullable = false)
    private Double rightThigh;

    @Column(name = "lefttThigh", nullable = false)
    private Double leftThigh;

    @Column(name = "rightLeg", nullable = false)
    private Double rightLeg;

    @Column(name = "lefttLeg", nullable = false)
    private Double leftLeg;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PerimetryAssessmentEntity that = (PerimetryAssessmentEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
