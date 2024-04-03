package com.github.odyn666.appSchool.repository;

import com.github.odyn666.appSchool.entity.LessonEntity;
import com.github.odyn666.appSchool.entity.TrainerEntity;
import com.github.odyn666.appSchool.entity.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainerEntityRepository extends JpaRepository<TrainerEntity, Long> {
    @Query("SELECT t FROM TrainerEntity t WHERE t.firstName = :name or t.lastName=:name")
    List<TrainerEntity> findByName(String name);

    Optional<TrainerEntity> findTrainerEntityByEmail(String email);
    Boolean existsTrainerEntityByEmail(String email);
    Optional<TrainerEntity> findTrainerEntityById(Long id);

    Optional<TrainerEntity> findTrainerEntityByStatus(Status status);

    @Query("SELECT t FROM TrainerEntity t order by t.studentsPassRate desc NULLS LAST")
    List<TrainerEntity> findTrainerEntityByStudentsPassRate();

    Optional<TrainerEntity> findTrainerEntityByIdentifier(String identifier);

    @Query("SELECT t FROM TrainerEntity t LEFT JOIN t.lessons WHERE t.id = :trainerID")
    Optional<List<LessonEntity>> findAllLessonsForTrainer(Long trainerID);




}
