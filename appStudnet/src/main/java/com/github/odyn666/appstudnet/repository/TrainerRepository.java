package com.github.odyn666.appstudnet.repository;

import com.github.odyn666.appstudnet.entity.TrainerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerRepository extends JpaRepository<TrainerEntity, Long> {
}
