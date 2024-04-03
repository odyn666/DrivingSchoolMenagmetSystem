package com.github.odyn666.appSchool.repository;

import com.github.odyn666.appSchool.entity.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonEntityRepository extends JpaRepository<LessonEntity, Long> {

//    @Query(value = "SELECT * FROM lessons WHERE student_id = ?1", nativeQuery = true)
    List<LessonEntity> findAllByStudentId(Long id);
}