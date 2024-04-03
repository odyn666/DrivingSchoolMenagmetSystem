package com.github.odyn666.appSchool.controller.auth;

import com.github.odyn666.appSchool.dto.auth.TrainerRegistrationDto;
import com.github.odyn666.appSchool.entity.TrainerEntity;
import com.github.odyn666.appSchool.service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final TrainerService trainerService;
    @GetMapping("/trainer/register")
    public ResponseEntity<TrainerEntity> registerTrainer(@RequestBody TrainerRegistrationDto dto) {

        if (trainerService.trainerEmailExists(dto.getEmail()))
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        TrainerEntity trainerEntity = trainerService.saveTrainer(dto);
        return ResponseEntity.ok(trainerEntity);
    }

}