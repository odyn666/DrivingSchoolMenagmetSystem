package com.github.odyn666.appSchool.repository;

import com.github.odyn666.appSchool.entity.StudentEntity;
import com.github.odyn666.appSchool.entity.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentEntityRepository extends JpaRepository<StudentEntity, Long> {

    List<StudentEntity> findAllByEmail(String email);
    List<StudentEntity> findAllByStatus(Status status);
}