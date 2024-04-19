package com.github.odyn666.appSchool.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordHasher {

    private final BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    public String hashPassword(String password) {

        return passwordEncoder.encode(password);
    }
}
