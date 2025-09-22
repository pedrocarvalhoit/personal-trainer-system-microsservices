package com.carvalho.pts_api_workout_session.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PersonalTrainerAndAthleteMappingDto implements Serializable {

    private String personalId;
    private String athleteId;

}
