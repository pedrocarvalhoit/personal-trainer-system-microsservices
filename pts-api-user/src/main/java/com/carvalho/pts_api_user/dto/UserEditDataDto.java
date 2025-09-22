package com.carvalho.pts_api_user.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEditDataDto {

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String phone;
    private String gender;

}
