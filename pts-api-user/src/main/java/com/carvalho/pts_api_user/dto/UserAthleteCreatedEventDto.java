package com.carvalho.pts_api_user.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserAthleteCreatedEventDto implements Serializable {

    private String athleteId;

    private String gender;

    private String fullName;

}
