package com.github.odyn666.appSchool.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainerRegistrationDto {

    private @NotEmpty String firstName;
    private @NotEmpty String lastName;
    private @NotEmpty String phoneNumber;
    private @NotEmpty @Email String email;
    private String password;
    private String matchingPassword;

}
