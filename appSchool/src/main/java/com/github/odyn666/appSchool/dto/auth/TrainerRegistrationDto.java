package com.github.odyn666.appSchool.dto.auth;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class TrainerRegistrationDto {

    private final @NotEmpty String firstName;
    private final @NotEmpty String lastName;
    private final String identifier;
    private final @NotEmpty String phoneNumber;
    private final @NotEmpty String email;
    private String password;
    private String matchingPassword;

}
