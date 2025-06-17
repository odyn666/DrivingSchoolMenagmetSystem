package com.github.odyn666.appSchool.controller;

import com.github.odyn666.appSchool.dto.*;
import com.github.odyn666.appSchool.entity.LessonEntity;
import com.github.odyn666.appSchool.entity.StudentEntity;
import com.github.odyn666.appSchool.entity.enums.Status;
import com.github.odyn666.appSchool.service.StudentService;
import com.github.odyn666.appSchool.service.TrainerService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {

    private final TrainerService trainerService;
    private final StudentService studentService;
//TODO: ADD PERMISSIONS FOR MANAGING THOSE ENDPOINTS !!IMPORTANT
    // CREATE
    @PostMapping("/add")
    public ResponseEntity<StudentEntity> registerStudent(@RequestBody StudentRegisterDto dto) {
        return ResponseEntity.ok(studentService.registerStudent(dto));
    }

    @GetMapping("/lessons/{id}")
    public ResponseEntity<LessonEntity> getLessonById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.findLessonById(id));
    }

    @GetMapping("/lessons/byStudent/{id}")
    public ResponseEntity<List<LessonEntity>> getLessonsByStudentId(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getLessonsByStudentId(id));
    }

    @PostMapping("/lessons/request")
    public ResponseEntity<LessonEntityDto> requestLesson(@RequestBody LessonRequestDto lesson) {
        LessonEntityDto entity = studentService.requestLesson(lesson);
        return ResponseEntity.ok(entity);
    }

    //READ
    @GetMapping("/trainers")
    public ResponseEntity<List<TrainerEntityDto>> getTrainers() {
        return ResponseEntity.ok(trainerService.getTrainers());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<StudentEntityDto> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.findStudentById(id));
    }

    @GetMapping
    public ResponseEntity<List<StudentEntity>> getStudentsByStatus(@RequestParam(defaultValue = "INACTIVE") Status status) {

        return ResponseEntity.ok(studentService.getStudentByStatus(status));
    }

    @GetMapping("/email")
    public ResponseEntity<List<StudentEntityDto>> getStudentByEmail(@PathParam("email") String email) {
        return ResponseEntity.ok(studentService.findStudentByEmail(email));
    }
    //UPDATE


    @PatchMapping("/lessons/update")
    public ResponseEntity<LessonEntityDto> updateLesson(@RequestBody LessonUpdateDto lesson) {
        return ResponseEntity.ok(studentService.updateLesson(lesson));
    }

    @PatchMapping
    public ResponseEntity<StudentEntity> blockStudentById(Long id) {
        return ResponseEntity.ok(studentService.blockStudentById(id));
    }
    //DELETE

    @DeleteMapping
    public ResponseEntity<String> deleteActiveStudentById(@RequestParam Long id) {
        studentService.deleteAndArchiveStudentById(id);
        return ResponseEntity.status(204).body("student was deleted");
    }

}
