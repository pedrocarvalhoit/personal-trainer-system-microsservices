package com.carvalho.pts_api_user.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserAthleteDataResponseDto {

    private String id;
    private String fullName;
    private String email;
    private String phone;
    private String photo;
    private LocalDate dateOfBirth;
    private Integer age;
    private String gender;
    private boolean enabled;


}
