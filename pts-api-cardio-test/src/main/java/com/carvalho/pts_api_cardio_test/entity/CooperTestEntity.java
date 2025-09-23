package com.carvalho.pts_api_cardio_test.entity;

import com.carvalho.pts_api_cardio_test.enums.CooperTestClassification;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "cooper_tests")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EntityListeners(AuditingEntityListener.class)
public class CooperTestEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    private String athleteId;
    private Integer athleteAge;
    private String athleteGender;

    @Column(nullable = false)
    private Double distance;

    private Double vo2Max;

    @Enumerated(EnumType.STRING)
    private CooperTestClassification classification;
}
