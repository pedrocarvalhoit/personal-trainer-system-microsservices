package com.carvalho.pts_api_user.dto;

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
