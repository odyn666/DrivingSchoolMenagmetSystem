package com.github.odyn666.appSchool.repository;

import com.github.odyn666.appSchool.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentEntityRepository extends JpaRepository<StudentEntity, Long> {
}