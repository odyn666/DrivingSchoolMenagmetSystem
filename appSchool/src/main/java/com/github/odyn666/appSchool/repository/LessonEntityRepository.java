package com.github.odyn666.appSchool.repository;

import com.github.odyn666.appSchool.entity.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LessonEntityRepository extends JpaRepository<LessonEntity, Long> {

    List<LessonEntity> findAllByStudentId(Long id);
}