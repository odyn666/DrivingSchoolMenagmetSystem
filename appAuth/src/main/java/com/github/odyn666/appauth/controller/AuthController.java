package com.github.odyn666.appauth.controller;

import com.github.odyn666.appauth.dto.TrainerRegistrationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/api/auth")
public class AuthController {
//    @GetMapping("/trainer/register")
    public ResponseEntity<TrainerRegistrationDto> registerTrainer(@RequestBody TrainerRegistrationDto dto) {

//TODO FIX AUTHORISATION
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

}
