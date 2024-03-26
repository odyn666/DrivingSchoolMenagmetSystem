package com.github.odyn666.appSchool.repository;

import com.github.odyn666.appSchool.entity.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonEntityRepository extends JpaRepository<LessonEntity, Long> {
}