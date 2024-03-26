package com.github.odyn666.apptrainer.repository;

import com.github.odyn666.apptrainer.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentEntityRepository extends JpaRepository<StudentEntity, Long> {
}