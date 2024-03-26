package com.github.odyn666.appSchool.controller;

import com.github.odyn666.appSchool.dto.TrainerEntityDto;
import com.github.odyn666.appSchool.service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final TrainerService trainerService;

    @GetMapping("/trainers")
    public ResponseEntity<List<TrainerEntityDto>> getTrainers() {
        return ResponseEntity.ok(trainerService.getTrainers());
    }
}
