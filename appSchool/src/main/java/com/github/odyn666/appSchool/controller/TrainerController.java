package com.github.odyn666.appSchool.controller;

import com.github.odyn666.appSchool.entity.TrainerEntity;
import com.github.odyn666.appSchool.service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/trainer")
@RequiredArgsConstructor
public class TrainerController {

    private final TrainerService trainerService;

    public ResponseEntity<TrainerEntity> createTrainer(@RequestBody TrainerEntity trainer) {
        return ResponseEntity.ok(trainerService.saveTrainer(trainer));
    }

}
