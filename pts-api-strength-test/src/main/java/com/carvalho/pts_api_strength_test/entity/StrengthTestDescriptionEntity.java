package com.carvalho.pts_api_strength_test.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "strength_test_description")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class StrengthTestDescriptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description = "For a reliable test, do a good specific warm up, and then start." +
            "Perform the selected exercise for 10 MAX repetitions and register de total load (with the barbell).";

}
