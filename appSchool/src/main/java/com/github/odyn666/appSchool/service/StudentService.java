package com.github.odyn666.appSchool.service;

import com.github.odyn666.appSchool.dto.LessonRequestDto;
import com.github.odyn666.appSchool.dto.StudentEntityDto;
import com.github.odyn666.appSchool.dto.StudentRegisterDto;
import com.github.odyn666.appSchool.entity.LessonEntity;
import com.github.odyn666.appSchool.entity.StudentEntity;
import com.github.odyn666.appSchool.entity.enums.LessonStatus;
import com.github.odyn666.appSchool.exception.exceptions.LessonNotFoundException;
import com.github.odyn666.appSchool.exception.exceptions.StudentNotFoundException;
import com.github.odyn666.appSchool.exception.exceptions.TrainerNotFoundException;
import com.github.odyn666.appSchool.mapper.StudentMapper;
import com.github.odyn666.appSchool.repository.LessonEntityRepository;
import com.github.odyn666.appSchool.repository.StudentEntityRepository;
import com.github.odyn666.appSchool.repository.TrainerEntityRepository;
import com.github.odyn666.appSchool.utils.PasswordHasher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentEntityRepository studentRepository;
    private final StudentMapper studentMapper;
    private final PasswordHasher passwordHasher;
    private final LessonEntityRepository lessonRepository;
    private final TrainerEntityRepository trainerEntityRepository;

    public StudentEntityDto findStudentById(Long id) {
        StudentEntity entity = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        StudentEntityDto dto = studentMapper.toDto(entity);

        return dto;
    }

    public List<StudentEntityDto> findStudentByEmail(String email) {
        List<StudentEntity> entities = studentRepository.findAllByEmail(email);
        return entities.stream()
                .map(studentMapper::toDto)
                .toList();
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

    public LessonEntity requestLesson(LessonRequestDto lessonDto) {
        LessonEntity lesson = new LessonEntity();
        lesson.setDate(Date.valueOf(lessonDto.getDate()));
        lesson.setStatus(LessonStatus.PENDING);
        lesson.setStartingHour(lessonDto.getStartingHour());
        lesson.setEndingHour(lessonDto.getEndingHour());
        lesson.setStudent(studentRepository.findById(lessonDto.getStudentId()).orElseThrow(StudentNotFoundException::new));
        lesson.setTrainer(trainerEntityRepository.findById(lessonDto.getTrainerId()).orElseThrow(TrainerNotFoundException::new));

        System.out.println(lesson.getTrainer());

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
