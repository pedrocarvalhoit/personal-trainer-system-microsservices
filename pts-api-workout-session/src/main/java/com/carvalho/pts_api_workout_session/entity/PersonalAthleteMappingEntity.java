package com.carvalho.pts_api_workout_session.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "personal_athlete_mapping")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class PersonalAthleteMappingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String personalId;

    private String athleteId;

}
