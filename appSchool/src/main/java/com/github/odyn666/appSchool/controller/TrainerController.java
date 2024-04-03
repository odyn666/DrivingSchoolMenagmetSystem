package com.github.odyn666.appSchool.controller;

import com.github.odyn666.appSchool.dto.TrainerEntityDto;
import com.github.odyn666.appSchool.entity.LessonEntity;
import com.github.odyn666.appSchool.entity.TrainerEntity;
import com.github.odyn666.appSchool.entity.enums.Status;
import com.github.odyn666.appSchool.service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/trainer")
@RequiredArgsConstructor
public class TrainerController {

    private final TrainerService trainerService;

    @PostMapping("/add")
    public ResponseEntity<TrainerEntity> createTrainer(@RequestBody TrainerEntity trainer) {
        return ResponseEntity.ok(trainerService.saveTrainer(trainer));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<TrainerEntity> findTrainerByID(@PathVariable Long id) {
        return ResponseEntity.ok(trainerService.getTrainerByID(id));
    }

    @GetMapping("/status")
    public ResponseEntity<List<TrainerEntityDto>> getTrainerByStatus(@RequestParam Status status) {
        return ResponseEntity.ok(trainerService.getTrainerByStatus(status));
    }

    @GetMapping("/passRate")
    public ResponseEntity<List<TrainerEntityDto>> getTrainerByPassRateFromHighest() {
        return ResponseEntity.ok(trainerService.getTrainersByStudentsPassRate());
    }

    @GetMapping("/identifier")
    public ResponseEntity<TrainerEntityDto> getTrainerByIdentifier(@RequestParam String identifier) {
        return ResponseEntity.ok(trainerService.getTrainerByIdentifier(identifier));
    }

    @GetMapping("/getAllLessonsFromTrainer")
    public ResponseEntity<List<LessonEntity>> findAllTrainerLessonsByTrainerID(Long id) {
        return ResponseEntity.ok(trainerService.findAllTrainerLessonsByTrainerID(id));
    }

    @PatchMapping("/updateTrainer/{id}")
    public ResponseEntity<TrainerEntity> getTrainer(@PathVariable Long id, @RequestBody Map<String, Object> variables) {
        return ResponseEntity.ok(trainerService.updateTrainer(id, variables));
    }


}
