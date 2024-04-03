package com.github.odyn666.appSchool.dto.auth;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class StudentRegisterDto {
    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String pkkNumber;

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;
    private String matchingPassword;

    @NotEmpty
    private String phoneNumber;
}
