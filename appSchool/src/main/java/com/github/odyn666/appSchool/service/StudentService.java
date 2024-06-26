package com.github.odyn666.appSchool.service;

import com.github.odyn666.appSchool.dto.StudentEntityDto;
import com.github.odyn666.appSchool.dto.StudentRegisterDto;
import com.github.odyn666.appSchool.entity.LessonEntity;
import com.github.odyn666.appSchool.entity.StudentEntity;
import com.github.odyn666.appSchool.exception.exceptions.LessonNotFoundException;
import com.github.odyn666.appSchool.exception.exceptions.StudentNotFoundException;
import com.github.odyn666.appSchool.mapper.StudentMapper;
import com.github.odyn666.appSchool.repository.LessonEntityRepository;
import com.github.odyn666.appSchool.repository.StudentEntityRepository;
import com.github.odyn666.appSchool.utils.PasswordHasher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentEntityRepository studentRepository;
    private final StudentMapper studentMapper;
    private final PasswordHasher passwordHasher;
    private final LessonEntityRepository lessonRepository;

    public StudentEntityDto findStudentById(Long id) {
        StudentEntity entity = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        StudentEntityDto dto = studentMapper.toDto(entity);

        return dto;
    }

    public StudentEntityDto findStudentByEmail(String email) {
        StudentEntity entity = studentRepository.findByEmail(email).orElseThrow(StudentNotFoundException::new);
        StudentEntityDto dto = studentMapper.toDto(entity);

        return dto;
    }

    public StudentEntity registerStudent(StudentRegisterDto dto) {
        StudentEntity entity = new StudentEntity();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(passwordHasher.hashPassword(dto.getPassword()));
        entity.setPhoneNumber(dto.getPhoneNumber());

        return studentRepository.save(entity);
    }

    public LessonEntity findLessonById(Long id) {
        LessonEntity entity = lessonRepository.findById(id).orElseThrow(LessonNotFoundException::new);

        return entity;
    }

    public LessonEntity requestLesson(LessonEntity lesson) {
        return lessonRepository.save(lesson);
    }

    public LessonEntity updateLesson(LessonEntity lessonUpdated) {
        LessonEntity lessonToUpdate = lessonRepository.findById(lessonUpdated.getId()).orElseThrow(LessonNotFoundException::new);
        lessonToUpdate.setDate(lessonUpdated.getDate());
        lessonToUpdate.setStatus(lessonUpdated.getStatus());
        lessonToUpdate.setStartingHour(lessonUpdated.getStartingHour());
        lessonToUpdate.setEndingHour(lessonUpdated.getEndingHour());

        lessonRepository.save(lessonToUpdate);

        return lessonToUpdate;
    }

    public List<LessonEntity> getLessonsByStudentId(Long id) {
        return lessonRepository.findAllByStudentId(id);
    }
}
