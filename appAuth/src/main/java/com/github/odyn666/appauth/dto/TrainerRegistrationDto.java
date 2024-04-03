package com.github.odyn666.appauth.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class TrainerRegistrationDto {

    private final @NotEmpty String firstName;
    private final @NotEmpty String lastName;
    private final String identifier;
    private final @NotEmpty String phoneNumber;
    private final @NotEmpty String email;
}
