package com.github.odyn666.appSchool.controller;

import com.github.odyn666.appSchool.dto.StudentEntityDto;
import com.github.odyn666.appSchool.dto.StudentRegisterDto;
import com.github.odyn666.appSchool.dto.TrainerEntityDto;
import com.github.odyn666.appSchool.entity.LessonEntity;
import com.github.odyn666.appSchool.entity.StudentEntity;
import com.github.odyn666.appSchool.service.StudentService;
import com.github.odyn666.appSchool.service.TrainerService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {

    private final TrainerService trainerService;
    private final StudentService studentService;

    @GetMapping("/trainers")
    public ResponseEntity<List<TrainerEntityDto>> getTrainers() {
        return ResponseEntity.ok(trainerService.getTrainers());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<StudentEntityDto> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.findStudentById(id));
    }

    @GetMapping("/email/")
    public ResponseEntity<List<StudentEntityDto>> getStudentByEmail(@PathParam("email") String email) {
        return ResponseEntity.ok(studentService.findStudentByEmail(email));
    }

    @PostMapping("/add")
    public ResponseEntity<StudentEntity> registerStudent(@RequestBody StudentRegisterDto dto) {
        return ResponseEntity.ok(studentService.registerStudent(dto));
    }

    @GetMapping("/lessons/{id}")
    public ResponseEntity<LessonEntity> getLessonById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.findLessonById(id));
    }

    @GetMapping("/lessons/my")
    public ResponseEntity<List<LessonEntity>> getLessonsByStudentId(@PathParam("studentId") Long id) {
        return ResponseEntity.ok(studentService.getLessonsByStudentId(id));
    }

    @PostMapping("/lessons/request")
    public ResponseEntity<LessonEntity> requestLesson(@RequestBody LessonEntity lesson) {
        return ResponseEntity.ok(studentService.requestLesson(lesson));
    }


    @PatchMapping("/lessons/update")
    public ResponseEntity<LessonEntity> updateLesson(@RequestBody LessonEntity lesson) {
        return ResponseEntity.ok(studentService.updateLesson(lesson));
    }
}
