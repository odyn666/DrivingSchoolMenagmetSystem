package com.github.odyn666.appSchool.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class StudentRegisterDto {
    private String firstName;
    private String lastName;
    private String pkkNumber;
    private String email;
    private String password;
    private String matchingPassword;
    private String phoneNumber;
}
