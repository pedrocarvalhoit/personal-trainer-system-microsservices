package com.carvalho.pts_api_authentication.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserCreatedEventDto implements Serializable {

    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String phone;
    private String gender;
    private String role;
    private String personalTrainerId;

}
