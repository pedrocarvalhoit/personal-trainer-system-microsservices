package com.carvalho.pts_api_athlete_recomendations.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents the entity for storing bioimpedance recommendations.
 * This entity is used to store the recommendations based on bioimpedance assessments,
 * including water consumption and caloric intake recommendations for weight loss or gain.
 */

@Entity
@Table(name = "bioimpedance_recommendations")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class BioimpedanceRecommendationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private String athleteId;

    @Column(nullable = false)
    private Long bioimpedanceId;

    @Column(name = "watter_intake")
    private Double watterIntake;

    @Column(name = "calories_intake_to_achieve_ideal_weight_slow")
    private Double caloriesIntakeToAchieveIdealWeightSlow;

    @Column(name = "calories_intake_to_achieve_ideal_weight_moderate")
    private Double caloriesIntakeToAchieveIdealWeightModerate;

    @Column(name = "calories_intake_to_achieve_ideal_weight_fast")
    private Double caloriesIntakeToAchieveIdealWeightFast;

    @Column(name = "days_to_achieve_ideal_weight_slow")
    private Double daysToAchieveIdealWeightSlow;

    @Column(name = "days_to_achieve_ideal_weight_moderate")
    private Double daysToAchieveIdealWeightModerate;

    @Column(name = "days_to_achieve_ideal_weight_fast")
    private Double daysToAchieveIdealWeightFast;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BioimpedanceRecommendationEntity that = (BioimpedanceRecommendationEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
