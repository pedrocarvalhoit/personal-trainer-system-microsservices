package com.carvalho.pts_api_cardio_test.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "cooper_test_description")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CooperTestDescriptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description = "Start on the treadmill with no incline, or flat solo ate the street," +
            " and do your best in 12 minuts. Register the total distance.";

}
