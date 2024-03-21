package com.github.odyn666.appstudnet.controller;

import com.github.odyn666.appstudnet.service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final TrainerService trainerService;

    @GetMapping("/trainers")
    public ResponseEntity<List<TrainerDto>> getTrainers() {
        return ResponseEntity.ok(trainerService.getTrainers());
    }
}
