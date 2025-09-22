package com.carvalho.pts_api_authentication.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class RegisterRequestDto {

    @NotEmpty(message = "First name is mandatory")
    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @NotEmpty(message = "Last name is mandatory")
    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @Email(message = "Format example: phdc@mail.com")
    @NotEmpty(message = "Email is mandatory")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotEmpty(message = "Password is mandatory")
    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Min 8 caracters")
    private String password;

    private LocalDate dateOfBirth;

    @NotEmpty(message = "Mobile is mandatory")
    @NotBlank(message = "Mobile is mandatory")
    private String phone;

    @NotEmpty(message = "Gender is mandatory")
    @NotBlank(message = "Gender is mandatory")
    private String gender;

    @NotEmpty(message = "Role is mandatory")
    @NotBlank(message = "Role is mandatory")
    private String role;

    private String personalTrainerId;

}
